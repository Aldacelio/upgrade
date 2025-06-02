package com.upgrade.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upgrade.backend.dto.ChallengeRequest;
import com.upgrade.backend.model.Challenge;
import com.upgrade.backend.model.User;
import com.upgrade.backend.repository.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserService userService;

    @Transactional
    public Challenge createChallenge(ChallengeRequest request) {
        User currentUser = userService.getCurrentUser();
        
        Challenge challenge = new Challenge();
        challenge.setTitle(request.getTitle());
        challenge.setDescription(request.getDescription());
        challenge.setType(request.getType());
        challenge.setDurationInDays(request.getDurationInDays());
        challenge.setStartDate(request.getStartDate());
        challenge.setUser(currentUser);
        
        return challengeRepository.save(challenge);
    }
}