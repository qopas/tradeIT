package org.example.Chat;

import org.example.Chat.Message.MessageDTO;
import org.example.Chat.Message.MessagePayload;
import org.example.Chat.Message.MessageService;
import org.example.Chat.Message.Messages;
import org.example.User.UserDTO;
import org.example.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
@Controller
@CrossOrigin
public class WebSocketController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/sendMessage/{id}")
    @SendTo("/topic/{id}")
    public MessageDTO sendMessage(@Payload MessagePayload message, @DestinationVariable("id") Integer id) {
        System.out.println("Received WebSocket message: " + message.toString());
        Messages saved = messageService.saveMessage(message, 1);
        UserDTO userDTO = UserDTO.fromUser(userRepository.findById(message.getSenderId()).get());
        MessageDTO m =  new MessageDTO(
                saved.getChatRoom().getId(),
                saved.getId(),
                userDTO,
                message.getMessage(),
                LocalDateTime.now()
        );
        return m;
    }
}