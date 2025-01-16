package com.backend.tempo.controllers;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.backend.tempo.services.HabitService;
import com.backend.tempo.database.Habit;

@Slf4j
@RestController
@RequestMapping("/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @PostMapping("/user/{userId}/dashboard/{dashboardId}")
    public ResponseEntity<Habit> createHabit(
            @PathVariable Integer userId,
            @PathVariable Integer dashboardId,
            @Valid @RequestBody Habit habit) {
        log.info("Creating habit for user: {} in dashboard: {}", userId, dashboardId);
        Habit createdHabit = habitService.createHabit(habit, userId, dashboardId);
        if (createdHabit != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdHabit);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public List<Habit> getHabitsByUserId(@PathVariable Integer userId) {
        log.info("Getting habits for user: {}", userId);
        return habitService.getHabitsByUserId(userId);
    }

    @GetMapping("/dashboard/{dashboardId}")
    public List<Habit> getHabitsByDashboardId(@PathVariable Integer dashboardId) {
        log.info("Getting habits for dashboard: {}", dashboardId);
        return habitService.getHabitsByDashboardId(dashboardId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Integer id) {
        log.info("Getting habit by id: {}", id);
        Habit habit = habitService.getHabitById(id);
        if (habit != null) {
            return ResponseEntity.ok(habit);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Integer id, @Valid @RequestBody Habit habit) {
        log.info("Updating habit with id: {}", id);
        Habit updatedHabit = habitService.updateHabit(id, habit);
        if (updatedHabit != null) {
            return ResponseEntity.ok(updatedHabit);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Integer id) {
        log.info("Deleting habit with id: {}", id);
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }
}