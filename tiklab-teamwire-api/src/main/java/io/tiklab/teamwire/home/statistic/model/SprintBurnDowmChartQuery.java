package io.tiklab.teamwire.home.statistic.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.page.Page;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;

import java.util.List;

/**
 * 迭代数据统计搜索模型
 */
@ApiModel
public class SprintBurnDowmChartQuery {

        @ApiProperty(name ="sprintId",desc = "迭代id")
        private String sprintId;
        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

        public String getSprintId() {
            return sprintId;
        }

        public void setSprintId(String sprintId) {
            this.sprintId = sprintId;
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