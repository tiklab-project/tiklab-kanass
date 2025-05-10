package io.tiklab.kanass.project.test.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

import java.util.Date;

@ApiModel
@Mapper
@Join
public class TestPlan extends BaseModel {
    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="name",desc="计划名称")
    private String name;

    @ApiProperty(name="startTime",desc="开始时间")
    private Date startTime;

    @ApiProperty(name="endTime ", desc="结束时间")
    private Date endTime;

    @ApiProperty(name="state",desc="状态 0未开始 1进行中 2结束")
    private Integer state;

    @ApiProperty (name="desc" ,desc="指述")
    private String desc;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
