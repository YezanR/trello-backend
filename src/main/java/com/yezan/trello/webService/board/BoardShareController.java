package com.yezan.trello.webService.board;

import com.yezan.trello.dto.response.shareRequest.ShareRequestDTO;
import com.yezan.trello.entity.ShareRequest;
import com.yezan.trello.entity.User;
import com.yezan.trello.security.Auth;
import com.yezan.trello.service.BoardShareService;
import com.yezan.trello.service.UserService;
import com.yezan.trello.service.exception.BoardShareException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/shareRequests")
public class BoardShareController {

    private final UserService userService;
    private final BoardShareService boardShareService;
    private final Auth auth;

    @Autowired
    private ModelMapper modelMapper;

    public BoardShareController(
            UserService userService,
            BoardShareService boardShareService,
            Auth auth) {
        this.userService = userService;
        this.boardShareService = boardShareService;
        this.auth = auth;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> request(@RequestBody com.yezan.trello.dto.request.ShareRequest request) {
        try {
            User withUser = this.userService.findByUsername(request.getUsername());
            this.boardShareService.request(request.getBoardId(), withUser, auth.getUser());
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
            this.boardShareService.accept(shareId, auth.getUser());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (BoardShareException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @GetMapping
    public List<ShareRequestDTO> findAllRequests() {
        List<ShareRequest> requests = this.boardShareService.findAllRequests(auth.getUser());
        return requests
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private ShareRequestDTO convertToResponse(ShareRequest request) {
        return modelMapper.map(request, ShareRequestDTO.class);
    }
}
