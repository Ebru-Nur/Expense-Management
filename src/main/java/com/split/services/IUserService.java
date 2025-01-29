package com.split.services;

import com.split.dto.UserDto;
import com.split.dto.UserDtoIU;
import com.split.entities.User;

import java.util.List;

public interface IUserService {

    public UserDto saveUser(UserDtoIU user);
    public UserDto login(String email, String password);
    public UserDto updateUser(Integer id, UserDtoIU user);

    public List<UserDto> getAllUsers();

    public User getUserById(Integer id);

    public boolean deleteUser(Integer id);

    public UserDto getUserByEmail (String email);


}
