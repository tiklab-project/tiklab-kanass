package io.tiklab.kanass.workitem.entity;

import io.tiklab.dal.jpa.annotation.*;

/**
 * 项目事项类型实例
 */
@Entity
@Table(name="pmc_work_type_dm")
public class WorkTypeDmEntity {

    @Id
    @GeneratorValue(length = 8)
    @Column(name = "id",length = 8)
    private String id;

    // 项目id
    @Column(name = "project_id",length = 64,notNull = true)
    private String projectId;

    // 事项类型id
    @Column(name = "work_type_id",length = 64,notNull = true)
    private String workTypeId;

    // 事项类型关联流程
    @Column(name = "flow_id",length = 64,notNull = true)
    private String flowId;

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

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

}
