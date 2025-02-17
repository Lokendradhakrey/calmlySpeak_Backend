package com.Lokenra.calmly_speak.auth.service;

import com.Lokenra.calmly_speak.entity.User;
import com.Lokenra.calmly_speak.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserServiceDetails implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserServiceDetails(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(username);
        if (user.isPresent()) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.get().getEmail())
                    .password(user.get().getPassword())
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException(username);
    }
}
