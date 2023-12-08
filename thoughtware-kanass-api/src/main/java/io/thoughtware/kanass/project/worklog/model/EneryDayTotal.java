package io.thoughtware.kanass.project.worklog.model;

import io.thoughtware.join.annotation.Join;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

/**
 * 每天统计总数
 */
@ApiModel
@Join
public class EneryDayTotal {
    @ApiProperty(name = "statistics", desc = "统计")
    private Integer statistic;

    public Integer getStatistics() {
        return statistic;
    }

    public void setStatistic(Integer statistic) {
        this.statistic = statistic;
    }
}
