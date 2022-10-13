package com.example.vegetableecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler({UsernameExistedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUsernameNotFoundException(UsernameExistedException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({NotFoundIdentityException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundIdentityException(NotFoundIdentityException e) {
        return e.getMessage();
    }


    @ResponseBody
    @ExceptionHandler({ItemNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleProductNotFoundException(NotFoundIdentityException e) {
        return e.getMessage();
    }
}
