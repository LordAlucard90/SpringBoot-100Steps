package com.lordalucard90.springboottutorials.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

//    @Override
//    public synchronized Throwable fillInStackTrace() {
//        return null;
//    }
}
