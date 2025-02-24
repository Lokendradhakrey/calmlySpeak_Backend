package com.Lokenra.calmly_speak.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    Integer id;

    public ResourceNotFoundException(String resourceName, String fieldName, Integer id) {
        super(String.format("%s not found with %s: %d", resourceName, fieldName, id));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.id = id;
    }
}
