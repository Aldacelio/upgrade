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
public class DailyProgressHistoryResponse {
    private LocalDate date;
    private String status; // "COMPLETADO" ou "VENCIDO"
    private Long challengeId;
}
