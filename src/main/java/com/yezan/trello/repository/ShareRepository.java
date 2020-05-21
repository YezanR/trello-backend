package com.yezan.trello.repository;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.Share;
import com.yezan.trello.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShareRepository extends CrudRepository<Share, Integer> {
    Optional<Share> findByBoardAndUser(Board board, User withUser);
}
