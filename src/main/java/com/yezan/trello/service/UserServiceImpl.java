package com.yezan.trello.service;

import com.yezan.trello.dto.request.user.UserCreateRequest;
import com.yezan.trello.entity.User;
import com.yezan.trello.exception.UserAlreadySignedUpException;
import com.yezan.trello.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signUp(UserCreateRequest request) {
        String email = request.getEmail();
        if (this.isSignedUp(email)) {
            throw new UserAlreadySignedUpException(
                    "User with email '" + email + "' has already signed up"
            );
        }

        String password = this.passwordEncoder.encode(request.getPassword());
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(password);
        user.setUsername(request.getUsername());
        return this.userRepository.save(user);
    }

    public boolean isSignedUp(String email) {
        return this.userRepository.findByEmail(email) != null;
    }

    public User findByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }
}
