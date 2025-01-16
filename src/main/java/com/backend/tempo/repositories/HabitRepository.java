package com.backend.tempo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.tempo.database.Habit;
import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Integer> {
    List<Habit> findByUserId(Integer userId);
    List<Habit> findByDashboardId(Integer dashboardId);
}