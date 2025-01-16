package com.backend.tempo.services;

import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.tempo.repositories.HabitCompletionRepository;
import com.backend.tempo.repositories.HabitRepository;
import com.backend.tempo.database.HabitCompletion;
import com.backend.tempo.database.Habit;

@Slf4j
@Service
public class HabitCompletionService {

    @Autowired
    private HabitCompletionRepository habitCompletionRepository;

    @Autowired
    private HabitRepository habitRepository;

    public HabitCompletion createCompletion(Integer habitId, LocalDate date, boolean completed, String notes) {
        log.info("Creating completion for habit: {} on date: {}", habitId, date);
        Habit habit = habitRepository.findById(habitId).orElse(null);
        if (habit != null) {
            HabitCompletion completion = new HabitCompletion();
            completion.setHabit(habit);
            completion.setDate(date);
            completion.setCompleted(completed);
            completion.setNotes(notes);
            return habitCompletionRepository.save(completion);
        }
        return null;
    }

    public List<HabitCompletion> getCompletionsByHabitId(Integer habitId) {
        log.info("Getting completions for habit: {}", habitId);
        return habitCompletionRepository.findByHabitId(habitId);
    }

    public HabitCompletion updateCompletion(Integer habitId, LocalDate date, boolean completed, String notes) {
        log.info("Updating completion for habit: {} on date: {}", habitId, date);
        HabitCompletion completion = habitCompletionRepository.findByHabitIdAndDate(habitId, date);
        if (completion != null) {
            completion.setCompleted(completed);
            completion.setNotes(notes);
            return habitCompletionRepository.save(completion);
        }
        return createCompletion(habitId, date, completed, notes);
    }
}