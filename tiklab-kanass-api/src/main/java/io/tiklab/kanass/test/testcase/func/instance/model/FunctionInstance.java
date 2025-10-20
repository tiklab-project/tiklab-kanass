package io.tiklab.kanass.test.testcase.func.instance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.kanass.test.testplan.cases.model.TestPlan;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.kanass.test.testcase.func.cases.model.FuncUnitCase;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;

import java.sql.Timestamp;
/**
 * 场景历史实例 模型
 */
@ApiModel
@Mapper(targetName  = "io.tiklab.kanass.test.func.instance.entity.FunctionInstanceEntity")
@Join
public class FunctionInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="caseId",desc="测试用例id")
    @Mappings({
            @Mapping(source = "funcUnitCase.id",target = "caseId")
    })
    @JoinField(key = "id")
    private FuncUnitCase funcUnitCase;

    @ApiProperty(name="testPlanInstanceId",desc="测试计划历史id")
    @Mappings({
            @Mapping(source = "testPlan.id",target = "testPlanInstanceId")
    })
    @JoinField(key = "id")
    private TestPlan testPlan;
//    private String testPlanInstanceId;

    @ApiProperty(name="projectId",desc="仓库id")
    private String projectId;

    @ApiProperty(name="result",desc="结果 0:失败 1:成功 2:待核查 3：不可用 4：阻塞")
    private Integer result;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="createUser",desc="创建人")
    @Mappings({
            @Mapping(source = "createUser.id",target = "createUser")
    })
    @JoinField(key = "id")
    private User createUser;

    @ApiProperty(name="comment",desc="评论")
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FuncUnitCase getFuncUnitCase() {
        return funcUnitCase;
    }

    public void setFuncUnitCase(FuncUnitCase funcUnitCase) {
        this.funcUnitCase = funcUnitCase;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
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

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }
}
