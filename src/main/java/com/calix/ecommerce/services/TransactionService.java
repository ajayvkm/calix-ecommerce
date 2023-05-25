package com.calix.ecommerce.services;

import com.calix.ecommerce.models.entity.Product;
import com.calix.ecommerce.models.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

@Service
public class TransactionService {
    Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    VendorService vendorService;

    public Transaction createTransaction(String txid, Double amount, String currency, String timestamp, String region, String vendorId, Set<Product> productSet) {
        Transaction transaction = new Transaction();
        transaction.setTxid(txid);
        transaction.setTxAmount(BigDecimal.valueOf(amount));
        transaction.setCurrency(currency);
        Timestamp ts = Timestamp.from(Instant.parse(timestamp));
        transaction.setTimestamp(ts);
        transaction.setRegion(region);
        transaction.setVendor(vendorService.getVendor(vendorId));
        transaction.setProducts(productSet);
        return transaction;
    }
}
