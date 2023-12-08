package org.example.Chat.UserChatRoom;

import org.example.Chat.ChatRooms.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserChatRoomRepository extends JpaRepository<UserChatRooms, Integer> {
    List<UserChatRooms> findByUserId(Integer userId);
    List<UserChatRooms> findByChatRoom(ChatRoom chatRoom);
    @Query(value = "SELECT uc.room_id " +
            "FROM UserChatRoom uc " +
            "WHERE (uc.user_id = :user1Id AND uc.room_id IN (SELECT room_id FROM UserChatRoom WHERE user_id = :user2Id)) " +
            "   OR (uc.user_id = :user2Id AND uc.room_id IN (SELECT room_id FROM UserChatRoom WHERE user_id = :user1Id))",
            nativeQuery = true)
    Optional<Integer> findRoomIdByUserIds(@Param("user1Id") Integer user1Id, @Param("user2Id") Integer user2Id);
}
