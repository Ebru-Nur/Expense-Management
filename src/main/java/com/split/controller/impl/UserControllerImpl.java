package com.split.controller.impl;

import com.split.dto.UserDto;
import com.split.dto.UserDtoIU;
import com.split.services.IUserService;
import com.split.controller.IUserController;
import com.split.entities.User;
import com.split.utils.ResponseUtils;
import com.split.utils.UtilJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserControllerImpl implements IUserController {

    @Autowired
    private IUserService userService; //genelde interface'i ile implemente edilir
    private final UtilJWT utilJWT;

    public UserControllerImpl(IUserService userService, UtilJWT utilJWT) {
        this.userService = userService;
        this.utilJWT = utilJWT;
    }

    @PostMapping(path = "/register")
    @Override
    public UserDto register(@RequestBody UserDtoIU user) {
        return userService.saveUser(user);
    }

    @PostMapping(path = "/login")
    @Override
    public UserDto login(@RequestBody UserDtoIU user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    @PutMapping(path = "/update")
    @Override
    public UserDto updateUser(@RequestBody UserDtoIU updateUser) {
        String id= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userService.updateUser(Integer.parseInt(id), updateUser);
    }

    @GetMapping(path = "/list")
    @Override
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    @Override
    public User getUserById(@PathVariable(name = "id") Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(path = "/")
    @Override
    public ResponseEntity<Map<String, Object>> deleteUser() {
        String id= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        boolean response = userService.deleteUser(Integer.parseInt(id));
        if (!response) return ResponseUtils.error("User not found");
        return ResponseUtils.success("User deleted successfully",true);
    }




}
