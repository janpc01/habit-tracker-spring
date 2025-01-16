package com.backend.tempo.controllers;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.backend.tempo.services.DashboardService;
import com.backend.tempo.database.Dashboard;

@Slf4j
@RestController
@RequestMapping("/dashboards")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Dashboard> createDashboard(@PathVariable Integer userId, @Valid @RequestBody Dashboard dashboard) {
        log.info("Creating dashboard for user: {}", userId);
        Dashboard createdDashboard = dashboardService.createDashboard(dashboard, userId);
        if (createdDashboard != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDashboard);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public List<Dashboard> getDashboardsByUserId(@PathVariable Integer userId) {
        log.info("Getting dashboards for user: {}", userId);
        return dashboardService.getDashboardsByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dashboard> getDashboardById(@PathVariable Integer id) {
        log.info("Getting dashboard by id: {}", id);
        Dashboard dashboard = dashboardService.getDashboardById(id);
        if (dashboard != null) {
            return ResponseEntity.ok(dashboard);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dashboard> updateDashboard(@PathVariable Integer id, @Valid @RequestBody Dashboard dashboard) {
        log.info("Updating dashboard with id: {}", id);
        Dashboard updatedDashboard = dashboardService.updateDashboard(id, dashboard);
        if (updatedDashboard != null) {
            return ResponseEntity.ok(updatedDashboard);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDashboard(@PathVariable Integer id) {
        log.info("Deleting dashboard with id: {}", id);
        dashboardService.deleteDashboard(id);
        return ResponseEntity.noContent().build();
    }
}