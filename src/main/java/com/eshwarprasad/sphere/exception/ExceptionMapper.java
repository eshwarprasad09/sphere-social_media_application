package com.eshwarprasad.sphere.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionMapper {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
//       return new ResponseEntity<>(new Object[]{resourceNotFoundException.getFieldValue(),resourceNotFoundException.getResourceName(),
//       resourceNotFoundException.getFieldName()},HttpStatus.NOT_FOUND);
//    }

}
