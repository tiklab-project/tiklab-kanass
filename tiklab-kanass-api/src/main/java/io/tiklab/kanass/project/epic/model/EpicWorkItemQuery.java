package io.tiklab.kanass.project.epic.model;

import io.tiklab.core.page.Page;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 史诗事项搜索模型
 */
@ApiModel
public class EpicWorkItemQuery {

        @ApiProperty(name ="epicId",desc = "所属史诗ID，精确匹配")
        private String epicId;

        @ApiProperty(name ="workItemName",desc = "事项名字，精确匹配")
        private String workItemName;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("epicId").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

        public String getEpicId() {
                return epicId;
        }

        public void setEpicId(String epicId) {
                this.epicId = epicId;
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

        public String getWorkItemName() {
                return workItemName;
        }

        public void setWorkItemName(String workItemName) {
                this.workItemName = workItemName;
        }
}