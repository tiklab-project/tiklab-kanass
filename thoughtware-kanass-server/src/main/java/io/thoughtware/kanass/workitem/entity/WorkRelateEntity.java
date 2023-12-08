package io.thoughtware.kanass.workitem.entity;


import io.thoughtware.dal.jpa.annotation.Column;
import io.thoughtware.dal.jpa.annotation.GeneratorValue;
import io.thoughtware.dal.jpa.annotation.Id;
import io.thoughtware.dal.jpa.annotation.Table;import io.thoughtware.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  关联事项实例
 */
@Entity
@Table(name="pmc_work_relate")
public class WorkRelateEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 事项id
    @Column(name = "work_item_id",length = 32,notNull = true)
    private String workItemId;

    // 关联事项id
    @Column(name = "relate_item_id",length = 32,notNull = true)
    private String relateItemId;

    // 创建日期
    @Column(name = "create_time")
    private Timestamp createTime;

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

    public String getRelateItemId() {
        return relateItemId;
    }

    public void setRelateItemId(String relateItemId) {
        this.relateItemId = relateItemId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
