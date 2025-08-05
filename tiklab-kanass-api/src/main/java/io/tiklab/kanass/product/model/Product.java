package io.tiklab.kanass.product.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;

import java.sql.Date;
import java.util.List;

@ApiModel
@Join
@Mapper
public class Product extends BaseModel {
    @ApiProperty(name="id",desc="id")
    private String id;


    @ApiProperty(name="name",desc="产品名字",required = true)
    private String name;


    @ApiProperty(name="startTime",desc="产品开始时间",required = true)
    private Date startTime;


    @ApiProperty(name="endTime",desc="产品结束时间",required = true)
    private Date endTime;

    @ApiProperty(name="remark",desc="产品备注")
    private String remark;


    @ApiProperty(name="sort",desc="产品排序",required = true)
    private Integer sort;

    @ApiProperty(name = "creator",desc = "产品创建者")
    private String creator;

    @ApiProperty(name = "color",desc = "产品颜色")
    private Integer color;

    @ApiProperty(name="master",desc="负责人",required = true)
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinField(key = "id")
    private User master;

    @ApiProperty(name="project_number",desc="项目数量")
    private Integer projectNumber;

    @ApiProperty(name="creatTime",desc="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String creatTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    private String updateTime;

    @ApiProperty(name="ids",desc="关联的项目id")
    private List<String> ids;

    @ApiProperty(name = "productLimits", desc="可见范围")
    private String productLimits;

    @ApiProperty(name = "status", desc="产品状态 0未开始 1进行中 2已结束")
    private String status;

    @ApiProperty(name = "progress", desc="产品进度")
    private Float progress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Integer getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(Integer projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getProductLimits() {
        return productLimits;
    }

    public void setProductLimits(String productLimits) {
        this.productLimits = productLimits;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getProgress() {
        return progress;
    }

    public void setProgress(Float progress) {
        this.progress = progress;
    }
}
