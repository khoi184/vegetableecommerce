package com.example.vegetableecommerce.exception;

public class NotFoundIdentityException extends RuntimeException{
    public NotFoundIdentityException(Long id) {
        super("Can not found by id: " + id);
    }
}
