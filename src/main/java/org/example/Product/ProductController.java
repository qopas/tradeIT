package org.example.Product;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductRepository productRepository;

    private final  ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getProducts(
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false) String condition,
            @RequestParam(required = false) Integer seller,
            @RequestParam(required = false) String name
    ) {
        Specification<Product> specification = productService.buildProductSpecification(category, condition, seller, name);
        List<Product> products = productRepository.findAll(specification);
        List<ProductDTO> productDTOs = products.stream()
                .map(ProductDTO::fromProduct)
                .collect(Collectors.toList());

        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }
    @GetMapping("/{product_id}")
    public ResponseEntity<List<ProductDTO>> getProducts(
            @PathVariable Integer product_id
    ){
        Optional<Product> product = productRepository.findById(product_id);
        List<ProductDTO> p = product.stream().map(ProductDTO::fromProduct).toList();
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.saveProduct(productRequest), HttpStatus.CREATED);
    }
}