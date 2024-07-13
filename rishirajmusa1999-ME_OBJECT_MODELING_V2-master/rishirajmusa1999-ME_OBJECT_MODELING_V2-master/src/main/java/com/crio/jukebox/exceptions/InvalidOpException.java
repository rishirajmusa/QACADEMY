package com.crio.jukebox.exceptions;

public class InvalidOpException extends RuntimeException {

    public InvalidOpException() {
        super();
    }

    public InvalidOpException(String message) {
        super(message);
    }
    
}
