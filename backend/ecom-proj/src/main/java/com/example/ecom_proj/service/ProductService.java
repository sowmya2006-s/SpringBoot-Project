package com.example.ecom_proj.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ecom_proj.repo.ProductRepo;
import com.example.ecom_proj.model.Product;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }


    // Service logic for product operations
}