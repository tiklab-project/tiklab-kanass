package io.tiklab.teamwire.projectset.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Date;

/**
 * 项目集实例
 */
@Entity
@Table(name="pmc_project_set")
public class ProjectSetEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 项目集名称
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

    @Column(name = "is_delete",length = 32)
    private Integer isDelete;

    // 创建日期
    @Column(name = "creat_time",length = 32)
    private Date creatTime;

    // 更新日期
    @Column(name = "update_time",length = 32)
    private Date updateTime;

    // 项目集可见范围
    @Column(name = "project_set_limits",length = 32,notNull = true)
    private String projectSetLimits;

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

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ProjectSetEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", remark='" + remark + '\'' +
                ", sort=" + sort +
                ", master='" + master + '\'' +
                ", isDelete=" + isDelete +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                '}';
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
