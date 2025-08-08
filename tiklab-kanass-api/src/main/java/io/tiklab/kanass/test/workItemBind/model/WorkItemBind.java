package io.tiklab.kanass.test.workItemBind.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.kanass.test.test.model.TestCase;
import io.tiklab.kanass.testplan.cases.model.TestPlan;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;

import java.sql.Timestamp;

/**
 * postinUrl配置 模型
 */
@ApiModel
@Mapper
@Join
public class WorkItemBind extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="workItemId",desc="workItemId")
    @Mappings({
            @Mapping(source = "workItem.id",target = "workItemId")
    })
    @JoinField(key = "id")
    private WorkItem workItem;

    @ApiProperty(name="caseId",desc="用例id")
    @Mappings({
            @Mapping(source = "testCase.id",target = "caseId")
    })
    @JoinField(key = "id")
    private TestCase testCase;

    @ApiProperty(name="projectId",desc="仓库id")
    private String projectId;

    @ApiProperty(name="testPlanId",desc="测试计划id")
    private String testPlanId;

    @ApiProperty(name="testPlan",desc="测试计划")
    @Mappings({
            @Mapping(source = "testPlan.id",target = "testPlanId")
    })
    @JoinField(key = "id")
    private TestPlan testPlan;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="projectUrl",desc="teamwire服务端地址")
    private String projectUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }
}
