package com.yezan.trello.exception;

public class UserAlreadySignedUpException extends RuntimeException {

    public UserAlreadySignedUpException(String message) {
        super(message);
    }
}
