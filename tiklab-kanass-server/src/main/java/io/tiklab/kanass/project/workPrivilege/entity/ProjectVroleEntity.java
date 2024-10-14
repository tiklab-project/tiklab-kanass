package io.tiklab.kanass.project.workPrivilege.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="pmc_project_vrole")
public class ProjectVroleEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "project_id",length = 12,notNull = true)
    private String projectId;

    @Column(name = "vrole_id",length = 64,notNull = true)
    private String vroleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getVroleId() {
        return vroleId;
    }

    public void setVroleId(String vroleId) {
        this.vroleId = vroleId;
    }
}
