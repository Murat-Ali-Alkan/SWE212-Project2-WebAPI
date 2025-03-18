package com.onlineshopping.project2restapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.onlineshopping.project2restapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
