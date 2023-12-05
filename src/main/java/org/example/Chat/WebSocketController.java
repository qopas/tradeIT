package org.example.Chat;

import org.example.Chat.Message.MessageDTO;
import org.example.Chat.Message.MessagePayload;
import org.example.Chat.Message.MessageService;
import org.example.Chat.Message.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
@Controller
@CrossOrigin
public class WebSocketController {
    @Autowired
    private MessageService messageService;


    @MessageMapping("/sendMessage/{roomID}")
    @SendTo("/topic/{roomID}")
    public MessageDTO sendMessage(@Payload MessagePayload message) {
        Messages saved = messageService.saveMessage(message);
        return new MessageDTO(
                saved.getId(),
                message.getSenderId(),
                null,
                message.getMessage(),
                LocalDateTime.now()
        );
    }
}