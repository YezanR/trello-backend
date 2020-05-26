package com.yezan.trello.webService.board;

import com.yezan.trello.dto.request.ShareRequest;
import com.yezan.trello.entity.User;
import com.yezan.trello.security.Auth;
import com.yezan.trello.service.BoardShareService;
import com.yezan.trello.service.UserService;
import com.yezan.trello.service.exception.BoardShareException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/shareRequests")
public class BoardShareController {

    private final UserService userService;
    private final BoardShareService boardShareService;
    private final Auth auth;

    public BoardShareController(
            UserService userService,
            BoardShareService boardService,
            Auth auth) {
        this.userService = userService;
        this.boardShareService = boardService;
        this.auth = auth;
    }

    @PostMapping
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

    @PostMapping("{shareId}/accept")
    public ResponseEntity<HttpStatus> accept(@PathVariable int shareId) {
        try {
            this.boardShareService.accept(shareId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public List<com.yezan.trello.dto.response.ShareRequest> findAllRequests() {
        List<com.yezan.trello.entity.ShareRequest> requests = this.boardShareService.findAllRequests(auth.getUser());
        List<com.yezan.trello.dto.response.ShareRequest> requestResponses = new ArrayList<>();
        requests.forEach((request) -> {
                requestResponses.add(com.yezan.trello.dto.response.ShareRequest.fromEntity(request));
            });
        return requestResponses;
    }
}
