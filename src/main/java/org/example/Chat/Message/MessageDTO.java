package org.example.Chat.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private Integer messageId;
    private Integer senderId;

    private String sender;

    private String messageText;

    private LocalDateTime timestamp;

    public static MessageDTO mapFromMessage(Messages message){
        return new MessageDTO(
                message.getId(),
                message.getSender().getId(),
                message.getSender().getUsername(),
                message.getMessageText(),
                message.getTimestamp()
        );
    }
}