package org.example.Notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Barter.Barter;
import org.example.User.User;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {

    private Integer id;
    private String type;
    private Integer barter_id;
    private String message;
    private String status;
    private LocalDate timestamp;

    public static NotificationDTO mapToDTO(Notification notification) {
        return NotificationDTO.builder()
                .id(notification.getId())
                .type(notification.getType())
                .barter_id(notification.getBarter_id().getId())
                .message(notification.getMessage())
                .status(notification.getStatus())
                .timestamp(notification.getTimestamp())
                .build();
    }

}
