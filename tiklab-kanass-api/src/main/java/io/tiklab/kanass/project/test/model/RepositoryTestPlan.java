package io.tiklab.kanass.project.test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.user.user.model.User;

import java.util.Date;
import java.util.Map;
// 远程地址的测试计划
@ApiModel
@Mapper
@Join
public class RepositoryTestPlan extends BaseModel {
    @ApiProperty (name="id" ,desc="id")  
    private String id;
    @ApiProperty(name="type", desc="类型") 
    private String type;
    @ApiProperty(name="name", desc="测试计划名称")
    private String name;
    @ApiProperty (name="startTime",desc="开始时间") 
    private Date startTime;
    @ApiProperty (name="endTime", desc="结束时间")
    private Date endTime;
    @ApiProperty (name="state",desc="状态 0 未开始 1进行中 2结束")
    private Integer state;
    private Map<String, Object> recentInstance;
    @ApiProperty (name="principal",desc="负责人") 
    @Mappings({
        @Mapping(source = "principal.id",target = "principal")
    })
    @JoinQuery(key = "id")
    private User principal;
    @ApiProperty (name="repository",desc="所属仓库") 
    private String repositoryId;
    @ApiProperty (name="desc" ,desc="指述") 
    private String desc;
//    @ApiProperty (name="createTime", desc="创建时间")
//    @JsonFormat(pattern= "yyyy-MM-dd" , timezone="GMT+8")
//    private Timestamp createTime;
//    @ApiProperty (name="updateTime",desc="更新时间")
//    @JsonFormat (pattern="yyyy-MM-dd", timezone="GMT+8")
//    private Timestamp updateTime;
    @ApiProperty (name=" sort",desc="排序") 
    private Integer sort;
    @ApiProperty (name="testCaseNum",desc="用例数量") 
    private Integer testCaseNum;

    private String apiEnvId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

//    public Timestamp getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Timestamp createTime) {
//        this.createTime = createTime;
//    }
//
//    public Timestamp getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Timestamp updateTime) {
//        this.updateTime = updateTime;
//    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getTestCaseNum() {
        return testCaseNum;
    }

    public void setTestCaseNum(Integer testCaseNum) {
        this.testCaseNum = testCaseNum;
    }

    public String getApiEnvId() {
        return apiEnvId;
    }

    public void setApiEnvId(String apiEnvId) {
        this.apiEnvId = apiEnvId;
    }
}
