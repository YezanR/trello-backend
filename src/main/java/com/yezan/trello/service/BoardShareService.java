package com.yezan.trello.service;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.Share;
import com.yezan.trello.entity.ShareRequest;
import com.yezan.trello.entity.User;

import java.util.List;

public interface BoardShareService {
    Share share(Board board, User user);
    Share share(int boardId, User withUser);
    ShareRequest request(int boardId, User withUser, User requester);
    Share accept(int shareRequestId, User acceptor);
    boolean isRequestedWith(Board board, User withUser);
    boolean isSharedWith(Board board, User withUser);
    List<ShareRequest> findAllRequests(User user);
    boolean isMember(User user, int boardId);
}
