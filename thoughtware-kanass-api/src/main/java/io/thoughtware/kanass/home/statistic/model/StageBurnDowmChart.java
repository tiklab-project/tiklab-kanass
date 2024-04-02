package io.thoughtware.kanass.home.statistic.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.join.annotation.Join;

import javax.validation.constraints.NotNull;

/**
 * 版本事项数据动态统计模型
 */
@ApiModel
@Mapper
@Join
public class StageBurnDowmChart extends BaseModel{

    @ApiProperty(name="id",desc="id",required = true)
    private String id;

    @NotNull
    @ApiProperty(name="stageId",desc="版本名称",required = true)
    private String stageId;

    @ApiProperty(name="recordTime",desc="记录时间")
    private String recordTime;

    @ApiProperty(name="remainWorkitemCount",desc="未完成事项数量")
    private Integer remainWorkitemCount;

    @ApiProperty(name="totalWorkitemCount",desc="全部事项数量")
    private Integer totalWorkitemCount;

    @ApiProperty(name="endWorkitemCount",desc="完成事项数量")
    private Integer endWorkitemCount;

    @ApiProperty(name="progressWorkitemCount",desc="进行中事项数量")
    private Integer progressWorkitemCount;

    @ApiProperty(name="noStartWorkitemCount",desc="未开始事项数量")
    private Integer noStartWorkitemCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getRemainWorkitemCount() {
        return remainWorkitemCount;
    }

    public void setRemainWorkitemCount(Integer remainWorkitemCount) {
        this.remainWorkitemCount = remainWorkitemCount;
    }

    public Integer getTotalWorkitemCount() {
        return totalWorkitemCount;
    }

    public void setTotalWorkitemCount(Integer totalWorkitemCount) {
        this.totalWorkitemCount = totalWorkitemCount;
    }

    public Integer getEndWorkitemCount() {
        return endWorkitemCount;
    }

    public void setEndWorkitemCount(Integer endWorkitemCount) {
        this.endWorkitemCount = endWorkitemCount;
    }

    public Integer getProgressWorkitemCount() {
        return progressWorkitemCount;
    }

    public void setProgressWorkitemCount(Integer progressWorkitemCount) {
        this.progressWorkitemCount = progressWorkitemCount;
    }

    public Integer getNoStartWorkitemCount() {
        return noStartWorkitemCount;
    }

    public void setNoStartWorkitemCount(Integer noStartWorkitemCount) {
        this.noStartWorkitemCount = noStartWorkitemCount;
    }
}
