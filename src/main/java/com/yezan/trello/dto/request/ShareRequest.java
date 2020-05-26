package com.yezan.trello.dto.request;

import javax.validation.constraints.NotNull;

public class ShareRequest {

    @NotNull
    private String username;

    @NotNull
    private int boardId;

    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
