package com.mysocialmedia.firebase.service.controllers;

import com.mysocialmedia.firebase.service.exceptions.MyBadRequestException;
import com.mysocialmedia.firebase.service.exceptions.MyFileBadRequestException;
import com.mysocialmedia.firebase.service.models.dtos.ErrorDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;


@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException e){
        StringBuilder stringBuilder = new StringBuilder();
        for(var fielError : e.getFieldErrors()){
            stringBuilder.append(fielError.getField())
                    .append(": ")
                    .append(fielError.getDefaultMessage())
                    .append(". ");
        }
        String message = stringBuilder.toString().trim();
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, message);
        return ResponseEntity.status(errorDto.getStatusCode()).body(errorDto);
    }

    @ExceptionHandler({
            MyFileBadRequestException.class,
            MyBadRequestException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageConversionException.class
    })
    public ResponseEntity<?> badRequest(Exception e){
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(errorDto.getStatusCode()).body(errorDto);
    }

    @ExceptionHandler({
            AccessDeniedException.class,  // Acceso denegado
            AuthenticationException.class // No autenticado
    })
    public ResponseEntity<?> unauthorized(Exception e) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.FORBIDDEN, e.getMessage());
        return ResponseEntity.status(errorDto.getStatusCode()).body(errorDto);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> methodnotallowed(Exception e) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        return ResponseEntity.status(errorDto.getStatusCode()).body(errorDto);
    }

    @ExceptionHandler({
            NoResourceFoundException.class,
            EntityNotFoundException.class
    })
    public ResponseEntity<?> notFound(Exception e){
        ErrorDto errorDto = new ErrorDto(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(errorDto.getStatusCode()).body(errorDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> internalServerError(RuntimeException e){
        ErrorDto errorDto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(errorDto.getStatusCode()).body(errorDto);
    }
}
