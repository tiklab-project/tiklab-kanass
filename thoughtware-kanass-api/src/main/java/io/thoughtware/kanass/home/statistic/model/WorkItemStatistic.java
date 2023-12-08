package io.thoughtware.kanass.home.statistic.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.join.annotation.Join;

import java.text.DecimalFormat;

/**
 * 按事项状态统计事项分布类型
 */
@ApiModel
@Join
public class WorkItemStatistic {

    @ApiProperty(name = "statisticalTitle", desc = "事项状态名称")
    private String statisticalTitle;

    @ApiProperty(name = "statisticalId", desc = "事项状态id")
    private String statisticalId;

    //该状态记录数
    @ApiProperty(name = "groupCount", desc = "分组数量")
    private Integer groupCount;

    @ApiProperty(name = "totalCount", desc = "全部数量")
    private Integer totalCount;

    @ApiProperty(name = "percent", desc = "该状态记录数占比")
    private Double percent;

    @ApiProperty(name = "percentText", desc = "状态记录数占比，字符串")
    private String percentText;

    public String getStatisticalTitle() {
        return statisticalTitle;
    }

    public void setStatisticalTitle(String statisticalTitle) {
        this.statisticalTitle = statisticalTitle;
    }

    public String getStatisticalId() {
        return statisticalId;
    }

    public void setStatisticalId(String statisticalId) {
        this.statisticalId = statisticalId;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
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

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    public void process(){
        percent = Double.valueOf(groupCount)/Double.valueOf(totalCount);

        //java.text.DecimalFormat percentFormat =new java.text.DecimalFormat();
        //percentText = percentFormat.format(percent);

        DecimalFormat df = new DecimalFormat("0.00%");
        percentText = df.format(percent);
    }

}
