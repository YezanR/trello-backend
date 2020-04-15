package com.yezan.trello.service;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.User;
import com.yezan.trello.repository.BoardRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
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
            return this.boardRepository.save(board);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void delete(int id) {
        this.boardRepository.deleteById(id);
    }
}
