package com.calix.ecommerce.services;

import com.calix.ecommerce.models.entity.Product;
import com.calix.ecommerce.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {
    Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepository repository;

    public Set<Product> getProducts(String productNames) {
        Set<Product> productInfoSet = new HashSet<>();
        Set<String> productNamesSet = Stream.of(productNames.split("\\|"))
                .collect(Collectors.toSet());

        productNamesSet.stream().forEach(sku -> {
            Optional<Product> productOptional = repository.getBySkuName(sku);
            Product product = productOptional.isPresent() ? productOptional.get() : new Product(sku, sku);
            productInfoSet.add(product);
        });

        return productInfoSet;
    }

}
