package io.tiklab.kanass.test.testplan.cases.model;

import io.tiklab.kanass.test.testcase.test.model.TestCase;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;

/**
 * 测试计划绑定的用例 模型
 */
@ApiModel
@Mapper
@Join
public class TestPlanCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="projectId",desc="projectId")
    private String projectId;

    @ApiProperty(name="testPlan",desc="测试计划")
    @Mappings({
            @Mapping(source = "testPlan.id",target = "testPlanId")
    })
    @JoinField(key = "id")
    private TestPlan testPlan;

    @ApiProperty(name="testCase",desc="用例id")
    @Mappings({
            @Mapping(source = "testCase.id",target = "testCaseId")
    })
    @JoinField(key = "id")
    private TestCase testCase;

    @ApiProperty(name="status",desc="状态:0 失败  1 通过  2无法执行")
    private Integer status;

    @ApiProperty(name="sort",desc="排序")
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

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
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
