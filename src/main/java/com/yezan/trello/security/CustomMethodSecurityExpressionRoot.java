package com.yezan.trello.security;

import com.yezan.trello.service.AppUserDetails;
import com.yezan.trello.service.BoardShareService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
        implements MethodSecurityExpressionOperations {

    private final BoardShareService shareService;

    public CustomMethodSecurityExpressionRoot(Authentication authentication, BoardShareService shareService) {
        super(authentication);
        this.shareService = shareService;
    }

    public boolean isBoardMember(int boardId) {
        AppUserDetails userDetails = (AppUserDetails) this.getPrincipal();
        return this.shareService.isMember(userDetails.getUser(), boardId);
    }

    @Override
    public void setFilterObject(Object o) {

    }

    @Override
    public Object getFilterObject() {
        return null;
    }

    @Override
    public void setReturnObject(Object o) {

    }

    @Override
    public Object getReturnObject() {
        return null;
    }

    @Override
    public Object getThis() {
        return null;
    }
}
