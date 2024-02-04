package io.thoughtware.kanass.workitem.entity;


import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 事项类型实例
 */
@Entity
@Table(name="pmc_work_version")
public class WorkVersionEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 事项类型名称
    @Column(name = "work_item_id",length = 16,notNull = true)
    private String workItemId;

    // 事项类型描述
    @Column(name = "version_id",length = 12,notNull = true)
    private String versionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}
