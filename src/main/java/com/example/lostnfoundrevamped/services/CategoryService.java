package com.example.lostnfoundrevamped.services;

import com.example.lostnfoundrevamped.entities.CategoryEntity;
import com.example.lostnfoundrevamped.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    // Fetch all the categories to use them when we submit an item in the form.
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get a category by the id in order to then get the name of the category for the alerts.
    public CategoryEntity getCategoryById(Integer categoryid) {
        return categoryRepository.findByCategoryid(categoryid);
    }
}