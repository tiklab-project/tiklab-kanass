package io.tiklab.kanass.test.func.instance.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.sql.Timestamp;

/**
 * 场景历史实例 实体
 */
@Entity
@Table(name="test_function_instance")
public class FunctionInstanceEntity {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 功能用例id
    @Column(name = "case_id")
    private String caseId;

    // test_plan_instance_id
    @Column(name = "test_plan_instance_id")
    private String testPlanInstanceId;

    //测试结果 0:失败 1:成功 2:待核查 3：不可用 4：阻塞
    @Column(name = "result")
    private Integer result;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "comment")
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getTestPlanInstanceId() {
        return testPlanInstanceId;
    }

    public void setTestPlanInstanceId(String testPlanInstanceId) {
        this.testPlanInstanceId = testPlanInstanceId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
