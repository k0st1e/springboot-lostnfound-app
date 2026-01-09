package com.example.lostnfoundrevamped.controllers.advice;

import com.example.lostnfoundrevamped.entities.AlertEntity;
import com.example.lostnfoundrevamped.entities.dto.UserDto;
import com.example.lostnfoundrevamped.repositories.AlertRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalAlertAdvice {
    @Autowired
    private AlertRepository alertRepository;

    // Global `hasUnreadAlerts` attribute for the navbar so we can color it orange when we need to.
    @ModelAttribute("hasUnreadAlerts")
    public boolean hasUnreadAlerts(HttpSession session) {

        // Get the user from the session.
        UserDto user = (UserDto) session.getAttribute("loggedinuser");

        // Return false if user is null.
        if (user == null) return false;

        // Find all unread alerts for the `loggedinuser` with params: `user.getUsername() & `isSeen: false`.
        List<AlertEntity> unreadAlerts = alertRepository.
                findByUserEntity_UsernameAndIsSeen(user.getUsername(), false);

        // If `unreadAlerts` is !empty return true.
        return !unreadAlerts.isEmpty();
    }
}