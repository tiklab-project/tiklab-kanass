package io.tiklab.kanass.workitem.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 事项类型模型
 */

@ApiModel
@Join
@Mapper
public class WorkProductPlan extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

//    @NotNull
    @ApiProperty(name="workItemId",desc="事项id",required = true)
    private String workItemId;

    // 多个事项ID
    private List<String> workItemIds;

    @NotNull
    @ApiProperty(name="productPlanId",desc="产品计划id",required = true)
    private String productPlanId;

    // null表示没有迁移   有id表示迁移的目标产品计划
    @ApiProperty(name="targetProductPlanId",desc="迁移的目标产品计划id")
    private String targetProductPlanId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public String getProductPlanId() {
        return productPlanId;
    }

    public void setProductPlanId(String productPlanId) {
        this.productPlanId = productPlanId;
    }

    public String getTargetProductPlanId() {
        return targetProductPlanId;
    }

    public void setTargetProductPlanId(String targetProductPlanId) {
        this.targetProductPlanId = targetProductPlanId;
    }

    public List<String> getWorkItemIds() {
        return workItemIds;
    }

    public void setWorkItemIds(List<String> workItemIds) {
        this.workItemIds = workItemIds;
    }
}
