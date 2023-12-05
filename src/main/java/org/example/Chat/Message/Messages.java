package org.example.Chat.Message;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Chat.ChatRooms.ChatRoom;
import org.example.User.Users;

import java.time.LocalDateTime;

@Entity
@Table(name = "Messages")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private Users sender;

    @Column(name = "messageText", nullable = false, columnDefinition = "TEXT")
    private String messageText;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
