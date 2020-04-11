package com.yezan.trello.webService.user;

import com.yezan.trello.entity.User;
import com.yezan.trello.dto.user.UserCreateRequest;
import com.yezan.trello.exception.UserAlreadySignedUpException;
import com.yezan.trello.security.AuthRequest;
import com.yezan.trello.security.JWTResponse;
import com.yezan.trello.security.JWTUtil;
import com.yezan.trello.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class UserController {

    final UserService userService;
    final AuthenticationManager authenticator;
    final UserDetailsService userDetailsService;
    final JWTUtil jwtUtil;

    public UserController(
            UserService userService,
            AuthenticationManager authenticator,
            UserDetailsService userDetailsService,
            JWTUtil jwtUtil) {
        this.userService = userService;
        this.authenticator = authenticator;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("signUp")
    public User signUp(@RequestBody @Valid UserCreateRequest user) {
        try {
            return this.userService.signUp(user);
        }
        catch (UserAlreadySignedUpException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PostMapping("signIn")
    public ResponseEntity<JWTResponse> signIn(@RequestBody AuthRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            this.authenticator.authenticate(authentication);
            final UserDetails userDetails = this.userDetailsService
                    .loadUserByUsername(username);
            final String token = this.jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JWTResponse(token));
        }
        catch (DisabledException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
        catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}