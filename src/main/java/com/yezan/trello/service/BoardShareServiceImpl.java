package com.yezan.trello.service;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.Share;
import com.yezan.trello.entity.ShareRequest;
import com.yezan.trello.entity.User;
import com.yezan.trello.repository.ShareRepository;
import com.yezan.trello.repository.ShareRequestRepository;
import com.yezan.trello.service.exception.BoardShareException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class BoardShareServiceImpl implements BoardShareService {

    private final BoardService boardService;
    private final ShareRepository shareRepository;
    private final ShareRequestRepository shareRequestRepository;

    public BoardShareServiceImpl(
            BoardService boardService,
            ShareRepository shareRepository,
            ShareRequestRepository shareRequestRepository) {
        this.boardService = boardService;
        this.shareRepository = shareRepository;
        this.shareRequestRepository = shareRequestRepository;
    }

    @Override
    public Share share(Board board, User user) {
        if (isSharedWith(board, user)) {
            throw new BoardShareException("Board already shared");
        }

        Share share = new Share(board, user);
        return this.shareRepository.save(share);
    }

    @Override
    public Share share(int boardId, User withUser) {
        Board board = this.boardService.findById(boardId);
        return this.share(board, withUser);
    }

    @Override
    public ShareRequest request(int boardId, User withUser) {
        Board board = this.boardService.findById(boardId);

        if (isRequestedWith(board, withUser) || isSharedWith(board, withUser)) {
            throw new BoardShareException("Board already shared or a request is pending");
        }

        ShareRequest request = new ShareRequest(board, withUser);
        return this.shareRequestRepository.save(request);
    }

    @Override
    public boolean isRequestedWith(Board board, User withUser) {
        Optional<ShareRequest> searchRequest =
                this.shareRequestRepository.findByBoardAndUser(board, withUser);
        return searchRequest.isPresent();
    }

    @Override
    public boolean isSharedWith(Board board, User withUser) {
        Optional<Share> searchRequest =
                this.shareRepository.findByBoardAndUser(board, withUser);
        return searchRequest.isPresent();
    }

    @Override
    @Transactional
    public Share accept(int shareRequestId) {
        Optional<ShareRequest> searchRequest = this.shareRequestRepository.findById(shareRequestId);
        ShareRequest request = searchRequest.orElseThrow(
                () -> new EntityNotFoundException("Share request not found")
        );
        Share share = this.share(request.getBoard(), request.getUser());
        this.shareRequestRepository.delete(request);
        return share;
    }
}
