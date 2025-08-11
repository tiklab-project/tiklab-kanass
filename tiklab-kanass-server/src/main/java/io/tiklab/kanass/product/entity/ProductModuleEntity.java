package io.tiklab.kanass.product.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="pmc_product_module")
public class ProductModuleEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id", length = 12)
    private String id;

    @Column(name = "module_name", length = 64,notNull = true)
    private String moduleName;

    @Column(name = "description")
    private String desc;

    @Column(name = "parent_id", length = 12)
    private String parentId;

    @Column(name = "product_id", length = 12,notNull = true)
    private String productId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
