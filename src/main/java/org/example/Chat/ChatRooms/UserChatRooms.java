package org.example.Chat.ChatRooms;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.User.User;

@Entity
@Table(name = "UserChatRoom")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserChatRooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_chat_room_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private ChatRoom chatRoom;
}