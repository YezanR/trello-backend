package com.yezan.trello.repository;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.ShareRequest;
import com.yezan.trello.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShareRequestRepository extends CrudRepository<ShareRequest, Integer> {
    Optional<ShareRequest> findByBoardAndUser(Board board, User withUser);
    List<ShareRequest> findAllByUser(User user);
}
