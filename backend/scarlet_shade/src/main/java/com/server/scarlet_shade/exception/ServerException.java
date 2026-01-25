package com.server.scarlet_shade.exception;

public class ServerException extends RuntimeException {
    
    public ServerException(){
        super("The Database could not register the values");
    }

    public ServerException(Exception e) {
        super(e);
    }
}
