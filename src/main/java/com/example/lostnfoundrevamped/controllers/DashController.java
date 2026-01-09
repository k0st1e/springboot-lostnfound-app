package com.example.lostnfoundrevamped.controllers;

import com.example.lostnfoundrevamped.entities.dto.CategoriesDto;
import com.example.lostnfoundrevamped.services.ItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashController {
    @Autowired
    ItemService itemService;
    @GetMapping("/dash")
    public String getDashPage(HttpSession session,
                              ModelMap modelMap) {

        if (session.getAttribute("loggedinuser") == null) {
            return "redirect:/";
        }

        // Make a list of `CategoriesDto` to use them for Chart.js
        List<CategoriesDto> items = itemService.getItemCountsPerCategory();
        modelMap.addAttribute("items", items);
        return "dash";
    }
}