package com.example.lostnfoundrevamped.repositories;

import com.example.lostnfoundrevamped.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    List<MessageEntity> findAllByItemEntity_Itemid(Integer itemEntityItemid);
}