package com.example.demo.entity;

import com.example.demo.listener.TenantListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@MappedSuperclass
@Data
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
@Filter(condition = "tenant_id=:tenantId", name = "tenantFilter")
@EntityListeners(TenantListener.class)
public abstract  class AbstractBaseEntity implements TenantAware
{
    @JsonIgnore
    @Column(nullable = false, updatable = false)
    protected String tenantId;


}
