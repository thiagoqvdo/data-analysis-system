package com.thiagoqvdo.data.analyze.exceptions;

public class FailedToCreateReportException extends RuntimeException{
    private static final String message = "Failed create flat file.";
    public FailedToCreateReportException() {
        super(message);
    }

    public FailedToCreateReportException(String message) {
        super(message);
    }

    public FailedToCreateReportException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToCreateReportException(Throwable cause) {
        super(cause);
    }

    public FailedToCreateReportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
