package com.dsac.ecommer_backend.exception;

public class ResourceFoundException extends Exception {

    public ResourceFoundException() {
        super("ERROR: The resource already exists. Try again");
    }

    public ResourceFoundException(String message) {
        super(message);
    }
}
