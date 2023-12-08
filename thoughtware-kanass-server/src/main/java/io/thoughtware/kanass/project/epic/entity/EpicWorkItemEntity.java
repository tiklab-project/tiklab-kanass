package io.thoughtware.kanass.project.epic.entity;

import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 史诗关联事项实例
 */
@Entity
@Table(name="pmc_epic_work_item")
public class EpicWorkItemEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 事项id
    @Column(name = "work_item_id",length = 32,notNull = true)
    private String workItemId;

    //史诗id
    @Column(name = "epic_id",length = 32,notNull = true)
    private String epicId;

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

    public String getEpicId() {
        return epicId;
    }

    public void setEpicId(String epicId) {
        this.epicId = epicId;
    }
}
