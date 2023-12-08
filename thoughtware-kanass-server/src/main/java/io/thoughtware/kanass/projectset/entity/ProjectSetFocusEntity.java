package io.thoughtware.kanass.projectset.entity;

import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 项目收藏实例
 */
@Entity
@Table(name="pmc_project_set_focus")
public class ProjectSetFocusEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 项目集id
    @Column(name = "project_set_id",length = 64)
    private String projectSetId;

    // 收藏者id
    @Column(name = "master_id",length = 64)
    private String masterId;

    // 排序参数
    @Column(name = "sort")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectSetId() {
        return projectSetId;
    }

    public void setProjectSetId(String projectSetId) {
        this.projectSetId = projectSetId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
