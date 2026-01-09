package com.example.lostnfoundrevamped.controllers;

import com.example.lostnfoundrevamped.entities.AlertEntity;
import com.example.lostnfoundrevamped.entities.dto.UserDto;
import com.example.lostnfoundrevamped.repositories.AlertRepository;
import com.example.lostnfoundrevamped.services.AlertService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AlertController {
    @Autowired
    AlertRepository alertRepository;
    @Autowired
    AlertService alertService;

    // Alert mapping with all alerts for the logged-in user.
    @GetMapping("/alerts")
    public String getAlertPage(HttpSession session,
                               ModelMap mm) {
        UserDto user = (UserDto) session.getAttribute("loggedinuser");
        if (user == null) { return "redirect:/"; }

        // Make a list of all the alerts for the `loggedinuser` and add them with `mm`.
        List<AlertEntity> allAlerts = alertRepository.findAllByUserEntity_Username(user.getUsername());
        mm.addAttribute("alerts", allAlerts);

        return "/alerts";
    }

    // Set all alerts for a user to `true`.
    @PostMapping("/do-flip-alerts")
    public String flipAlerts(HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("loggedinuser");
        if (user == null) { return "redirect:/"; }
        // Set all the alerts to `true`.
        alertService.setAlertsTrue(user.getUsername());
        return "redirect:/alerts";
    }
}