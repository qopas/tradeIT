package org.example.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO2 {

    private Integer productId;

    private String name;

    private List<String> imageURL;
    public static ProductDTO2 fromEntity(Product product) {
        List<String> imageURLs = product.getImageUrls();
        return ProductDTO2.builder()
                .productId(product.getId())
                .name(product.getProductName())
                .imageURL(imageURLs)
                .build();
    }

}
