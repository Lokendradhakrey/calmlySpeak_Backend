package com.Lokenra.calmly_speak.controller;

import com.Lokenra.calmly_speak.DTO.UserDto;
import com.Lokenra.calmly_speak.auth.service.CustomUserServiceDetails;
import com.Lokenra.calmly_speak.auth.service.JwtService;
import com.Lokenra.calmly_speak.entity.AuthReq;
import com.Lokenra.calmly_speak.entity.AuthResponse;
import com.Lokenra.calmly_speak.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calmlySpeak")
public class UserController {

    private final UserService userService;
    private final CustomUserServiceDetails serviceDetails;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public UserController(UserService userService, CustomUserServiceDetails serviceDetails, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.serviceDetails = serviceDetails;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> createUser(@RequestBody UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        String accessToken = jwtService.generateToken(serviceDetails.loadUserByUsername(user.getEmail()));
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(accessToken);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable int userId) {
        UserDto user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User has been deleted with userId: " + userId);
    }

    @PostMapping("/authenticate")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthReq authReq) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword()));
        if (authentication.isAuthenticated()) {
            AuthResponse response = new AuthResponse();
            response.setAccessToken(jwtService.generateToken(serviceDetails.loadUserByUsername(authReq.getEmail())));
            return response;
        } else {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
    }


}
