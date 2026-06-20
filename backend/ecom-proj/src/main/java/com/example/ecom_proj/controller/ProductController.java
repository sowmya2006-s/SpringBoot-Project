package com.example.ecom_proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecom_proj.model.Product;
import com.example.ecom_proj.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {


@Autowired
private ProductService productService;

@RequestMapping("/")
public String welcome() {
    return "Welcome to Wonderful World";
}

@GetMapping
public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
}

@GetMapping("/product/{id}")
public ResponseEntity<Product> getProductById(
        @PathVariable Long id) {

    Product product = productService.getProductById(id);

    if (product == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(product);
}

@PostMapping("/product")
public ResponseEntity<?> addProduct(
        @RequestPart("product") Product product,
        @RequestPart("imageFile") MultipartFile imageFile) {

    try {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        Product savedProduct =
                productService.addProduct(product);

        return new ResponseEntity<>(
                savedProduct,
                HttpStatus.CREATED);

    } catch (Exception e) {

        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@GetMapping("/product/{id}/image")
public ResponseEntity<byte[]> getImageById(
        @PathVariable Long id) {

    Product product =
            productService.getProductById(id);

    if (product == null ||
            product.getImageData() == null) {

        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
            .contentType(
                    MediaType.parseMediaType(
                            product.getImageType()))
            .body(product.getImageData());
}


}
