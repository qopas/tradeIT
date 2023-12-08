package org.example.Chat.ChatRooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    ChatRoom findByRoomName(String roomName);
    @Query(value = "SELECT c.* FROM ChatRooms c " +
            "JOIN UserChatRoom ucr ON c.room_id = ucr.room_id " +
            "WHERE ucr.user_id = :user_id", nativeQuery = true)
    List<ChatRoom> findChatRoomsByUserId(@Param("user_id") Integer userId);
    @Query(value = "SELECT cr.* FROM ChatRooms cr " +
            "JOIN UserChatRoom ucr1 ON cr.room_id = ucr1.room_id " +
            "JOIN UserChatRoom ucr2 ON cr.room_id = ucr2.room_id " +
            "WHERE ucr1.user_id = :userId1 AND ucr2.user_id = :userId2", nativeQuery = true)
    List<ChatRoom> findChatRoomsByUserIds(@Param("userId1") Integer userId1,
                                          @Param("userId2") Integer userId2);
}