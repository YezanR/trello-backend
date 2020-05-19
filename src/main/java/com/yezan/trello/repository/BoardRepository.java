package com.yezan.trello.repository;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends CrudRepository<Board, Integer> {
    List<Board> findAllByOwner(User owner);
    Board getOneById(int id);
}
