package io.tiklab.teamwire.projectset.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.user.user.model.User;

import java.util.List;

/**
 * 项目集模版
 */
@ApiModel
@Join
@Mapper
public class ProjectSet extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;


    @ApiProperty(name="name",desc="项目集名字",required = true)
    private java.lang.String name;


    @ApiProperty(name="startTime",desc="项目集开始时间",required = true)
    private java.sql.Date startTime;


    @ApiProperty(name="endTime",desc="项目集结束时间",required = true)
    private java.sql.Date endTime;

    @ApiProperty(name="remark",desc="项目集备注")
    private java.lang.String remark;


    @ApiProperty(name="sort",desc="项目集排序",required = true)
    private java.lang.Integer sort;

    @ApiProperty(name = "creator",desc = "项目集创建者")
    private String creator;

    @ApiProperty(name="master",desc="负责人",required = true)
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    @ApiProperty(name="project_number",desc="项目数量")
    private Integer projectNumber;

    @ApiProperty(name="isDelete",desc="删除")
    private java.lang.Integer isDelete;

    @ApiProperty(name="creatTime",desc="创建时间")
    private java.sql.Date creatTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    private java.sql.Date updateTime;

    @ApiProperty(name="ids",desc="关联的项目id")
    private List<String>  ids;

    @ApiProperty(name = "projectSetLimits", desc="可见范围")
    private String projectSetLimits;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.sql.Date getStartTime() {
        return startTime;
    }

    public void setStartTime(java.sql.Date startTime) {
        this.startTime = startTime;
    }
    public java.sql.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.sql.Date endTime) {
        this.endTime = endTime;
    }
    public java.lang.String getRemark() {
        return remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }
    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setIsDelete(java.lang.Integer isDelete) {
        this.isDelete = isDelete;
    }
    public java.sql.Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(java.sql.Date creatTime) {
        this.creatTime = creatTime;
    }
    public java.sql.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.sql.Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(Integer projectNumber) {
        this.projectNumber = projectNumber;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getProjectSetLimits() {
        return projectSetLimits;
    }

    public void setProjectSetLimits(String projectSetLimits) {
        this.projectSetLimits = projectSetLimits;
    }
}
