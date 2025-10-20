package io.tiklab.kanass.test.testplan.instance.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能测试类型计划的测试报告 实体
 */
@Entity
public class FunctionTestReportEntity implements Serializable {

    @Column(name = "total")
    private Integer total;

    @Column(name = "success_count")
    private Integer successCount;

    @Column(name = "fail_count")
    private Integer failCount;

    @Column(name = "not_executed_count")
    private Integer notExecutedCount;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Integer getNotExecutedCount() {
        return notExecutedCount;
    }

    public void setNotExecutedCount(Integer notExecutedCount) {
        this.notExecutedCount = notExecutedCount;
    }
}
