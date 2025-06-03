package com.upgrade.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upgrade.backend.model.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByUserIdAndFinishedFalse(Long userId);
    List<Challenge> findByUserIdAndFinishedTrue(Long userId);
}