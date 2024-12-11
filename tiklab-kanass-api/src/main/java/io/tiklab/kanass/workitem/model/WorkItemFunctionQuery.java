package io.tiklab.kanass.workitem.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 事项评论搜索条件模型
 */
@ApiModel
public class WorkItemFunctionQuery {
        private String id;

        private String name;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("name").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();


        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
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