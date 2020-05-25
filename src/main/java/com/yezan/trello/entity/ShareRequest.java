package com.yezan.trello.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "share_requests")
public class ShareRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String message;
    private LocalDateTime createdAt;

    public ShareRequest() {

    }

    public ShareRequest(Board board, User user) {
        setBoard(board);
        setUser(user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
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
