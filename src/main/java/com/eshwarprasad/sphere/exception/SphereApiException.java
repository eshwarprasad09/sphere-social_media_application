package com.eshwarprasad.sphere.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SphereApiException extends RuntimeException{

    private final HttpStatus status;
    private final String message;

    public SphereApiException(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
    public SphereApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

}
