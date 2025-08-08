package io.tiklab.kanass.test.func.cases.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class FuncUnitStepQuery {

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name = "id", desc = "接口步骤ID，精确匹配")
    private String id;

    @ApiProperty(name = "funcUnitId", desc = "用例id精确匹配")
    private String funcUnitId;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuncUnitId() {
        return funcUnitId;
    }

    public void setFuncUnitId(String funcUnitId) {
        this.funcUnitId = funcUnitId;
    }
}