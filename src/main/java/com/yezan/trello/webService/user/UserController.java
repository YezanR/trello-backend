package com.yezan.trello.webService.user;

import com.yezan.trello.entity.User;
import com.yezan.trello.dto.user.UserCreateRequest;
import com.yezan.trello.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signUp")
    public User signUp(@RequestBody @Valid UserCreateRequest user) {
        return this.userService.signUp(user);
    }
}