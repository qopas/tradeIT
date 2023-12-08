package org.example.Chat.ChatRooms;

import org.example.Chat.Message.MessageDTO;
import org.example.Chat.Message.MessageService;
import org.example.Chat.UserChatRoom.UserChatRoomRepository;
import org.example.User.User;
import org.example.User.UserDTO;
import org.example.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessageAware;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/chat")
public class ChatRoomsController {
    private final ChatRoomsServices chatRoomService;
    private final MessageService messageService;
    private final UserChatRoomRepository userChatRoomRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChatRoomsController(ChatRoomsServices chatRoomService, MessageService messageService,UserChatRoomRepository userChatRoomRepository,UserRepository userRepository) {
        this.chatRoomService = chatRoomService;
        this.messageService = messageService;
        this.userChatRoomRepository = userChatRoomRepository;
        this.userRepository = userRepository;
    }
    @GetMapping()
    public ResponseEntity<List<ChatRoomDTO>>getChatRoomsByUserId(@RequestHeader Integer user_id) {
        List<ChatRoomDTO> chatRoomDTOs = chatRoomService.getChatRoomsByUserId(user_id);
        return new ResponseEntity<>(chatRoomDTOs, HttpStatus.OK);
    }
    @GetMapping("/{target_user_id}")
    public ChatRoomDTO getMessagesForRoom(@PathVariable Integer target_user_id, @RequestHeader Integer user_id){
        Integer roomId =userChatRoomRepository.findRoomIdByUserIds(target_user_id,user_id).orElse(null);
        UserDTO targetUser = UserDTO.fromUser(userRepository.findById(target_user_id).get());
        ChatRoomDTO chatRoomDTO;
        if (roomId == null) {
            chatRoomDTO = ChatRoomDTO.builder()
                    .id(null)
                    .targetUser(targetUser)
                    .messages(null)
                    .build();
        }
        List<MessageDTO> messageDTOS = messageService.getMessagesForRoom(roomId);
        boolean isRead = false;
            chatRoomDTO =  ChatRoomDTO.builder()
                    .id(roomId)
                    .targetUser(targetUser)
                    .isRead(isRead)
                    .messages(messageDTOS)
                    .build();
            return chatRoomDTO;
    }


}