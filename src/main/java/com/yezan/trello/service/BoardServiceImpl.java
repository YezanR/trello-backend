package com.yezan.trello.service;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.Share;
import com.yezan.trello.entity.User;
import com.yezan.trello.repository.BoardRepository;
import com.yezan.trello.repository.SharingRepository;
import com.yezan.trello.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final SharingRepository sharingRepository;
    private final UserRepository userRepository;

    public BoardServiceImpl(
            BoardRepository boardRepository,
            SharingRepository sharingRepository,
            UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.sharingRepository = sharingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Board> findAllByOwner(User owner) {
        List<Board> boards = new ArrayList<>();
        Iterable<Board> results = this.boardRepository.findAllByOwner(owner);
        results.forEach(boards::add);
        return boards;
    }

    @Override
    public Board create(Board board) {
        return this.boardRepository.save(board);
    }

    @Override
    public Board update(int id, Board board) {
        Optional<Board> searchResult = this.boardRepository.findById(id);
        if (searchResult.isPresent()) {
            Board existingBoard = searchResult.get();
            existingBoard.setTitle(board.getTitle());
            existingBoard.setDescription(board.getDescription());
            return this.boardRepository.save(existingBoard);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void delete(int id) {
        this.boardRepository.deleteById(id);
    }

    @Override
    public Board findById(int id) {
        return this.boardRepository.getOneById(id);
    }

    @Override
    public Share share(Board board, User user) {
        Share share = new Share(board, user);
        return this.sharingRepository.save(share);
    }

    @Override
    public Share share(int boardId, int userId) {
        Board board = this.findById(boardId);
        User user = this.userRepository.getOneById(userId);
        return this.share(board, user);
    }
}
