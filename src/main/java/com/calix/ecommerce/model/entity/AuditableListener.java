package com.calix.ecommerce.model.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class AuditableListener {
    @PreUpdate
    @PrePersist
    public void beforeSave(Auditable auditable) {
        Instant instant = Instant.now();
        ZonedDateTime nowUTC = instant.atZone(ZoneOffset.UTC);
        Timestamp timestamp = Timestamp.from(nowUTC.toInstant());
        auditable.setUpdateDate(timestamp);
        if (auditable.getCreateDate() == null) {
            auditable.setCreateDate(timestamp);
        }
    }
}

