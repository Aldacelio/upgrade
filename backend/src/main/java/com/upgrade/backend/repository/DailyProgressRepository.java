package com.upgrade.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.upgrade.backend.model.DailyProgress;

public interface DailyProgressRepository extends JpaRepository<DailyProgress, Long> {
    
    @Query("SELECT dp FROM DailyProgress dp WHERE dp.challenge.user.id = :userId AND (dp.completed = true OR dp.date < CURRENT_DATE)")
    List<DailyProgress> findUserProgressHistory(@Param("userId") Long userId);
}
