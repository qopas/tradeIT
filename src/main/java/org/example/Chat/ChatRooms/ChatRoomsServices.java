package org.example.Chat.ChatRooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomsServices {
    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomsServices(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public List<ChatRoom> getChatRoomsByUserId(Integer userId) {
        return chatRoomRepository.findChatRoomsByUserId(userId);
    }
}