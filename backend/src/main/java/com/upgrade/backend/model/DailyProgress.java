package com.upgrade.backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class DailyProgress {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;
    private boolean completed;

    @ManyToOne
    private Challenge challenge;
}