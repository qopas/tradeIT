package org.example.Images;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.example.Product.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImageController {
    @Autowired
    private ImageService imageService;
    @PostMapping("/add")
    public String handleFileUpload(@RequestParam("image") MultipartFile file, @RequestParam("product_id") Integer productId) {
       return imageService.uploadFile(file, productId);
    }
}
