package io.tiklab.kanass.support.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 最近访问的模型实例
 */
@Entity
@Table(name="pmc_recent")
public class RecentEntity {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12,notNull = true)
    private String id;

    //名字
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    //模型（project, workItem, sprint)
    @Column(name = "model",length = 64,notNull = true)
    private String model;

    //模型id
    @Column(name = "model_id",length = 12,notNull = true)
    private String modelId;

    //操作人id
    @Column(name = "master_id",length = 12,notNull = true)
    private String masterId = "111111";

    //项目id
    @Column(name = "project_id",length = 12)
    private String projectId;

    @Column(name = "project_type_id",length = 8)
    private String projectTypeId;

    //访问时间
    @Column(name = "recent_time",notNull = true)
    private Timestamp recentTime;

    @Column(name = "icon_url",notNull = true)
    private String iconUrl;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }



    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(String projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public Timestamp getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(Timestamp recentTime) {
        this.recentTime = recentTime;
    }
}
