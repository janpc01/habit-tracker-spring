package com.backend.tempo.controllers;

import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.tempo.services.HabitCompletionService;
import com.backend.tempo.database.HabitCompletion;

@Slf4j
@RestController
@RequestMapping("/habit-completions")
public class HabitCompletionController {

    @Autowired
    private HabitCompletionService habitCompletionService;

    @PostMapping("/habit/{habitId}")
    public ResponseEntity<HabitCompletion> createCompletion(
            @PathVariable Integer habitId,
            @RequestParam LocalDate date,
            @RequestParam boolean completed,
            @RequestParam(required = false) String notes) {
        log.info("Creating completion for habit: {} on date: {}", habitId, date);
        HabitCompletion completion = habitCompletionService.createCompletion(habitId, date, completed, notes);
        if (completion != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(completion);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/habit/{habitId}")
    public List<HabitCompletion> getCompletionsByHabitId(@PathVariable Integer habitId) {
        log.info("Getting completions for habit: {}", habitId);
        return habitCompletionService.getCompletionsByHabitId(habitId);
    }

    @PutMapping("/habit/{habitId}")
    public ResponseEntity<HabitCompletion> updateCompletion(
            @PathVariable Integer habitId,
            @RequestParam LocalDate date,
            @RequestParam boolean completed,
            @RequestParam(required = false) String notes) {
        log.info("Updating completion for habit: {} on date: {}", habitId, date);
        HabitCompletion completion = habitCompletionService.updateCompletion(habitId, date, completed, notes);
        return ResponseEntity.ok(completion);
    }
}