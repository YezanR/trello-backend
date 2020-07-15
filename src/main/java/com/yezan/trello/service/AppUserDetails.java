package com.yezan.trello.service;

import org.springframework.security.core.userdetails.User;

public class AppUserDetails extends User {

    private com.yezan.trello.entity.User user;

    public AppUserDetails(com.yezan.trello.entity.User user) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
        this.user = user;
    }

    public com.yezan.trello.entity.User getUser() {
        return user;
    }
}
