package com.yezan.trello.service;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.Share;
import com.yezan.trello.entity.ShareRequest;
import com.yezan.trello.entity.User;
import com.yezan.trello.repository.ShareRepository;
import com.yezan.trello.repository.ShareRequestRepository;
import com.yezan.trello.service.exception.BoardShareException;
import com.yezan.trello.service.exception.UnauthorizedBoardShareException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
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
    public Share share(Board board, User withUser) {
        if (isSharedWith(board, withUser)) {
            throw new BoardShareException("Board already shared");
        }
        if (board.getOwner().equals(withUser)) {
            throw new BoardShareException("Owner shouldn't share a board with himself!");
        }

        Share share = new Share(board, withUser);
        return this.shareRepository.save(share);
    }

    @Override
    public Share share(int boardId, User withUser) {
        Board board = this.boardService.findById(boardId);
        return this.share(board, withUser);
    }

    @Override
    public ShareRequest request(int boardId, User withUser, User requester) {
        Board board = this.boardService.findById(boardId);

        if (isRequestedWith(board, withUser) || isSharedWith(board, withUser)) {
            throw new BoardShareException("Board already shared or a request is pending");
        }
        if (board.getOwner().equals(withUser)) {
            throw new BoardShareException("Owner shouldn't share a board with himself!");
        }
        if (!board.getOwner().equals(requester)) {
            throw new UnauthorizedBoardShareException(
                    "Only board owner is allowed to share their board");
        }

        ShareRequest request = new ShareRequest(board, withUser);
        request.setCreatedAt(LocalDateTime.now());
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
    public Share accept(int shareRequestId, User acceptor) {
        Optional<ShareRequest> searchRequest = this.shareRequestRepository.findById(shareRequestId);
        ShareRequest request = searchRequest.orElseThrow(
                () -> new EntityNotFoundException("Share request not found")
        );

        if (!acceptor.equals(request.getUser())) {
            throw new UnauthorizedBoardShareException("Only concerned user is allowed to accept a share request");
        }

        Share share = this.share(request.getBoard(), request.getUser());
        this.shareRequestRepository.delete(request);
        return share;
    }

    @Override
    public List<ShareRequest> findAllRequests(User forUser) {
        return this.shareRequestRepository.findAllByUser(forUser);
    }
}
