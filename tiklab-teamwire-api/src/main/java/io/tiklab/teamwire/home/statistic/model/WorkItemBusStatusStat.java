package io.tiklab.teamwire.home.statistic.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.join.annotation.Join;

import javax.validation.constraints.NotNull;

/**
 * 按照事项状态分组统计类型
 */
@ApiModel
@Join
public class WorkItemBusStatusStat {

    @NotNull
    @ApiProperty(name="statusName",desc="状态名称",eg="@text32",required = true)
    private java.lang.String statusName;

    //该状态记录数
    @ApiProperty(name = "groupCount", desc = "分组数量")
    private Integer groupCount;

    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
