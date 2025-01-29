package com.split.services.impl;

import com.split.dto.UserDto;
import com.split.dto.UserDtoIU;
import com.split.entities.*;
import com.split.repository.UserRepository;
import com.split.services.IUserService;

import com.split.utils.UtilJWT;
import com.split.utils.Validator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UtilJWT jwtUtil;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UtilJWT jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDto saveUser(UserDtoIU dtoUser) {
        User user = new User();
        if( userRepository.findByEmail(dtoUser.getEmail()) != null){
            throw new IllegalArgumentException("User already exists");
        }
        if (!Validator.email(dtoUser.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        user.setFirstName(dtoUser.getFirstName());
        user.setLastName(dtoUser.getLastName());
        user.setEmail(dtoUser.getEmail());
        user.setPhone(dtoUser.getPhone());
        user.setPassword( passwordEncoder.encode(dtoUser.getPassword()));
        User dbUser = userRepository.save(user);//değişiklikleri kaydettikten sonra veritabanındaki studentı dönüyor
        String token = jwtUtil.generateToken(user.getId().toString());
        return new UserDto(token, dbUser.getFirstName(), dbUser.getLastName() );

    }

    @Override
    public UserDto login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found."
            );
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid password."
            );
        }
        String token = jwtUtil.generateToken(user.getId().toString());
        return new UserDto(token, user.getFirstName(), user.getLastName());
    }
    @Override
    public UserDto updateUser(Integer id, UserDtoIU updatedUserDto) {
        Optional<User> optional = userRepository.findById(id);
        if (optional == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found."

            );
        }
        User user = optional.get();
        if (updatedUserDto.getFirstName() != null) {
            user.setFirstName(updatedUserDto.getFirstName());
        }
        if (updatedUserDto.getLastName() != null) {
            user.setLastName(updatedUserDto.getLastName());
        }
        if (updatedUserDto.getEmail() != null) {
            user.setEmail(updatedUserDto.getEmail());
        }
        if (updatedUserDto.getPhone() != null) {
            user.setPhone(updatedUserDto.getPhone());
        }
        User updatedUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(updatedUser, userDto);
        return userDto;
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDto> dtoList = new ArrayList<>();
        for (User user : userList) {//tüm elemanları döneceği için for kullandık
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(user, dto);//userlist içinde dönen elemanları dto içerisine aktardık
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        User user = optional.get();
        return user;
    }

    @Override
    public boolean deleteUser(Integer id) {
        try {
            Optional<User> optional = userRepository.findById(id);
            if (optional == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found."
                );
            }
            userRepository.delete(optional.get());
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }


}
