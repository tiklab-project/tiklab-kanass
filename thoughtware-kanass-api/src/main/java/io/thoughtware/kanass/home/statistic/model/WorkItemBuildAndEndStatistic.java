package io.thoughtware.kanass.home.statistic.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.join.annotation.Join;

/**
 * 事项创建与解决统计模型
 */
@ApiModel
@Join
public class WorkItemBuildAndEndStatistic {

    @ApiProperty(name = "time", desc = "时间")
    private String time;

    //经办人
    @ApiProperty(name = "id", desc = "id")
    private String id;

    //该状态记录数
    @ApiProperty(name = "buildCount", desc = "创建总数")
    private Integer buildCount;

    //所有记录数
    @ApiProperty(name = "endCount", desc = "完成总数")
    private Integer endCount;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBuildCount() {
        return buildCount;
    }

    public void setBuildCount(Integer buildCount) {
        this.buildCount = buildCount;
    }

    public Integer getEndCount() {
        return endCount;
    }

    public void setEndCount(Integer endCount) {
        this.endCount = endCount;
    }
}
