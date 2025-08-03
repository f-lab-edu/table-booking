package me.app.tablebooking.common.exception;

public class ErrorMsg {
    private final String message;
    private final String errorType;

    public ErrorMsg(String message, String errorType) {
        this.message = message;
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorType() {
        return errorType;
    }
}

