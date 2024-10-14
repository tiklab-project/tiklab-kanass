package io.tiklab.kanass.project.project.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 收藏项目实例
 */

@Entity
@Table(name="pmc_project_focus")
public class ProjectFocusEntity  implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 收藏的项目id
    @Column(name = "project_id",length = 64)
    private String projectId;

    // 收藏者id
    @Column(name = "master_id",length = 64)
    private String masterId;

    // 排序
    @Column(name = "sort")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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
