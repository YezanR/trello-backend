package com.yezan.trello.service;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.User;
import com.yezan.trello.repository.BoardRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(
            BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<Board> findAllByOwner(User owner) {
        return this.boardRepository.findAllByOwner(owner);
    }

    @Override
    public Board create(Board board) {
        return this.boardRepository.save(board);
    }

    @Override
    public Board update(int id, Board board) {
        Board existingBoard = this.findById(id);
        existingBoard.setTitle(board.getTitle());
        existingBoard.setDescription(board.getDescription());
        return this.boardRepository.save(existingBoard);
    }

    @Override
    public void delete(int id) {
        this.boardRepository.deleteById(id);
    }

    @Override
    public Board findById(int id) {
        Optional<Board> searchResult = this.boardRepository.findById(id);
        return searchResult.orElseThrow(EntityNotFoundException::new);
    }
}
