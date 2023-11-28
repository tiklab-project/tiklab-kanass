package io.tiklab.teamwire.project.worklog.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 日历视图模型
 */
@ApiModel
public class CalendarHeader {
    @ApiProperty(name = "dateTime", desc = "时间")
    private String dateTime;

    @ApiProperty(name = "weekDay", desc = "周几")
    private String weekDay;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }
}
