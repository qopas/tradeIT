package org.example.Barter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Product.Product;
import org.example.User.User;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Barter")
public class Barter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proposal_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "offeredProduct_id")
    private Product offeredId;
    @ManyToOne
    @JoinColumn(name = "requestedProduct_id")
    private Product requestedId;
    @Column(name = "status")
    private String status;
    @Column(name = "initialize_at")
    private LocalDate initialize_at;
    @Column(name = "last_updated")
    private LocalDate last_updated;
    @Column(name = "message")
    private String message;
    @Column(name = "first_to_complete_id")
    private Integer first_to_complete_id;
}
