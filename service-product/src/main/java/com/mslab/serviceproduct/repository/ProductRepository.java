package com.mslab.serviceproduct.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mslab.serviceproduct.entity.Category;
import com.mslab.serviceproduct.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    public List<Product> findByCategory(Category category);
}
