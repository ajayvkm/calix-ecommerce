package com.calix.ecommerce.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "transaction")
@EntityListeners(AuditableListener.class)
@NoArgsConstructor
public class Transaction extends AbstractBaseEntity {

    @Column(name = "txid")
    private String txid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid", nullable = false)
    private Customer customer;

    @Column(name = "txamount")
    private BigDecimal txAmount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "region")
    private String region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendorid", nullable = false)
    private Vendor vendor;

    @Column(name = "timestamp")
    private Timestamp timestamp;
}
