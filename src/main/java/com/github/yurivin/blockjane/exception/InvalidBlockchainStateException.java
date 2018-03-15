package com.github.yurivin.blockjane.exception;

public class InvalidBlockchainStateException extends RuntimeException {

    public InvalidBlockchainStateException(String message) {
        super(message);
    }

    InvalidBlockchainStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
