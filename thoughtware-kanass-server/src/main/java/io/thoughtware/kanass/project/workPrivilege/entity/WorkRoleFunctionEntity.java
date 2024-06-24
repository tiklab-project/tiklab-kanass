package io.thoughtware.kanass.project.workPrivilege.entity;

import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="pmc_work_role_function")
public class WorkRoleFunctionEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "role_id",length = 12,notNull = true)
    private String roleId;

    @Column(name = "role_type",length = 64,notNull = true)
    private String roleType;

    @Column(name = "function_id",length = 12)
    private String functionId;

    // 1: system 2: project
    @Column(name = "type",length = 12)
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
}