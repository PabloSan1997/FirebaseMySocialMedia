package com.mysocialmedia.firebase.service.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
public class ErrorDto {
    private Integer statusCode;
    private String error;
    private String message;
    private Date timestamp;
    public ErrorDto(HttpStatus status, String message){
        this.statusCode = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.timestamp = new Date();
    }

}
