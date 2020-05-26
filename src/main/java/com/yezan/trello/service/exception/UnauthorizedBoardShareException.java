package com.yezan.trello.service.exception;

public class UnauthorizedBoardShareException extends BoardShareException {
    public UnauthorizedBoardShareException(String message) {
        super(message);
    }
}
