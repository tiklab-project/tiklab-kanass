package io.tiklab.kanass.workitem.entity;

import io.tiklab.dal.jpa.annotation.*;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="pmc_work_role_function")
public class WorkItemRoleFunctionEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "work_type_id",length = 12,notNull = true)
    private String workTypeId;

    @Column(name = "function_id",length = 12,notNull = true)
    private String functionId;

    @Column(name = "role_id",length = 64,notNull = true)
    private String roleId;

    // 优先级描述
    @Column(name = "function_type",length = 12,notNull = true)
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
}
