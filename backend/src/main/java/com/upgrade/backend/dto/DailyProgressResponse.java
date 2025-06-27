package com.upgrade.backend.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyProgressResponse {
    private Long id;
    private LocalDate date;
    private boolean completed;
    private String status;
}
