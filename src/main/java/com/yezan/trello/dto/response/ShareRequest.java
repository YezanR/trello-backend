package com.yezan.trello.dto.response;

import com.yezan.trello.entity.BaseEntity;
import com.yezan.trello.entity.User;

import java.time.LocalDateTime;

public class ShareRequest {

    public Board board;
    public User user;
    public String message;
    public LocalDateTime createdAt;

    public ShareRequest() {

    }

    public ShareRequest(Board board, User user) {
        this.board = board;
        this.user = user;
    }

    public static ShareRequest fromEntity(BaseEntity entity) {
        com.yezan.trello.entity.ShareRequest requestEntity = (com.yezan.trello.entity.ShareRequest) entity;
        Board board = Board.fromEntity(requestEntity.getBoard());
        ShareRequest shareRequest = new ShareRequest(board, requestEntity.getUser());
        shareRequest.message = requestEntity.getMessage();
        shareRequest.createdAt = requestEntity.getCreatedAt();
        return shareRequest;
    }
}

class Board {
    public int id;
    public String title;
    public User owner;

    public Board() {

    }

    public static Board fromEntity(BaseEntity entity) {
        com.yezan.trello.entity.Board boardEntity = (com.yezan.trello.entity.Board) entity;
        Board board = new Board();
        board.id = boardEntity.getId();
        board.title = boardEntity.getTitle();
        board.owner = boardEntity.getOwner();
        return board;
    }
}
