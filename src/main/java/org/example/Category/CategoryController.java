package org.example.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    public CategoryRepository categoryRepository;
    @GetMapping()
    public ResponseEntity<List<Category>> getCategories(){
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }
}
