package com.upgrade.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}