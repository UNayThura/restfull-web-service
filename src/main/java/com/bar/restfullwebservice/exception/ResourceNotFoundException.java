package com.bar.restfullwebservice.exception;

/**
 * Created by Htay Hlaing Aung on 9/21/2021
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }

}
