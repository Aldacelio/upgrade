package com.upgrade.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upgrade.backend.dto.ChallengeRequest;
import com.upgrade.backend.model.Challenge;
import com.upgrade.backend.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<Challenge> createChallenge(@RequestBody ChallengeRequest request) {
        Challenge newChallenge = challengeService.createChallenge(request);
        return ResponseEntity.ok(newChallenge);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable Long id) {
        Challenge challenge = challengeService.getChallenge(id);
        return ResponseEntity.ok(challenge);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Challenge> updateChallenge(@PathVariable Long id, @RequestBody ChallengeRequest request) {
        Challenge updatedChallenge = challengeService.updateChallenge(id, request);
        return ResponseEntity.ok(updatedChallenge);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {
        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Challenge>> getActiveChallengesByUser(@PathVariable Long userId) {
        List<Challenge> challenges = challengeService.getActiveChallengesByUser(userId);
        return ResponseEntity.ok(challenges);
    }
}