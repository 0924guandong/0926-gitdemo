package com.example.p_demo.exception;

public class LoginFailedException extends BaseException {

    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
