package com.thiagoqvdo.data.analyze.exceptions;

public class FailedToReadFlatFileException extends RuntimeException{
    private static final String message = "Failed to read flat file.";
    public FailedToReadFlatFileException() {
        super(message);
    }

    public FailedToReadFlatFileException(String message) {
        super(message);
    }

    public FailedToReadFlatFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToReadFlatFileException(Throwable cause) {
        super(cause);
    }

    public FailedToReadFlatFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
