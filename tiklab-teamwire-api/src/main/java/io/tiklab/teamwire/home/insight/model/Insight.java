package io.tiklab.teamwire.home.insight.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.user.user.model.User;

import java.sql.Timestamp;

/**
 * 仪表盘模型
 */
@ApiModel
@Join
@Mapper
public class Insight extends BaseModel {

    @ApiProperty(name="id",desc="仪表盘id")
    private java.lang.String id;

    @ApiProperty(name="insightName",desc="仪表盘名称")
    private String insightName;

    @ApiProperty(name="insightGroup",desc="仪表盘分组")
    private String insightGroup = "1";

    @ApiProperty(name="master",desc="负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    @ApiProperty(name="createdTime",desc="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createdTime;

    @ApiProperty(name="data",desc="位置，搜索条件等数据")
    private java.lang.String data;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getInsightName() {
        return insightName;
    }

    public void setInsightName(java.lang.String insightName) {
        this.insightName = insightName;
    }
    public java.lang.String getInsightGroup() {
        return insightGroup;
    }

    public void setInsightGroup(java.lang.String insightGroup) {
        this.insightGroup = insightGroup;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public java.lang.String getData() {
        return data;
    }

    public void setData(java.lang.String data) {
        this.data = data;
    }
}
