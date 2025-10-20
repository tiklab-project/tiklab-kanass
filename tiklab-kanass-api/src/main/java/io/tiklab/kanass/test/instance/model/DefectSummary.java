package io.tiklab.kanass.test.instance.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;

/**
 * 测试报告 模型
 */
@ApiModel
@Mapper
public class DefectSummary extends BaseModel {

    @ApiProperty(name="total",desc="总数")
    private Integer total = 0;

    @ApiProperty(name="progressStatusCount",desc="进行中的状态数")
    private Integer progressStatusCount = 0;

    @ApiProperty(name="doneStatusCount",desc="完成的状态数")
    private Integer doneStatusCount = 0;

    @ApiProperty(name="todoStatusCount",desc="代办的状态数")
    private Integer todoStatusCount = 0;


    @ApiProperty(name="lowCount",desc="优先级 低 的数量")
    private Integer lowCount = 0;

    @ApiProperty(name="mediumCount",desc="优先级 中 的数量")
    private Integer mediumCount = 0;

    @ApiProperty(name="highCount",desc="优先级 高 的数量")
    private Integer highCount = 0;;


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getProgressStatusCount() {
        return progressStatusCount;
    }

    public void setProgressStatusCount(Integer progressStatusCount) {
        this.progressStatusCount = progressStatusCount;
    }

    public Integer getDoneStatusCount() {
        return doneStatusCount;
    }

    public void setDoneStatusCount(Integer doneStatusCount) {
        this.doneStatusCount = doneStatusCount;
    }

    public Integer getTodoStatusCount() {
        return todoStatusCount;
    }

    public void setTodoStatusCount(Integer todoStatusCount) {
        this.todoStatusCount = todoStatusCount;
    }

    public Integer getLowCount() {
        return lowCount;
    }

    public void setLowCount(Integer lowCount) {
        this.lowCount = lowCount;
    }

    public Integer getMediumCount() {
        return mediumCount;
    }

    public void setMediumCount(Integer mediumCount) {
        this.mediumCount = mediumCount;
    }

    public Integer getHighCount() {
        return highCount;
    }

    public void setHighCount(Integer highCount) {
        this.highCount = highCount;
    }
}
