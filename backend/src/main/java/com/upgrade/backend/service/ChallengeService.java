package com.upgrade.backend.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upgrade.backend.dto.ChallengeRequest;
import com.upgrade.backend.dto.ChallengeResponse;
import com.upgrade.backend.model.Challenge;
import com.upgrade.backend.model.DailyProgress;
import com.upgrade.backend.model.User;
import com.upgrade.backend.repository.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserService userService;

    @Transactional
    public ChallengeResponse createChallenge(ChallengeRequest request) {
        User currentUser = userService.getCurrentUser();
        
        Challenge challenge = new Challenge();
        challenge.setTitle(request.getTitle());
        challenge.setDescription(request.getDescription());
        challenge.setType(request.getType());
        challenge.setDurationInDays(request.getDurationInDays());
        challenge.setStartDate(request.getStartDate());
        challenge.setUser(currentUser);
        
        List<DailyProgress> progressList = new ArrayList<>();
        for (int i = 0; i < request.getDurationInDays(); i++) {
            DailyProgress dailyProgress = new DailyProgress();
            dailyProgress.setDate(request.getStartDate().plusDays(i));
            dailyProgress.setCompleted(false);
            dailyProgress.setChallenge(challenge);
            progressList.add(dailyProgress);
        }
        challenge.setProgressList(progressList);
        
        Challenge savedChallenge = challengeRepository.save(challenge);
        return mapToChallengeResponse(savedChallenge);
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

    public List<Challenge> getActiveChallengesByUser(Long userId) {
        return challengeRepository.findByUserIdAndFinishedFalse(userId);
    }

    private ChallengeResponse mapToChallengeResponse(Challenge challenge) {
        return ChallengeResponse.builder()
                .id(challenge.getId())
                .title(challenge.getTitle())
                .description(challenge.getDescription())
                .type(challenge.getType())
                .durationInDays(challenge.getDurationInDays())
                .startDate(challenge.getStartDate())
                .finished(challenge.isFinished())
                .build();
    }
}