package org.example.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Category.Category;
import org.example.User.User;
import org.example.User.UserDTO;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private UserDTO sellerId;
    private Category category;
    private String productName;
    private String description;
    private String condition;


    public static ProductDTO fromProduct(Product product) {
        User seller = product.getSeller();
        UserDTO sellerInfo = new UserDTO(seller.getId(), seller.getFirstName() + " " + seller.getLastName(), seller.getUsername(), seller.getEmail());
        return new ProductDTO(
                product.getId(),
                sellerInfo,
                product.getCategory(),
                product.getProductName(),
                product.getDescription(),
                product.getCondition()
        );
    }
}
