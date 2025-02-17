package com.Lokenra.calmly_speak.service.impl;

import com.Lokenra.calmly_speak.DTO.UserDto;
import com.Lokenra.calmly_speak.entity.User;
import com.Lokenra.calmly_speak.repository.UserRepo;
import com.Lokenra.calmly_speak.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        User savedUser = userRepo.save(user);
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUser(int userId) {
        User user = this.userRepo.findById(userId).orElseThrow();
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        this.userRepo.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }
}
