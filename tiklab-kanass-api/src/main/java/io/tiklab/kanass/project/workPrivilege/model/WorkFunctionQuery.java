package io.tiklab.kanass.project.workPrivilege.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 优先级搜索添加模型
 */
@ApiModel
public class WorkFunctionQuery {

    @ApiProperty(name ="parentIdIsNull", desc = "versionId是否为空,true:为空")
    private Boolean parentIdIsNull;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public Boolean getParentIdIsNull() {
        return parentIdIsNull;
    }

    public void setParentIdIsNull(Boolean parentIdIsNull) {
        this.parentIdIsNull = parentIdIsNull;
    }

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


}