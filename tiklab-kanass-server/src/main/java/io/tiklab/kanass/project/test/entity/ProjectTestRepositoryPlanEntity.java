package io.tiklab.kanass.project.test.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 项目关联测试计划实例
 */
@Entity
@Table(name="pmc_project_test_repository_plan")
public class ProjectTestRepositoryPlanEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "test_repository_id",length = 12,notNull = true)
    private String testRepositoryId;

    @Column(name = "project_id",length = 12,notNull = true)
    private String  projectId;

    @Column(name = "plan_id",length = 12,notNull = true)
    private String  planId;

    @Column(name = "sort",length = 4,notNull = true)
    private Integer  sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestRepositoryId() {
        return testRepositoryId;
    }

    public void setTestRepositoryId(String testRepositoryId) {
        this.testRepositoryId = testRepositoryId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
