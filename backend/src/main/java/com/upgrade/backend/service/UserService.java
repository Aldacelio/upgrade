package com.upgrade.backend.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upgrade.backend.dto.UserStatsResponse;
import com.upgrade.backend.model.Challenge;
import com.upgrade.backend.model.User;
import com.upgrade.backend.repository.ChallengeRepository;
import com.upgrade.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

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

    @Transactional(readOnly = true)
    public List<Challenge> getUserChallengeHistory(Long userId) {
        getUser(userId);
        return challengeRepository.findByUserIdAndFinishedTrue(userId);
    }
}