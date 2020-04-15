package com.yezan.trello.security;

import com.yezan.trello.entity.User;
import com.yezan.trello.security.exception.UserNotAuthenticatedException;
import com.yezan.trello.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class AuthImpl implements Auth {

    private UserService userService;

    public AuthImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User getUser() {
        Authentication authentication = this.getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();
            try {
                return this.userService.findByUsername(username);
            }
            catch (EntityNotFoundException e) {
                throw new UserNotAuthenticatedException("User '" + username + "' not found, hence not authenticated");
            }
        }
        else {
            throw new UserNotAuthenticatedException("User is not authenticated");
        }
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
