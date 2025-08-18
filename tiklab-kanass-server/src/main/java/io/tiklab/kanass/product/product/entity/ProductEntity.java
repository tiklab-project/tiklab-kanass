package io.tiklab.kanass.product.product.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Date;

/**
 * 产品
 */
@Entity
@Table(name="pmc_product")
public class ProductEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 产品名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    // 开始日期
    @Column(name = "start_time",length = 64,notNull = true)
    private Date startTime;

    // 结束日期
    @Column(name = "end_time",length = 32,notNull = true)
    private Date endTime;

    // 备注
    @Column(name = "remark",length = 32)
    private String remark;

    // 创建者
    @Column(name = "creator",length = 64,notNull = true)
    private String creator;

    // 排序
    @Column(name = "sort",length = 32,notNull = true)
    private Integer sort;

    // 负责人
    @Column(name = "master",length = 32,notNull = true)
    private String master;

    @Column(name = "color")
    private Integer color;


    // 创建日期
    @Column(name = "creat_time",length = 32)
    private String creatTime;
    // 更新日期
    @Column(name = "update_time",length = 32)
    private String updateTime;

    // 产品可见范围
    @Column(name = "product_limits",length = 32,notNull = true)
    private String productLimits;

    @Column(name = "status",length = 8)
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductLimits() {
        return productLimits;
    }

    public void setProductLimits(String productLimits) {
        this.productLimits = productLimits;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
