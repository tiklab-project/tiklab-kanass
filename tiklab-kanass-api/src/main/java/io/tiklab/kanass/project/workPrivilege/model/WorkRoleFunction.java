package io.tiklab.kanass.project.workPrivilege.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;

/**
 * 优先级模型
 */
@ApiModel
@Mapper
public class WorkRoleFunction extends BaseModel {

    @ApiProperty(name="id",desc="事项功能")
    private String id;

    @ApiProperty(name="roleId",desc="角色id")
    private String roleId;

    @ApiProperty(name="roleType",desc="角色id类型, role: 角色 vrole: 虚拟角色")
    private String roleType;


    private String[] functionList;

    private String privilegeId;
    private String newPrivilegeId;
    @ApiProperty(name="functionId",desc="事项功能Id")
    private String functionId;

    private String functionType;

    @ApiProperty(name="type",desc="类型 1: system 2: project")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String[] getFunctionList() {
        return functionList;
    }

    public void setFunctionList(String[] functionList) {
        this.functionList = functionList;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    public String getNewPrivilegeId() {
        return newPrivilegeId;
    }

    public void setNewPrivilegeId(String newPrivilegeId) {
        this.newPrivilegeId = newPrivilegeId;
    }
}
