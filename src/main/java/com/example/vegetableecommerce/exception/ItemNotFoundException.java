package com.example.vegetableecommerce.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException() {
        super("Product not existed!");
    }
}
