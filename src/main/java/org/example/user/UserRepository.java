package org.example.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    @Query(value = "SELECT u.* FROM Users u " +
            "JOIN UserChatRoom ucr ON u.user_id = ucr.user_id " +
            "WHERE ucr.room_id = :room_id AND u.user_id != :user_id", nativeQuery = true)
    User findOtherUserInChat(@Param("room_id") Integer roomId, @Param("user_id") Integer userId);
}
