package com.calix.ecommerce.dto;

import com.calix.ecommerce.models.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String code;
    private Customer customer;
}
