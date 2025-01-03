package com.sportshop.sportshop.exception;

public enum ErrorCode {
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXISTED(1002, "User Existed"),
    USER_NOT_EXISTED(1003, "User Not Existed"),
    USERNAME_INVALID(1004, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1005, "Password must be at least 8 characters"),
    UNCATEGORIZED_EXCEPTION(9999, "uncategorized error");

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
