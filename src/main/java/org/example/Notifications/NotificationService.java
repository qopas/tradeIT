package org.example.Notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationController notificationController;

    @Autowired
    private NotificationRepository notificationRepository;
    public void notifyNewMessage(Notification notification) {
        notificationController.sendNotification(notification);
    }

    public void notifyNewBarter(Notification notification) {
        notificationController.sendNotification(notification);
    }
    public List<Notification> getNotificationsByUserId(Integer userId) {
        return notificationRepository.findByUserId(userId);
    }

    public void save(Notification newNotification) {
        notificationRepository.save(newNotification);
    }
}
