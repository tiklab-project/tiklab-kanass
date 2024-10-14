package io.tiklab.kanass.workitem.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.page.Page;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;

import java.util.List;

/**
 * 事项评论搜索条件模型
 */
@ApiModel
public class WorkCommentQuery {
        @ApiProperty(name ="workItemId",desc = "事项ID，精确匹配")
        private String workItemId;

        @ApiProperty(name ="workItemIds",desc = "事项ID，精确匹配")
        private String[] workItemIds;

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

        public String getWorkItemId() {
                return workItemId;
        }

        public void setWorkItemId(String workItemId) {
                this.workItemId = workItemId;
        }

        public Page getPageParam() {
            return pageParam;
        }

        public void setPageParam(Page pageParam) {
            this.pageParam = pageParam;
        }

        public String[] getWorkItemIds() {
                return workItemIds;
        }

        public void setWorkItemIds(String[] workItemIds) {
                this.workItemIds = workItemIds;
        }
}