package com.example.ecom_proj.repo;
import com.example.ecom_proj.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
