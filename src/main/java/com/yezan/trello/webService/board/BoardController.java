package com.yezan.trello.webService.board;

import com.yezan.trello.entity.Board;
import com.yezan.trello.entity.User;
import com.yezan.trello.security.Auth;
import com.yezan.trello.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/boards")
public class BoardController {

    private final BoardService boardService;
    private final Auth auth;

    public BoardController(
            BoardService boardService,
            Auth auth) {
        this.boardService = boardService;
        this.auth = auth;
    }

    @GetMapping("personal")
    public List<Board> findAllPersonal() {
        User currentUser = this.auth.getUser();
        return this.boardService.findAllByOwner(currentUser);
    }

    @GetMapping("{id}")
    public ResponseEntity<Board> findById(@PathVariable int id) {
        try {
            Board board = this.boardService.findById(id);
            return ResponseEntity.ok(board);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Board> create(@RequestBody Board board) {
        User owner = this.auth.getUser();
        board.setOwner(owner);
        Board createdBoard = this.boardService.create(board);
        return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Board> update(@PathVariable int id, @RequestBody Board board) {
        try {
            Board updatedBoard = this.boardService.update(id, board);
            return ResponseEntity.ok(updatedBoard);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        try {
            this.boardService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
