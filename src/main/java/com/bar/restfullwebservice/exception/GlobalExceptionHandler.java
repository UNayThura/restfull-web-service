package com.bar.restfullwebservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Htay Hlaing Aung on 9/21/2021
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public final ResponseEntity<ApiError> handleResourceNotFoundException(Exception ex){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex, WebRequest webRequest){
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }
}
