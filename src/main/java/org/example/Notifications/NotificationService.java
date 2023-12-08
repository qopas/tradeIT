package org.example.Notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationController notificationController;

    public void notifyNewMessage(String message) {
        Notification notification = new Notification();
        notificationController.sendNotification(notification);
    }

    public void notifyNewBarter(String barter) {
        Notification notification = new Notification();
        notificationController.sendNotification(notification);
    }
}
