package com.example.consultantinternship.excepiton;

public class WrongIdException extends Exception {
    public WrongIdException() {
        super();
    }

    public WrongIdException(String message) {
        super(message);
    }

    public WrongIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongIdException(Throwable cause) {
        super(cause);
    }

    protected WrongIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
