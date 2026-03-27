package org.example.service;

import org.example.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final List<User> USERS = List.of(
            new User(1L, "admin", "admin123", "admin@example.com", "ADMIN"),
            new User(2L, "user",  "user123",  "user@example.com",  "USER")
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = USERS.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
//                .password("{noop}" + user.getPassword())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public Optional<User> findByUsername(String username) {
        return USERS.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }
}