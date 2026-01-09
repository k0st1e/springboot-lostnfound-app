package com.example.lostnfoundrevamped.repositories;

import com.example.lostnfoundrevamped.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByCategoryid(Integer categoryid);
}