package org.example.Chat.ChatRooms;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Chat.UserChatRoom.UserChatRooms;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "ChatRooms")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer id;

    @Column(name = "roomName", nullable = false)
    private String roomName;
    @OneToMany(mappedBy = "chatRoom")
    private Set<UserChatRooms> userChatRooms;


}