package com.backend.tempo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.tempo.database.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}