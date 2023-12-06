package org.example.Product;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.example.City.City;
import org.example.Images.Images;
import org.example.User.User;
import org.example.Category.Category;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "user_id")
    private User seller;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "condition")
    private String condition;

    @Column(name = "targetProducts")
    private String targetProducts;

    @Column(name = "details")
    private String details;

    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "city")
    private City city;

    @Transient
    private List<Images> images;
    public List<String> getImageUrls() {
        return images.stream()
                .map(Images::getImage_url)
                .collect(Collectors.toList());
    }


}
