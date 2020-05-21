package com.yezan.trello.service;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.Share;
import com.yezan.trello.entity.ShareRequest;
import com.yezan.trello.entity.User;

public interface BoardShareService {
    Share share(Board board, User user);
    Share share(int boardId, User withUser);
    ShareRequest request(int boardId, User withUser);
    Share accept(int shareRequestId);
    boolean isRequestedWith(Board board, User withUser);
    boolean isSharedWith(Board board, User withUser);
}
