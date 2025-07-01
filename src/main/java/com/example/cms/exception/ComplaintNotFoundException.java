package com.example.cms.exception;

public class ComplaintNotFoundException extends RuntimeException{
    public ComplaintNotFoundException(String message) {
        super(message);
    }

    public ComplaintNotFoundException() {
        super("complaint not found");
    }
}
