package io.tiklab.kanass.workitem.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 事项文档实例
 */
@Entity
@Table(name="pmc_work_test_case")
public class WorkTestCaseEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 事项id
    @Column(name = "work_item_id",length = 16,notNull = true)
    private String workItemId;

    // 文档id
    @Column(name = "test_case_id",length = 32,notNull = true)
    private String testCaseId;

    @Column(name = "repository_id",length = 12,notNull = true)
    private String repositoryId;

    @Column(name = "project_id",length = 12,notNull = true)
    private String projectId;
    // 排序
    @Column(name = "sort",length = 4)
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
