package com.calix.ecommerce.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "vendor")
@EntityListeners(AuditableListener.class)
@NoArgsConstructor
public class Vendor extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    public Vendor(String name) {
        this.name = name;
    }
}
