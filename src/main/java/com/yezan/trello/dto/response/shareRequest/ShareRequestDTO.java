package com.yezan.trello.dto.response.shareRequest;

import com.yezan.trello.entity.User;

import java.time.LocalDateTime;

public class ShareRequestDTO {
    private BoardDTO board;
    private User user;
    private String message;
    private LocalDateTime createdAt;

    public BoardDTO getBoard() {
        return board;
    }

    public void setBoard(BoardDTO board) {
        this.board = board;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

