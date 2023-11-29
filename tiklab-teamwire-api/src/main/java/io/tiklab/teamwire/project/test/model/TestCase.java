package io.tiklab.teamwire.project.test.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.user.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Map;

/**
 * 测试用例 模型
 */
@ApiModel
@Mapper(targetName  = "io.tiklab.teston.test.test.entity.TestCasesEntity")
@Join
public class TestCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="name",desc="用例名",required = true)
    private String name;

    @ApiProperty(name="category",desc="所属模块")
    @Mappings({
            @Mapping(source = "category.id",target = "categoryId")
    })
    @JoinQuery(key = "id")
    private Category category;

    @ApiProperty(name="仓库Id",desc="所属仓库")
    private String repositoryId;

    @NotNull
    @ApiProperty(name="testType",desc="测试类型:auto,perform,function",required = true)
    private String testType;

    @NotNull
    @ApiProperty(name="caseType",desc="用例类型，例：api-unit,api-scene",required = true)
    private String caseType;

    @ApiProperty(name="user",desc="创建人")
    @Mappings({
            @Mapping(source = "createUser.id",target = "createUser")
    })
    @JoinQuery(key = "id")
    private User createUser;

    @ApiProperty(name="updateUser",desc="更新人")
    @Mappings({
            @Mapping(source = "updateUser.id",target = "updateUser")
    })
    @JoinQuery(key = "id")
    private User updateUser;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp updateTime;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    @ApiProperty(name="desc",desc="描述")
    private String desc;

    @ApiProperty(name="director",desc="负责人")
    @Mappings({
            @Mapping(source = "director.id",target = "director")
    })
    @JoinQuery(key = "id")
    private User director;

    @ApiProperty(name="status",desc="状态")
    private Integer status;

    @ApiProperty(name="priorityLevel",desc="优先级")
    private Integer priorityLevel;

    @ApiProperty(name="workItemId",desc="需求")
    private String workItemId;

    private Map<Object,Object> recentInstance;

    public String getId() {
        return id;
    }

    public TestCase setId(String id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<Object, Object> getRecentInstance() {
        return recentInstance;
    }

    public void setRecentInstance(Map<Object, Object> recentInstance) {
        this.recentInstance = recentInstance;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public User getDirector() {
        return director;
    }

    public void setDirector(User director) {
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
}
