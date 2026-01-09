package com.example.lostnfoundrevamped.repositories;

import com.example.lostnfoundrevamped.entities.AlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<AlertEntity, Integer> {
    // Fetch all alerts by username.
    List<AlertEntity> findAllByUserEntity_Username(String username);
    // Alerts by String `username` and boolean `isSeen`.
    List<AlertEntity> findByUserEntity_UsernameAndIsSeen(String username, boolean isSeen);
}