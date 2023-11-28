package io.tiklab.teamwire.home.insight.model;


import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 仪表盘查询参数模型
 */
@ApiModel
public class InsightQuery {
    @ApiProperty(name="insightName",desc="仪表盘名称")
    private String insightName;

    @ApiProperty(name="insightId",desc="仪表盘名称")
    private String insightId;
    @ApiProperty(name="masterId",desc="最近查看仪表盘的人员id")
    private String masterId;
    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("insightName").get();

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

    public String getInsightName() {
        return insightName;
    }

    public void setInsightName(String insightName) {
        this.insightName = insightName;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getInsightId() {
        return insightId;
    }

    public void setInsightId(String insightId) {
        this.insightId = insightId;
    }
}