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

        return new ResponseEntity<>(productService.getProducts(category, condition, seller, name), HttpStatus.OK);
    }
    @GetMapping("/{product_id}")
    public ResponseEntity<ProductDTO> getProducts(
            @PathVariable Integer product_id
    ){
        Optional<Product> product = productRepository.findById(product_id);
        ProductDTO productDTO = productService.mapProductToDTO(product.get());
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> createProduct(@ModelAttribute  ProductRequest productRequest) {
        return new ResponseEntity<>(productService.saveProduct(productRequest), HttpStatus.CREATED);
    }
}