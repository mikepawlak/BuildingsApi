package com.msr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@Entity
public class SiteUse {
    @Id
    private int id;

    @JsonProperty("site_id")
    private int siteId;

    private String description;

    @JsonProperty("size_sqft")
    private long sizeSqft;

    @JsonProperty("use_type_id")
    private int useTypeId;

    @Transient
    private UseType useType;
}
