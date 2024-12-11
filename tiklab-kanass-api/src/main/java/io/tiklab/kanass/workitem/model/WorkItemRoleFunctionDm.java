package io.tiklab.kanass.workitem.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;

import javax.validation.constraints.NotNull;

/**
 * 优先级模型
 */
@ApiModel
@Mapper
public class WorkItemRoleFunctionDm extends BaseModel {

    @ApiProperty(name="id",desc="优先级ID")
    private String id;

    @ApiProperty(name="roleId",desc="角色id")
    private String roleId;

    @ApiProperty(name="domainId",desc="项目id")
    private String domainId;

    @ApiProperty(name="workTypeId",desc="事项类型id")
    private String workTypeId;

    @ApiProperty(name="newWorkTypeId",desc="新的事项类型id")
    private String newWorkTypeId;

    @ApiProperty(name="functionListId",desc="功能ids")
    private String[] functionListId;
    @ApiProperty(name="functionId",desc="功能id")
    private String functionId;

    @ApiProperty(name="functionType",desc = "功能类型， field: 字段权限， action: 功能权限")
    private String functionType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String[] getFunctionListId() {
        return functionListId;
    }

    public void setFunctionListId(String[] functionListId) {
        this.functionListId = functionListId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getNewWorkTypeId() {
        return newWorkTypeId;
    }

    public void setNewWorkTypeId(String newWorkTypeId) {
        this.newWorkTypeId = newWorkTypeId;
    }
}
