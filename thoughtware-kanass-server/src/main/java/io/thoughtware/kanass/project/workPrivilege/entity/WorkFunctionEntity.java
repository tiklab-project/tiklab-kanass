package io.thoughtware.kanass.project.workPrivilege.entity;

import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="pmc_work_function")
public class WorkFunctionEntity implements Serializable {
    @Id
    @GeneratorValue(length = 8)
    @Column(name = "id",length = 8)
    private String id;

    @Column(name = "name",length = 64,notNull = true)
    private String name;

    @Column(name = "code",length = 64,notNull = true)
    private String code;

    @Column(name = "parent_id",length = 64,notNull = true)
    private String parentId;

    @Column(name = "sort",length = 32)
    private String sort;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}