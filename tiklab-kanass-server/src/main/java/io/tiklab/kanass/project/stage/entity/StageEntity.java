package io.tiklab.kanass.project.stage.entity;

import io.tiklab.dal.jpa.annotation.*;
import io.tiklab.postin.annotation.ApiProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目阶段实例
 */
@Entity
@Table(name="pmc_stage")
public class StageEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    //阶段名称
    @Column(name = "stage_name",length = 64,notNull = true)
    private String stageName;


    @Column(name = "tree_path")
    private String treePath;

    @Column(name = "color")
    private int color;

    // 父级id
    @Column(name = "parent_id",length = 64)
    private String parentId;

    //阶段状态
    @Column(name = "status",length = 64,notNull = true)
    private String status;

    // 阶段进度
    @Column(name = "progress",length = 64,notNull = true)
    private Integer progress;

    // 负责人
    @Column(name = "master",length = 32)
    private String master;

    // 描述
    @Column(name = "description",length = 64)
    private String desc;

    // 所属项目id
    @Column(name = "project_id",length = 12,notNull = true)
    private String projectId;

    @Column(name = "root_id",length = 12,notNull = true)
    private String rootId;

    // 开始时间
    @Column(name = "start_time")
    private String startTime;

    // 结束时间
    @Column(name = "end_time")
    private String endTime;

    @Column(name ="deep")
    private Integer deep;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }



    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    public Integer getDeep() {
        return deep;
    }

    public void setDeep(Integer deep) {
        this.deep = deep;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
