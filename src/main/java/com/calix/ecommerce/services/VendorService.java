package com.calix.ecommerce.services;

import com.calix.ecommerce.models.entity.Vendor;
import com.calix.ecommerce.repositories.VendorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendorService {
    Logger logger = LoggerFactory.getLogger(VendorService.class);

    @Autowired
    VendorRepository repository;

    public Vendor getVendor(String verdorId) {
        Optional<Vendor> vendorByName = repository.findByName(verdorId);
        Vendor vendor = vendorByName.isPresent() ? vendorByName.get() : new Vendor(verdorId);
        return vendor;
    }
}
