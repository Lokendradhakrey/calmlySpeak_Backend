package com.Lokenra.calmly_speak.service;

import com.Lokenra.calmly_speak.DTO.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto getUser(int userId);
    void deleteUser(UserDto userId);
    List<UserDto> getAllUsers();
}
