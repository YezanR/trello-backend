package com.yezan.trello.service;

import com.yezan.trello.dto.user.UserCreateRequest;
import com.yezan.trello.entity.User;

public interface UserService {
    User signUp(UserCreateRequest user);
    boolean isSignedUp(String emailOrUsername);
    User findByUsername(String username);
}
