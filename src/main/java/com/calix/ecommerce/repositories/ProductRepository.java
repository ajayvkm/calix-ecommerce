package com.calix.ecommerce.repositories;

import com.calix.ecommerce.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> getBySkuName(String skuName);
}
