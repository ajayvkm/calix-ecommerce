package com.calix.ecommerce.services;

import com.calix.ecommerce.models.entity.Customer;
import com.calix.ecommerce.models.entity.Product;
import com.calix.ecommerce.models.entity.Transaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ImportService {
    Logger logger = LoggerFactory.getLogger(ImportService.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    TransactionService transactionService;

    public static String TYPE = "text/csv";
    static String[] HEADERs = {"CustomerEmail", "Products", "TransactionID", "TransactionAmount", "Currency", "Timestamp", "Region", "VendorId"};

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void process(MultipartFile file) {
        Set<Customer> customerSet = new HashSet<>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                String email = csvRecord.get(0);
                String products = csvRecord.get(1);
                String txid = csvRecord.get(2);
                Double amount = Double.valueOf(csvRecord.get(3));
                String currency = csvRecord.get(4);
                String timestamp = csvRecord.get(5);
                String region = csvRecord.get(6);
                String vendorId = csvRecord.get(7);

                Optional<Customer> customerOptional = customerService.getCustomer(email);
                Customer customer = null;
                if(customerOptional.isPresent()) {
                    customer = customerOptional.get();
                } else {
                    customer = new Customer("Richard S", email, "MARKETING_PROMO_1");
                }

                Set<Product> productSet = productService.getProducts(products);

                Transaction transaction = transactionService.createTransaction(txid, amount, currency, timestamp, region, vendorId, productSet);
                customer.setTransactions(Set.of(transaction));
                customerSet.add(customer);
            }

            List<Customer> customerList = customerService.saveAllCustomer(customerSet);

        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }
}
