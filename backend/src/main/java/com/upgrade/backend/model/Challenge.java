package com.upgrade.backend.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Data;

@Entity
@Data
public class Challenge {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private String type; // saúde, estudo, leitura, etc.
    private int durationInDays;
    private LocalDate startDate;

    private boolean finished = false;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<DailyProgress> progressList;
}