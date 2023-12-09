package org.example.Notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping()
    public ResponseEntity<List<NotificationDTO>> getNotification(@RequestHeader Integer user_id){
        List<NotificationDTO> notificationDTOS = notificationRepository.findByUserId(user_id).stream()
                .map(NotificationDTO::mapToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(notificationDTOS, HttpStatus.OK);
    }
    @PatchMapping("/read")
    public ResponseEntity<List<NotificationDTO>> markAsCompleted(@RequestHeader Integer user_id){
        List<Notification> userNotifications = notificationRepository.findByUserId(user_id);
        userNotifications.forEach(notification -> notification.setStatus("READ"));
        notificationRepository.saveAll(userNotifications);
        List<NotificationDTO> notificationDTOS = userNotifications.stream()
                .map(NotificationDTO::mapToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(notificationDTOS, HttpStatus.OK);
    }
}

