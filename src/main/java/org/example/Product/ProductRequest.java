package org.example.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private Integer seller_id;
    private String name;
    private Integer category_id;
    private String description;
    private String details;
    private String condition;
    private MultipartFile file;
}
