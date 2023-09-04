package com.compass.challenge.exceptions;

public class ResourceNotFoundException extends Exception{
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue)); // Post not found with id : 1
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(Long postId) {
        super(String.format("%s not found with %s : '%s'",postId));
    }

    public ResourceNotFoundException() {

    }
}
