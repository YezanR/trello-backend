package com.yezan.trello.webService.board;

import com.yezan.trello.dto.board.ShareRequest;
import com.yezan.trello.entity.User;
import com.yezan.trello.service.BoardShareService;
import com.yezan.trello.service.UserService;
import com.yezan.trello.service.exception.BoardShareException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api")
public class BoardShareController {

    private final UserService userService;
    private final BoardShareService boardShareService;

    public BoardShareController(
            UserService userService,
            BoardShareService boardService) {
        this.userService = userService;
        this.boardShareService = boardService;
    }

    @PostMapping("shareRequests")
    public ResponseEntity<HttpStatus> request(@RequestBody ShareRequest request) {
        try {
            User withUser = this.userService.findByUsername(request.getUsername());
            this.boardShareService.request(request.getBoardId(), withUser);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (BoardShareException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PostMapping("shareRequests/{shareId}/accept")
    public ResponseEntity<HttpStatus> accept(@PathVariable int shareId) {
        try {
            this.boardShareService.accept(shareId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
