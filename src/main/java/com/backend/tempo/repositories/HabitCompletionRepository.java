package com.backend.tempo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.tempo.database.HabitCompletion;
import java.time.LocalDate;
import java.util.List;

public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Integer> {
    List<HabitCompletion> findByHabitId(Integer habitId);
    HabitCompletion findByHabitIdAndDate(Integer habitId, LocalDate date);
}