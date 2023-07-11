package com.inverse.project.Jobless.exceptions;

public class APIException extends RuntimeException {
    public APIException(String message) {
        super(message);
    }

    public APIException() {
        super();
    }
}
