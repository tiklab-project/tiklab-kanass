package io.tiklab.kanass.test.testplan.cases.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;

import java.sql.Timestamp;
import java.util.Map;

/**
 * 测试计划 模型
 */
@ApiModel
@Mapper
@Join
public class TestPlan extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="name",desc="测试计划名称")
    private String name;

    @ApiProperty(name="startTime",desc="开始时间")
    private java.util.Date startTime;

    @ApiProperty(name="endTime",desc="结束时间")
    private java.util.Date endTime;

    @ApiProperty(name="state",desc="状态  0 未开始  1进行中  2结束")
    private Integer state;

    private Map<String,Object> recentInstance;

    @ApiProperty(name="principal",desc="负责人")
    @Mappings({
            @Mapping(source = "principal.id",target = "principals")
    })
    @JoinField(key = "id")
    private User principal;

    @ApiProperty(name="project",desc="所属仓库")
    private String projectId;

    @ApiProperty(name="desc",desc="描述")
    private String desc;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp updateTime;

    @ApiProperty(name="testCaseNum",desc="用例数量")
    private Integer testCaseNum;

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
    public java.util.Date getStartTime() {
        return startTime;
    }

    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }
    public java.util.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Map<String, Object> getRecentInstance() {
        return recentInstance;
    }

    public void setRecentInstance(Map<String, Object> recentInstance) {
        this.recentInstance = recentInstance;
    }

    public User getPrincipal() {
        return principal;
    }

    public void setPrincipal(User principal) {
        this.principal = principal;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }


    public Integer getTestCaseNum() {
        return testCaseNum;
    }

    public void setTestCaseNum(Integer testCaseNum) {
        this.testCaseNum = testCaseNum;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
