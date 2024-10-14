package io.tiklab.kanass.home.insight.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.sql.Timestamp;

/**
 * 仪表盘实体
 */
@Entity
@Table(name="pmc_insight")
public class InsightEntity {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    //仪表盘名称
    @Column(name = "insight_name",length = 64,notNull = true)
    private String insightName;

    //仪表盘分组
    @Column(name = "insight_group",length = 64,notNull = true)
    private String insightGroup;

    //仪表盘创建人
    @Column(name = "master",length = 64)
    private String master;

    //创建时间
    @Column(name = "createdTime",length = 64)
    private Timestamp createdTime;

    //位置，搜索条件等数据
    @Column(name = "data",length = 1024)
    private String data;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInsightName() {
        return insightName;
    }

    public void setInsightName(String insightName) {
        this.insightName = insightName;
    }

    public String getInsightGroup() {
        return insightGroup;
    }

    public void setInsightGroup(String insightGroup) {
        this.insightGroup = insightGroup;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
