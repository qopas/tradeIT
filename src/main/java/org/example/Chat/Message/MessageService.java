package org.example.Chat.Message;

import org.example.Chat.ChatRooms.ChatRoom;
import org.example.Chat.ChatRooms.ChatRoomRepository;
import org.example.Chat.UserChatRoom.UserChatRoomRepository;
import org.example.Chat.UserChatRoom.UserChatRooms;
import org.example.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final UserChatRoomRepository userChatRoomRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatRoomRepository chatRoomRepository, UserRepository userRepository,UserChatRoomRepository userChatRoomRepository) {
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
        this.userChatRoomRepository = userChatRoomRepository;
    }

    public Messages saveMessage(MessagePayload message, Integer reciverId) {
        ChatRoom chatRoom;
        if (message.getId() == -1) {
            chatRoom = new ChatRoom();
            chatRoom.setRoomName("chat");
            ChatRoom c = chatRoomRepository.save(chatRoom);
            UserChatRooms userChatRoom1 = UserChatRooms.builder()
                    .user(userRepository.findById(message.getSenderId()).get())
                    .chatRoom(c)
                    .build();
            UserChatRooms userChatRoom2 = UserChatRooms.builder()
                    .user(userRepository.findById(reciverId).get())
                    .chatRoom(c)
                    .build();
            userChatRoomRepository.save(userChatRoom1);
            userChatRoomRepository.save(userChatRoom2);
        }
        else {
            chatRoom =  chatRoomRepository.findById(message.getId()).orElse(null);
        }
        Messages m = new Messages();
        m.setChatRoom(chatRoom);
        m.setMessageText(message.getMessage());
        m.setSender(userRepository.findById(message.getSenderId()).get());
        System.out.println(userRepository.findById(message.getSenderId()).get());
        m.setTimestamp(LocalDateTime.now());
        messageRepository.save(m);
        return m;
    }
    public List<MessageDTO> getMessagesForRoom(Integer roomId) {
        return messageRepository.findByChatRoom_IdOrderByTimestamp(roomId)
                .stream()
                .map(MessageDTO::mapFromMessage)
                .collect(Collectors.toList());
    }
}