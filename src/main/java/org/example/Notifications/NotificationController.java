package org.example.Notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/sendNotification")
    public void sendNotification(@RequestBody Notification notification) {
        // Process the notification and send it to the "/topic/notification" channel
        messagingTemplate.convertAndSend("/topic/notification", notification);
    }
}

