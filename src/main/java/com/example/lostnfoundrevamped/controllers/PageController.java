package com.example.lostnfoundrevamped.controllers;

import com.example.lostnfoundrevamped.services.ItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @Autowired
    ItemService itemService;

    // Login
    @GetMapping("/")
    public String getLoginPage() { return "login"; }

    // Register
    @GetMapping("/register")
    public String getRegisterPage() { return "register"; }

    // Home
    @GetMapping("/home")
    public String getHomePage(HttpSession session,
                              ModelMap modelMap) {
        if (session.getAttribute("loggedinuser") == null) {
            return "redirect:/";
        }
        //  Get all the items to later display them with images in the homepage.
        modelMap.addAttribute("allitems", itemService.getAllItems());
        return "/home";
    }

    // Choose Lost or Found
    @GetMapping("/choose")
    public String getChoosePage(HttpSession session) {
        if (session.getAttribute("loggedinuser") == null) {
            return "redirect:/";
        }
        return "/choose";
    }
}