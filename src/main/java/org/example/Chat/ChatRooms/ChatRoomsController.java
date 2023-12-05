package org.example.Chat.ChatRooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/chatRooms")
public class ChatRoomsController {
    private final ChatRoomsServices chatRoomService;

    @Autowired
    public ChatRoomsController(ChatRoomsServices chatRoomService) {
        this.chatRoomService = chatRoomService;
    }
    @GetMapping("/{userId}")
    public List<ChatRoom> getChatRoomsByUserId(@PathVariable Integer userId) {
        return chatRoomService.getChatRoomsByUserId(userId);
    }
}