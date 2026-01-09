package com.example.lostnfoundrevamped.services;

import com.example.lostnfoundrevamped.entities.AlertEntity;
import com.example.lostnfoundrevamped.entities.UserEntity;
import com.example.lostnfoundrevamped.entities.dto.NewAlertDto;
import com.example.lostnfoundrevamped.entities.enums.ItemStatus;
import com.example.lostnfoundrevamped.repositories.AlertRepository;
import com.example.lostnfoundrevamped.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    AlertRepository alertRepository;

    // Create alerts for interested users.
    // When we submit an item we already have valuable information.
    // Hit users(via items) that are of the same category but status is flipped.
    public void createAlertsForUsers(NewAlertDto dto) {
        // Get the status reversed.
        ItemStatus reversedStatus = dto.getItemStatus().equals("LOST") ?
                ItemStatus.FOUND : ItemStatus.LOST;

        // Find the users of the same category but with reversed status.
        List<UserEntity> users = itemRepository.findUsersToNotify(dto.getItemCategory(), reversedStatus);

        // Make a new empty place for alerts.
        List<AlertEntity> pouchOfAlerts = new ArrayList<>();

        // Iterate the `users`, create new alert entities and save them.
        for (UserEntity userEntity : users) {
            AlertEntity alertEntity = new AlertEntity();
            alertEntity.setUserEntity(userEntity);
            alertEntity.setIsSeen(false);
            alertEntity.setAlertMessage(dto.getAlertMessage());
            pouchOfAlerts.add(alertEntity);
        }
        alertRepository.saveAll(pouchOfAlerts);
    }

    // Make a list of `unreadAlerts`.
    // Find alerts by username and `isSeen: false`
    // For the `unreadAlerts` change each `alertEntity`...
    // ...using `-> alertEntity.setIsSeen(true)`.
    // Save the edited alerts to the DB.
    public void setAlertsTrue(String username) {
        List<AlertEntity> unreadAlerts = alertRepository.
                findByUserEntity_UsernameAndIsSeen(username, false);
            unreadAlerts.forEach(alertEntity -> alertEntity.setIsSeen(true));
        alertRepository.saveAll(unreadAlerts);
    }
}