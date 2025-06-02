package com.upgrade.backend.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "challenge", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DailyProgress> progressList;
}