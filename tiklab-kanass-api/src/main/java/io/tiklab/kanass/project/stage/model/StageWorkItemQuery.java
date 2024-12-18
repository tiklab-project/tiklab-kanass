package io.tiklab.kanass.project.stage.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 阶段事项查找参数模型
 */
@ApiModel
public class StageWorkItemQuery {

    @ApiProperty(name ="stageId",desc = "所属阶段ID，精确匹配")
    private String stageId;

    @ApiProperty(name ="stageIds",desc = "所属阶段ID，精确匹配")
    private String[] stageIds;
    @ApiProperty(name ="workItemId",desc = "所属阶段ID，精确匹配")
    private String workItemId;

    @ApiProperty(name ="workItemName",desc = "所属阶段ID，精确匹配")
    private String workItemName;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public List<Order> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(List<Order> orderParams) {
        this.orderParams = orderParams;
    }

    public Page getPageParam() {
        return pageParam;
    }

    public void setPageParam(Page pageParam) {
        this.pageParam = pageParam;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getWorkItemName() {
        return workItemName;
    }

    public void setWorkItemName(String workItemName) {
        this.workItemName = workItemName;
    }

    public String[] getStageIds() {
        return stageIds;
    }

    public void setStageIds(String[] stageIds) {
        this.stageIds = stageIds;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }
}