package org.example.Product;

import org.example.Category.CategoryRepository;
import org.example.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.criteria.Predicate;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public Specification<Product> buildProductSpecification(Integer categoryId, String condition, Integer sellerId, String name) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (categoryId != null) {
                predicates.add(criteriaBuilder.equal(root.get("category").get("categoryId"), categoryId));
            }

            if (condition != null) {
                predicates.add(criteriaBuilder.equal(root.get("condition"), condition));
            }
            if (sellerId != null) {
                predicates.add(criteriaBuilder.equal(root.get("sellerId"), sellerId));
            }

            if (name != null) {
                predicates.add(criteriaBuilder.like(root.get("productName"), "%" + name + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
    public Map<String, Object> saveProduct(ProductRequest productRequest){
        Product product = new Product();
        product.setSeller(userRepository.findById(productRequest.getSeller_id()).orElse(null));
        product.setCategory(categoryRepository.findById(productRequest.getCategory_id()).orElse(null));
        product.setProductName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCondition(productRequest.getCondition());
        Product saved = productRepository.save(product);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product '" + productRequest.getName() + "' created successfully.");
        response.put("product_id", saved.getId());
        return response;
    }

}