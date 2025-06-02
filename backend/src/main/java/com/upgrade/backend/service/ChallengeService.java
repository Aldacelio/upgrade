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

    public Challenge getChallenge(Long id) {
        return challengeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Desafio não encontrado"));
    }

    @Transactional
    public Challenge updateChallenge(Long id, ChallengeRequest request) {
        Challenge challenge = getChallenge(id);
        
        User currentUser = userService.getCurrentUser();
        if (!challenge.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Não autorizado a atualizar este desafio");
        }

        challenge.setTitle(request.getTitle());
        challenge.setDescription(request.getDescription());
        challenge.setType(request.getType());
        challenge.setDurationInDays(request.getDurationInDays());
        challenge.setStartDate(request.getStartDate());
        
        return challengeRepository.save(challenge);
    }

    @Transactional
    public void deleteChallenge(Long id) {
        Challenge challenge = getChallenge(id);
        
        User currentUser = userService.getCurrentUser();
        if (!challenge.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Não autorizado a deletar este desafio");
        }

        challengeRepository.delete(challenge);
    }
}