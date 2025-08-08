package io.tiklab.kanass.test.common.stepcommon.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 变量断言 实体
 */
@Entity
@Table(name="test_case_step_common")
public class StepCommonEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属断言
    @Column(name = "case_id")
    private String caseId;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 类型
    @Column(name = "type")
    private String type;

    // 排序
    @Column(name = "sort")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
