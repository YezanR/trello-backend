package com.yezan.trello.service;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.User;
import com.yezan.trello.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

    @Mock
    BoardRepository boardRepository;

    @InjectMocks
    BoardServiceImpl boardService;

    @Test
    void findAllByOwner_ShouldReturnBoards() {
        List<Board> expectedBoards = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Board board = new Board();
            board.setId(i+1);
            expectedBoards.add(board);
        }
        when(boardRepository.findAllByOwner(any())).thenReturn(expectedBoards);

        List<Board> boards = this.boardService.findAllByOwner(null);

        assertEquals(expectedBoards, boards);
    }

    @Test
    void create() {
        Board board = new Board("Foo");
        when(boardRepository.save(board)).thenReturn(board);

        Board createdBoard = this.boardService.create(board);

        assertEquals(board.getTitle(), createdBoard.getTitle());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }
}