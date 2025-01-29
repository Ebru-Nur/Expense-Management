package com.split.controller;

import com.split.dto.UserDto;
import com.split.dto.UserDtoIU;
import com.split.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IUserController {

    public UserDto register(UserDtoIU dtoUser);
    public UserDto login(UserDtoIU dtoUser);

    public UserDto updateUser(UserDtoIU updateUser);

    public List<UserDto> getAllUsers();

    public User getUserById(Integer id);

    public ResponseEntity<Map<String, Object>> deleteUser();



}
