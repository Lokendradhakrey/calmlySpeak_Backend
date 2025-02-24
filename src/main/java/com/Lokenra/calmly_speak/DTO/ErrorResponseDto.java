package com.Lokenra.calmly_speak.DTO;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponseDto {
    private String message;
    private int status;
    private LocalDateTime timestamp;

    public ErrorResponseDto(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
