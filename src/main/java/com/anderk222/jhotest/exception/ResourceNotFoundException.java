package com.anderk222.jhotest.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    private Object id;
    private String field;
    private  String resource;

    public ResourceNotFoundException(Object id, String field, String resource) {
    
        super(resource +" not found with "+field+ " "+field);
        
        this.id=id;
        this.field=field;
        this.resource =resource;
        
    }
    
    
}