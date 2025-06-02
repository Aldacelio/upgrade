package com.upgrade.backend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ChallengeRequest {
    private String title;
    private String description;
    private String type;
    private int durationInDays;
    private LocalDate startDate;
}