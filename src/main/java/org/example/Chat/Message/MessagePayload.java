package org.example.Chat.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessagePayload {
    private Integer id;
    private String message;
    private Integer senderId;
    private Integer targetUserId;
}