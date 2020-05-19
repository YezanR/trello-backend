package com.yezan.trello.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shares")
public class Share {

    @EmbeddedId
    private Shares id = new Shares();

    public Share() {

    }

    public Share(Board board, User user) {
        this.id.setUser(user);
        this.id.setBoard(board);
    }

    public Shares getId() {
        return id;
    }

    public void setId(Shares id) {
        this.id = id;
    }
}
