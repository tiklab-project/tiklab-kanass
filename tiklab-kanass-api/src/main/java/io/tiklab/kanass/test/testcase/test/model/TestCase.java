package io.tiklab.kanass.test.testcase.test.model;

import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 测试用例 模型
 */
@ApiModel
@Mapper(targetName  = "io.tiklab.kanass.test.test.entity.TestCasesEntity")
@Join
public class TestCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="caseKey",desc="用例key")
    private String caseKey;

    @NotNull
    @ApiProperty(name="name",desc="用例名",required = true)
    private String name;

    @ApiProperty(name="module",desc="所属模块")
    @Mappings({
            @Mapping(source = "module.id",target = "moduleId")
    })
    @JoinField(key = "id")
    private Module module;

    @ApiProperty(name="仓库Id",desc="仓库id")
    private String projectId;

    @ApiProperty(name="仓库",desc="仓库模型")
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
    })
    @JoinField(key = "id")
    private Project project;

    @NotNull
    @ApiProperty(name="caseType",desc="用例类型，例：api-unit,api-scene",required = true)
    private String caseType;

    @ApiProperty(name="user",desc="创建人")
    @Mappings({
            @Mapping(source = "createUser.id",target = "createUser")
    })
    @JoinField(key = "id")
    private User createUser;

    @ApiProperty(name="updateUser",desc="更新人")
    @Mappings({
            @Mapping(source = "updateUser.id",target = "updateUser")
    })
    @JoinField(key = "id")
    private User updateUser;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp updateTime;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    @ApiProperty(name="desc",desc="描述")
    private String desc;

    @ApiProperty(name="director",desc="负责人")
    @Mappings({
            @Mapping(source = "director.id",target = "director")
    })
    @JoinField(key = "id")
    private User director;

    @ApiProperty(name="status",desc="状态")
    private Integer status;

    @ApiProperty(name="priorityLevel",desc="优先级")
    private Integer priorityLevel;

    @ApiProperty(name="demand",desc="关联需求")
    @Mappings({
            @Mapping(source = "demand.id",target = "demand")
    })
    @JoinField(key = "id")
    private WorkItem demand;


    public String getId() {
        return id;
    }

    public TestCase setId(String id) {
        this.id = id;
        return this;
    }

    public String getCaseKey() {
        return caseKey;
    }

    public void setCaseKey(String caseKey) {
        this.caseKey = caseKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public WorkItem getDemand() {
        return demand;
    }

    public void setDemand(WorkItem demand) {
        this.demand = demand;
    }
}
