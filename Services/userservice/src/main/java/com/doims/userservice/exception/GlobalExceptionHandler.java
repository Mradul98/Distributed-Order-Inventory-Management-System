package com.doims.userservice.exception;

import com.doims.userservice.dto.ApiResponse;
import com.doims.userservice.dto.ErrorResponse;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //private static final Logger log = (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailExists(EmailAlreadyExistsException ex)
    {
        ErrorResponse errorResponse=new ErrorResponse(
                ex.getMessage(),
                "User_001",
                false,
                LocalDateTime.now()

        );

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handelValidationErrors(MethodArgumentNotValidException ex)
    {
        List<String> errors=ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        ErrorResponse errorResponse=new ErrorResponse(
                errors.toString(),
                "VALIDATION_ERROR",
                false,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex)
    {
        ErrorResponse errorResponse=new ErrorResponse(
                "Something went wrong",
                "Generic_500",
                false,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


}
