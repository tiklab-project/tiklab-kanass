package io.tiklab.kanass.home.statistic.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 项目集的事项动态查询模型
 */
@ApiModel
public class ProjectSetBurnDowmChartQuery {
        @ApiProperty(name ="projectSetId",desc = "所属项目集ID，精确匹配")
        private String projectSetId;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("recordTime").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

        public String getProjectSetId() {
                return projectSetId;
        }

        public void setProjectSetId(String projectSetId) {
                this.projectSetId = projectSetId;
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