package io.tiklab.kanass.test.instance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.kanass.test.testcase.test.model.TestCase;
import io.tiklab.kanass.test.testplan.cases.model.TestPlan;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.user.user.model.User;

import java.sql.Timestamp;

/**
 * 测试计划下用例的实例的中间层 模型
 */
@ApiModel
@Mapper
@Join
public class Instance extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="name",desc="名字")
    private String name;

    @ApiProperty(name="type",desc="类型")
    private String type;

    @ApiProperty(name="projectId",desc="所属id")
    private String projectId;

    @ApiProperty(name="content",desc="内容：json字符串概要")
    private String content;

    @ApiProperty(name="createUser",desc="创建人")
    @Mappings({
            @Mapping(source = "createUser.id",target = "createUser")
    })
    @JoinField(key = "id")
    private User createUser;

    @ApiProperty(name="executeNumber",desc="执行次数")
    private Integer executeNumber;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="status",desc="执行状态")
    private String status;

    @ApiProperty(name="planType",desc="计划类型")
    private String planType;

    @ApiProperty(name = "caseId")
    @Mappings({
            @Mapping(source = "testCase.id",target = "caseId")
    })
    @JoinField(key = "id")
    private TestCase testCase;

    @ApiProperty(name = "testPlanId")
    @Mappings({
            @Mapping(source = "testPlan.id",target = "testPlanId")
    })
    @JoinField(key = "id")
    private TestPlan testPlan;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getExecuteNumber() {
        return executeNumber;
    }

    public void setExecuteNumber(Integer executeNumber) {
        this.executeNumber = executeNumber;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }
}
