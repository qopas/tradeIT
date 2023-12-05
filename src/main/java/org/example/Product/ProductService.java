package org.example.Product;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.http.Method;
import org.example.Category.CategoryRepository;
import org.example.City.CityRepository;
import org.example.Images.Images;
import org.example.Images.ImagesRepository;
import org.example.User.User;
import org.example.User.UserDTO;
import org.example.User.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import jakarta.persistence.criteria.Predicate;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ImagesRepository imagesRepository;
    public Specification<Product> buildProductSpecification(Integer categoryId, String condition, Integer seller, String name) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (categoryId != null) {
                predicates.add(criteriaBuilder.equal(root.get("category").get("categoryId"), categoryId));
            }

            if (condition != null) {
                predicates.add(criteriaBuilder.equal(root.get("condition"), condition));
            }
            if (seller != null) {
                predicates.add(criteriaBuilder.equal(root.get("seller").get("id"), seller));
            }

            if (name != null) {
                predicates.add(criteriaBuilder.like(root.get("productName"), "%" + name + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
    public Map<String, Object> saveProduct(@NotNull ProductRequest productRequest){
        try {
            Product product = new Product();
            product.setSeller(userRepository.findById(productRequest.getSeller_id()).get());
            product.setCategory(categoryRepository.findById(productRequest.getCategory_id()).get());
            product.setProductName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setCondition(productRequest.getCondition());
            product.setDetails(productRequest.getDetails());
            product.setTarget_products(productRequest.getTargetProducts());
            product.setStatus("Available");
            product.setCity(cityRepository.findById(productRequest.getCity_id()).get());
            Product saved = productRepository.save(product);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Product '" + productRequest.getName() + "' created successfully.");
            response.put("product_id", saved.getId());
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ProductDTO mapProductToDTO(@NotNull Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        User seller = product.getSeller();
        UserDTO sellerInfo = new UserDTO(seller.getId(), seller.getFirstName() + " " + seller.getLastName(), seller.getUsername(), seller.getEmail());
        dto.setSeller(sellerInfo);
        dto.setProductName(product.getProductName());
        dto.setCategory(product.getCategory());
        dto.setDetails(product.getDetails());
        dto.setTargetProducts(product.getTarget_products());
        dto.setStatus(product.getStatus());
        List<String> imgUrls = imagesRepository.findByProductIdId(product.getId())
                .stream()
                .map(Images::getImage_url)
                .collect(Collectors.toList());
        dto.setImageURL(imgUrls);
        dto.setDescription(product.getDescription());
        dto.setCondition(product.getCondition());
        dto.setCity(product.getCity());
        return dto;
    }
    public  List<ProductDTO> getProducts(Integer category, String condition, Integer seller, String name){
        Specification<Product> specification = buildProductSpecification(category, condition, seller, name);
        List<Product> products = productRepository.findAll(specification);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product product: products){
            productDTOs.add(mapProductToDTO(product));
        }
        return productDTOs;
    }

}