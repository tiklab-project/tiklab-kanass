package io.tiklab.kanass.project.project.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * 项目实例
 */
@Entity
@Table(name="pmc_project")
public class ProjectEntity implements Serializable {

    @Id
    @Column(name = "id",length = 12)
    private String id;

    //项目名字
    @Column(name = "project_name",length = 64,notNull = true)
    private String projectName;

    //项目类型 id
    @Column(name = "project_type_id",length = 32,notNull = true)
    private String  projectTypeId;

    //项目类型 key，用于项目事项id生成
    @Column(name = "project_key",length = 32,notNull = true)
    private String projectKey;

    // 项目可见范围
    @Column(name = "project_limits",length = 32,notNull = true)
    private String projectLimits;

    //项目创建人id
    @Column(name = "creator",length = 64,notNull = true)
    private String creator;

    //项目负责人
    @Column(name = "master",length = 32)
    private String master;

    //项目描述
    @Column(name = "description",length = 64)
    private String desc;

    //项目集id
    @Column(name = "project_set_id",length = 32)
    private String projectSetId;

    //项目集id
    @Column(name = "product_id",length = 32)
    private String productId;

    //项目状态 1 未开始  2 进行中 3 已完成
    @Column(name = "project_state",length = 32)
    private String  projectState;

    //开始时间
    @Column(name = "start_time")
    private Date startTime;

    //结束时间
    @Column(name = "end_time")
    private Date endTime;

    //icon
    @Column(name = "icon_url",length = 64)
    private String iconUrl;

    @Column(name = "color")
    private int color;

    @Column(name = "create_time")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(String projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getProjectSetId() {
        return projectSetId;
    }

    public void setProjectSetId(String projectSetId) {
        this.projectSetId = projectSetId;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getProjectLimits() {
        return projectLimits;
    }

    public void setProjectLimits(String projectLimits) {
        this.projectLimits = projectLimits;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
