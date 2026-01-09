package com.example.lostnfoundrevamped.controllers;

import com.example.lostnfoundrevamped.entities.MessageEntity;
import com.example.lostnfoundrevamped.entities.dto.InsertMessageDto;
import com.example.lostnfoundrevamped.entities.dto.UserDto;
import com.example.lostnfoundrevamped.services.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping("/insertmessage/{itemid}")
    public String insertMessage(@ModelAttribute("NewMessageDto") InsertMessageDto dto,
                                @PathVariable("itemid") int itemid,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        // Set the item id and username.
        dto.setItem_entity_itemid(itemid);
        UserDto loggedInUser = (UserDto) session.getAttribute("loggedinuser");
        String username = loggedInUser.getUsername();
        dto.setUser_entity_username(username);

        // Convert to Entity and Save.
        MessageEntity messageEntity = messageService.convertDtoToEntity(dto);
        messageService.saveNewMessage(messageEntity);

        // Get all Messages for a specific item with `itemid`.
        List<MessageEntity> allMessages = messageService.getAllItemsById(itemid);
        redirectAttributes.addFlashAttribute("allMessages", allMessages);

        return "redirect:/item/" + itemid;
    }
}