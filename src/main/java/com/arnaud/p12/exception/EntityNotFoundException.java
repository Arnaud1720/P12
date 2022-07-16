package com.arnaud.p12.exception;

import lombok.Getter;

import java.util.List;

public class EntityNotFoundException  extends RuntimeException{
    @Getter
    private final ErrorCode errorCodes;
    @Getter
    private List<String> erros;

    public EntityNotFoundException(String message, ErrorCode errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }


}
