package io.tiklab.kanass.project.worklog.model;

import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

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
