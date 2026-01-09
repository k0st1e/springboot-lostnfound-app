package com.example.lostnfoundrevamped.services;

import com.example.lostnfoundrevamped.entities.MessageEntity;
import com.example.lostnfoundrevamped.entities.dto.InsertMessageDto;
import com.example.lostnfoundrevamped.repositories.ItemRepository;
import com.example.lostnfoundrevamped.repositories.MessageRepository;
import com.example.lostnfoundrevamped.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    private MessageRepository messageRepository;

    // Convert message to entity.
    public MessageEntity convertDtoToEntity(InsertMessageDto dto) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessagecontent(dto.getMessagecontent());
        messageEntity.setUserEntity(userRepository.getReferenceById(dto.getUser_entity_username()));
        messageEntity.setItemEntity(itemRepository.getReferenceById(dto.getItem_entity_itemid()));
        return messageEntity;
    }

    // Save msg to db.
    public void saveNewMessage(MessageEntity messageEntity) { messageRepository.save(messageEntity); }

    // Get all messages by a specific item id.
    public List<MessageEntity> getAllItemsById(int itemid) {
        return messageRepository.findAllByItemEntity_Itemid(itemid);
    }
}