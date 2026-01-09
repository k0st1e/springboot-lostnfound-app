package com.example.lostnfoundrevamped.repositories;

import com.example.lostnfoundrevamped.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
}