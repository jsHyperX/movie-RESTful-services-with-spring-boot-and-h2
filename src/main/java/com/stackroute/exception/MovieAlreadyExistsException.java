package com.stackroute.exception;

public class MovieAlreadyExistsException extends Exception {

    public MovieAlreadyExistsException() {
        super();
    }

    String messge;
    public MovieAlreadyExistsException(String message) {
        super(message);
        this.messge = message;

    }

}
