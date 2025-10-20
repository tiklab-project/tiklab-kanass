package io.tiklab.kanass.test.testplan.instance.model;

import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.beans.annotation.Mapper;

@Mapper
public class FunctionTestReport extends BaseModel {
    private Integer total;

    private Integer successCount;

    private Integer successRate;

    private Integer failCount;

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

    public Integer getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Integer successRate) {
        this.successRate = successRate;
    }
}
