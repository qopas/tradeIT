package org.example.Barter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BarterRequest {
    private Integer offered_product_id;
    private Integer desired_product_id;
    private String message;
}
