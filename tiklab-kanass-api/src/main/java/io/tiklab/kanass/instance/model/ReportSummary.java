package io.tiklab.kanass.instance.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;

/**
 * 测试报告 模型
 */
@ApiModel
@Mapper
public class ReportSummary extends BaseModel {

    @ApiProperty(name="instanceStatusSummary",desc="历史状态统计")
    private InstanceStatusSummary instanceStatusSummary;

    @ApiProperty(name="defectSummary",desc="缺陷统计")
    private DefectSummary defectSummary;


    public InstanceStatusSummary getInstanceStatusSummary() {
        return instanceStatusSummary;
    }

    public void setInstanceStatusSummary(InstanceStatusSummary instanceStatusSummary) {
        this.instanceStatusSummary = instanceStatusSummary;
    }

    public DefectSummary getDefectSummary() {
        return defectSummary;
    }

    public void setDefectSummary(DefectSummary defectSummary) {
        this.defectSummary = defectSummary;
    }
}
