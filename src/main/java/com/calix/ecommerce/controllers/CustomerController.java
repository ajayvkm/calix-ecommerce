package com.calix.ecommerce.controllers;

import com.calix.ecommerce.dto.CustomerRequest;
import com.calix.ecommerce.dto.CustomerResponse;
import com.calix.ecommerce.exceptions.ResourceNotFoundException;
import com.calix.ecommerce.models.entity.Customer;
import com.calix.ecommerce.services.CustomerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    Logger logger = LoggerFactory.getLogger(ImportController.class);
    @Autowired
    CustomerService customerService;

    @PostMapping("/")
    @Operation(summary = "API to add new customerRequest.")
    @ApiImplicitParam(name = "X-API", value = "X-API", dataType = "string", paramType = "header")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "created"),
            @ApiResponse(code = 500, message = "Failed saving customerRequest.")
    })
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest)
    {
        try {
            customerService.addCustomer(customerRequest.getEmail(), customerRequest.getName(), customerRequest.getCategory());
            logger.info("New customerRequest added {}", customerRequest.getEmail());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occurred while saving the customerRequest", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{email}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "CustomerRequest not found.")
    })
    @ApiImplicitParam(name = "X-API", value = "X-API", dataType = "string", paramType = "header")
    public ResponseEntity<CustomerResponse> getCustomer(@RequestParam("email") String email) {
        try {
            CustomerResponse response = null;
            Optional<Customer> customer = customerService.getCustomer(email);
            if (customer.isPresent()) {
                response = new CustomerResponse("SUCCESS", customer.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response = new CustomerResponse("SUCCESS", null);
                //throw new ResourceNotFoundException("Customer not found by email: " + email);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            logger.error("Error occurred while search", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
