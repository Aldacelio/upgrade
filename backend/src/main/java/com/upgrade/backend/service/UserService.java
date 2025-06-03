package com.upgrade.backend.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upgrade.backend.model.User;
import com.upgrade.backend.repository.UserRepository;
import com.upgrade.backend.dto.UserStatsResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional(readOnly = true)
    public UserStatsResponse getUserStats(Long id) {
        User user = getUser(id);
        return UserStatsResponse.builder()
                .level(user.getLevel())
                .points(user.getPoints())
                .build();
    }
}