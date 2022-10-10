package com.example.vegetableecommerce.exception;

public class UsernameExistedException extends Exception {
    public UsernameExistedException() {
        super("Username existed!");
    }
}
