package org.example.Notifications;

import org.example.Chat.WebSocketController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private WebSocketController webSocketController;

    @Autowired
    private NotificationRepository notificationRepository;
    public void notifyNewMessage(Notification notification) {
        webSocketController.sendNotification(notification);
    }

    public void notifyNewBarter(Notification notification) {
        webSocketController.sendNotification(notification);
    }
    public List<Notification> getNotificationsByUserId(Integer userId) {
        return notificationRepository.findByUserId(userId);
    }

    public void save(Notification newNotification) {
        notificationRepository.save(newNotification);
    }
}
