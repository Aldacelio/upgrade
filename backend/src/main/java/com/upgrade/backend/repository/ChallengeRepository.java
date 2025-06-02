package com.upgrade.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upgrade.backend.model.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}