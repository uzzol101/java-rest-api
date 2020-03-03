package com.uzzol.crud.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExeption extends Exception {

    public static final long serialVersionId = 1L;
    public ResourceNotFoundExeption(String message) {
        super(message);
    }
}
