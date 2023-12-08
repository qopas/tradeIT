package org.example.Chat.ChatRooms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Chat.Message.MessageDTO;
import org.example.User.UserDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomDTO {
    private Integer id;
    private UserDTO targetUser;
    private boolean isRead;
    private List<MessageDTO> messages;

}