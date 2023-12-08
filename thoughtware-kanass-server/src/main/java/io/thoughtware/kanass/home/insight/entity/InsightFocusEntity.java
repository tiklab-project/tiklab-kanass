package io.thoughtware.kanass.home.insight.entity;

import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 收藏项目实例
 */

@Entity
@Table(name="pmc_insight_focus")
public class InsightFocusEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 收藏的仪表盘id
    @Column(name = "insight_id",length = 64)
    private String insightId;

    // 收藏者id
    @Column(name = "master_id",length = 64)
    private String masterId;

    // 排序
    @Column(name = "sort")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInsightId() {
        return insightId;
    }

    public void setInsightId(String insightId) {
        this.insightId = insightId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
