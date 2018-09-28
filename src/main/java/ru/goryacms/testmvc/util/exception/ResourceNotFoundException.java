package ru.goryacms.testmvc.util.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message, null, true, false);
    }
}