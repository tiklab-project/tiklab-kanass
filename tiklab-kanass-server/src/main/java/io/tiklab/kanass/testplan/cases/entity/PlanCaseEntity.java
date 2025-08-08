package io.tiklab.kanass.testplan.cases.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.Id;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 测试计划中查询用例返回的 实体
 */
@Entity
public class PlanCaseEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    // 用例名
    @Column(name = "name",length = 64)
    private String name;

    // 所属模块
    @Column(name = "module_id")
    private String moduleId;

    // 用例类型，例：api-unit,api-scene
    @Column(name = "case_type")
    private String caseType;

    // 创建人
    @Column(name = "create_user")
    private String createUser;

    // 负责人
    @Column(name = "director",length = 32)
    private String director;

    // 状态
    @Column(name = "status",length = 8)
    private Integer status;

    // 优先级
    @Column(name = "priority_level",length = 8)
    private Integer priorityLevel;

    @Column(name = "create_time")
    private Timestamp createTime;

    //用于plan 绑定的case，返回的id做映射
    @Column(name = "plan_case_id")
    private String planCaseId;

    // 结果，用于功能测试用例展示
    @Column(name = "result")
    private Integer result;

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

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getPlanCaseId() {
        return planCaseId;
    }

    public void setPlanCaseId(String planCaseId) {
        this.planCaseId = planCaseId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
