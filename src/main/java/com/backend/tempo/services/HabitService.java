package com.backend.tempo.services;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.tempo.repositories.HabitRepository;
import com.backend.tempo.repositories.UserRepository;
import com.backend.tempo.repositories.DashboardRepository;
import com.backend.tempo.database.Habit;
import com.backend.tempo.database.User;
import com.backend.tempo.database.Dashboard;

@Slf4j
@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

    public Habit createHabit(Habit habit, Integer userId, Integer dashboardId) {
        log.info("Creating habit for user: {} in dashboard: {}", userId, dashboardId);
        User user = userRepository.findById(userId).orElse(null);
        Dashboard dashboard = dashboardRepository.findById(dashboardId).orElse(null);
        
        if (user != null && dashboard != null) {
            habit.setUser(user);
            habit.setDashboard(dashboard);
            return habitRepository.save(habit);
        }
        return null;
    }

    public List<Habit> getHabitsByUserId(Integer userId) {
        log.info("Getting habits for user: {}", userId);
        return habitRepository.findByUserId(userId);
    }

    public List<Habit> getHabitsByDashboardId(Integer dashboardId) {
        log.info("Getting habits for dashboard: {}", dashboardId);
        return habitRepository.findByDashboardId(dashboardId);
    }

    public Habit getHabitById(Integer id) {
        log.info("Getting habit by id: {}", id);
        return habitRepository.findById(id).orElse(null);
    }

    public Habit updateHabit(Integer id, Habit habit) {
        log.info("Updating habit with id: {}", id);
        Habit existingHabit = getHabitById(id);
        if (existingHabit != null) {
            existingHabit.setName(habit.getName());
            existingHabit.setDescription(habit.getDescription());
            return habitRepository.save(existingHabit);
        }
        return null;
    }

    public void deleteHabit(Integer id) {
        log.info("Deleting habit with id: {}", id);
        habitRepository.deleteById(id);
    }
}