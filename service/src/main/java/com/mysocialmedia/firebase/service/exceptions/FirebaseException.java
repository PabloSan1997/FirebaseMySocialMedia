package com.mysocialmedia.firebase.service.exceptions;

public class FirebaseException extends RuntimeException{
    public FirebaseException() {
        super("Error al guardar datos");
    }
}
