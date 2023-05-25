package com.calix.ecommerce.dto;

import lombok.Data;

@Data
public class CustomerRequest {
    private String email;
    private String name;
    private String category;
}
