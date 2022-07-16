package com.arnaud.p12.exception;

public enum ErrorCode {

    USER_NOT_FOUND(1000),
    USER_NOT_VALID(1001),
    MAIL_FORMAT_INVALID(1002),
    NONE_ROLE_COLLECTION(2000),
    ASSOCIATION_NOT_FOUND(3000);


    private final int code;

    ErrorCode(int code) {
        this.code=code;
    }
}
