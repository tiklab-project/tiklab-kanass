package io.tiklab.kanass.test.testplan.cases.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 测试计划绑定的用例 实体
 */
@Entity @Table(name="test_test_plan_cases")
public class TestPlanCaseEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 测试计划
    @Column(name = "test_plan_id",length = 32)
    private String testPlanId;

    // 用例id
    @Column(name = "test_case_id",length = 32)
    private String testCaseId;

    // 状态
    @Column(name = "status",length = 32)
    private Integer status;

    // 排序
    @Column(name = "sort")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
