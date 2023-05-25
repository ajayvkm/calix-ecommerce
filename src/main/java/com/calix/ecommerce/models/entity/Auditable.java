package com.calix.ecommerce.models.entity;

import java.sql.Timestamp;

public interface Auditable {
    Timestamp getCreateDate();

    void setCreateDate(Timestamp createDate);

    Timestamp getUpdateDate();

    void setUpdateDate(Timestamp updateDate);
}
