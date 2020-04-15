package com.yezan.trello.service;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.User;

import java.util.List;

public interface BoardService {
    List<Board> findAllByOwner(User owner);
    Board create(Board board);
    Board update(int id, Board board);
    void delete(int id);
}
