package com.calix.ecommerce.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "product")
@EntityListeners(AuditableListener.class)
@NoArgsConstructor
public class Product extends AbstractBaseEntity {

    @Column(name = "skuname")
    private String skuName;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "products")
    Set<Transaction> transactions;

    public Product(String skuName, String description) {
        this.skuName = skuName;
        this.description = description;
    }
}
