package com.mysocialmedia.firebase.service.exceptions;

public class MyFileBadRequestException extends RuntimeException {
    public MyFileBadRequestException() {
        super("Descripcion solo puede tener menos de 300 caracteres y la imagen tiene que tener formato conocido (jpg, png, )");
    }

    public MyFileBadRequestException(String message) {
        super(message);
    }
}
