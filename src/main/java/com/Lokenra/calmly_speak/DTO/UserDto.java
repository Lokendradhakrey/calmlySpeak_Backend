package com.Lokenra.calmly_speak.DTO;

import com.Lokenra.calmly_speak.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private UserRole role;
}
