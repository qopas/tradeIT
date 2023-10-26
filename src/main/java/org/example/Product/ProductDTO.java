package org.example.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Category.Category;
import org.example.User.UserDTO;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private UserDTO seller;
    private Category category;
    private String productName;
    private String description;
    private String condition;
    private String targetProducts;
    private String details;
    private String status;
    private List<String> imageURL;


}
