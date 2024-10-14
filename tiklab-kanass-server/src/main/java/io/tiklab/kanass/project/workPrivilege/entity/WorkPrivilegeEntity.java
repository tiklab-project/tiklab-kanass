package io.tiklab.kanass.project.workPrivilege.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="pmc_work_privilege")
public class WorkPrivilegeEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "name",length = 64,notNull = true)
    private String name;

    @Column(name = "grouper",length = 64,notNull = true)
    private String grouper;

    @Column(name = "scope",length = 64,notNull = true)
    private String scope;

    @Column(name = "work_type_id",length = 12,notNull = true)
    private String workTypeId;

    @Column(name = "project_id",length = 12)
    private String projectId;

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

    public String getGrouper() {
        return grouper;
    }

    public void setGrouper(String grouper) {
        this.grouper = grouper;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}