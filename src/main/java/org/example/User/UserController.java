package org.example.User;

import org.example.Barter.BarterDTO;
import org.example.Barter.BarterService;
import org.example.Notifications.Notification;
import org.example.Notifications.NotificationDTO;
import org.example.Notifications.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BarterService barterService;
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(
            @PathVariable Integer user_id
    ){
        return new ResponseEntity<>(userRepository.findById(user_id).orElse(null), HttpStatus.OK);
    }
    @GetMapping("/{user_id}/barters")
    public ResponseEntity<List<BarterDTO>> getBartersByUser(@PathVariable("user_id") Integer userId) {
        List<BarterDTO> userBarters = barterService.getBartersByUser(userId);

        if (userBarters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(userBarters, HttpStatus.OK);
        }
    }
    @GetMapping("/notification")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUserId(@RequestHeader Integer user_id) {
        List<NotificationDTO> notifications = notificationService.getNotificationsByUserId(user_id)
                .stream()
                .map(NotificationDTO::mapToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}
