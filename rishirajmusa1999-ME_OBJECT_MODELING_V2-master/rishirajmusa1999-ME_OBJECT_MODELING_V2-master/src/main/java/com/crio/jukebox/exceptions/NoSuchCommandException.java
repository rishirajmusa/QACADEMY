package com.crio.jukebox.exceptions;

public class NoSuchCommandException extends RuntimeException{

    public NoSuchCommandException() {
        super();
    }

    public NoSuchCommandException(String message) {
        super(message);
    }
    
}
