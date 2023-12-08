package org.example.Notifications;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Barter.Barter;
import org.example.User.User;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "proposal_id", referencedColumnName = "proposal_id")
    private Barter barter_id;

    @Column(name = "message", columnDefinition = "nvarchar(MAX)")
    private String message;

    @Column(name = "status", length = 15)
    private String status;

    @Column(name = "timestamp")
    private LocalDate timestamp;

}
