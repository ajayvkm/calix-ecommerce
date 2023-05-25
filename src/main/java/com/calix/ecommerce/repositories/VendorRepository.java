package com.calix.ecommerce.repositories;

import com.calix.ecommerce.models.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    Optional<Vendor> findByName(String name);
}
