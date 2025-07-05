package com.springrestapi.exception;

public class PersonNotCreatedException extends RuntimeException {
    public PersonNotCreatedException(String message) {
        super(message);
    }
}
