package com.doims.userservice.exception;

import com.doims.userservice.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handelValidationError(MethodArgumentNotValidException ex)
    {
        String message=ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(new ApiResponse(message,false), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handelGeneralException(Exception ex)
    {
        return new ResponseEntity<>(new ApiResponse("Something went wrong",false),HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
