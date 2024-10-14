package io.tiklab.kanass.project.version.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 迭代收藏实例
 */
@Entity
@Table(name="pmc_version_focus")
public class VersionFocusEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 迭代所属项目id
    @Column(name = "project_id",length = 64)
    private String projectId;

    // 迭代id
    @Column(name = "version_id",length = 64)
    private String versionId;

    // 迭代负责人
    @Column(name = "master_id",length = 64)
    private String masterId;

    // 迭代排序
    @Column(name = "sort")
    private Integer sort;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
