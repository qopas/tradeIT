package org.example.TradeProposal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Product.Product;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeProposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proposal_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product offeredId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product requestedId;
    @Column(name = "status")
    private String status;

}
