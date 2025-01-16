package com.backend.tempo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.tempo.database.Dashboard;
import java.util.List;

public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {
    List<Dashboard> findByUserId(Integer userId);
}