package io.tiklab.teamwire.home.statistic.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.join.annotation.Join;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.tiklab.user.user.model.User;

import java.text.DecimalFormat;

/**
 *
 */
@ApiModel
@Join
public class  WorkItemAssignerStat {

    //负责人id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String assignerId;

    //负责人
    @ApiProperty(name = "assigner", desc = "负责人")
    private User assigner;

    //分组记录数目
    @ApiProperty(name = "groupCount", desc = "分组数量")
    private Integer groupCount;

    //所有记录数
    @ApiProperty(name = "totalCount", desc = "全部数量")
    private Integer totalCount;

    //该组记录数占比
    @ApiProperty(name = "percent", desc = "百分比，整数")
    private Double percent;

    //该组记录数占比，字符串
    @ApiProperty(name = "percentText", desc = "百分比，字符串")
    private String percentText;

    public String getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    public User getAssigner() {
        return assigner;
    }

    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public String getPercentText() {
        return percentText;
    }

    public void setPercentText(String percentText) {
        this.percentText = percentText;
    }

    public void process(){
        percent = Double.valueOf(groupCount)/Double.valueOf(totalCount);

        DecimalFormat df = new DecimalFormat("0.00%");
        percentText = df.format(percent);
    }

}
