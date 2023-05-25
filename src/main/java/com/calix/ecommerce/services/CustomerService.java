package com.calix.ecommerce.services;

import com.calix.ecommerce.models.entity.Customer;
import com.calix.ecommerce.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {
    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerRepository customerRepository;

    public Optional<Customer> getCustomer(final String email) {
        return customerRepository.findByEmail(email);
    }

    public void addCustomer(final String email, final String name, final String category) {
        try {
            Customer customer = new Customer();
            customer.setName(name);
            customer.setEmail(email);
            customer.setCategory(category);
            Customer _customer = customerRepository.save(customer);
            logger.info("New account added to the database {}", _customer.getId());
        } catch (Exception e) {

        }
    }

    public List<Customer> saveAllCustomer(Set<Customer> customerSet) {
        List<Customer> customers = customerRepository.saveAll(customerSet);
        return customers;
    }
}
