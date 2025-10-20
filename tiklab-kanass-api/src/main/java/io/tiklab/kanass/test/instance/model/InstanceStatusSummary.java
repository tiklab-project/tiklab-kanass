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
public class InstanceStatusSummary extends BaseModel {

    @ApiProperty(name="total",desc="总数")
    private Integer total = 0;

    @ApiProperty(name="failCount",desc="失败数")
    private Integer failCount = 0;

    @ApiProperty(name="successCount",desc="成功数")
    private Integer successCount = 0;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }
}
