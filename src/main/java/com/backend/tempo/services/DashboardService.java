package com.backend.tempo.services;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.tempo.repositories.DashboardRepository;
import com.backend.tempo.repositories.UserRepository;
import com.backend.tempo.database.Dashboard;
import com.backend.tempo.database.User;

@Slf4j
@Service
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private UserRepository userRepository;

    public Dashboard createDashboard(Dashboard dashboard, Integer userId) {
        log.info("Creating dashboard for user: {}", userId);
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            dashboard.setUser(user);
            return dashboardRepository.save(dashboard);
        }
        return null;
    }

    public List<Dashboard> getDashboardsByUserId(Integer userId) {
        log.info("Getting dashboards for user: {}", userId);
        return dashboardRepository.findByUserId(userId);
    }

    public Dashboard getDashboardById(Integer id) {
        log.info("Getting dashboard by id: {}", id);
        return dashboardRepository.findById(id).orElse(null);
    }

    public Dashboard updateDashboard(Integer id, Dashboard dashboard) {
        log.info("Updating dashboard with id: {}", id);
        Dashboard existingDashboard = getDashboardById(id);
        if (existingDashboard != null) {
            existingDashboard.setName(dashboard.getName());
            return dashboardRepository.save(existingDashboard);
        }
        return null;
    }

    public void deleteDashboard(Integer id) {
        log.info("Deleting dashboard with id: {}", id);
        dashboardRepository.deleteById(id);
    }
}