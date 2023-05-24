package com.calix.ecommerce.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

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
}
