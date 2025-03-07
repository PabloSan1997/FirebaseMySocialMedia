package com.mysocialmedia.firebase.service.exceptions;

public class MyBadRequestException extends RuntimeException {
    public MyBadRequestException() {
        super();
    }

    public MyBadRequestException(String message) {
        super(message);
    }
}
