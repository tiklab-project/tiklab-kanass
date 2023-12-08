package io.thoughtware.kanass.projectset.model;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.core.page.Page;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 收藏的项目集查询条件模板
 */
@ApiModel
public class ProjectSetFocusQuery {

        @ApiProperty(name ="masterId",desc = "收藏者id")
        private String masterId;

        @ApiProperty(name ="projectSetId",desc = "项目集id")
        private String projectSetId;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

        public String getMasterId() {
            return masterId;
        }

        public void setMasterId(String masterId) {
            this.masterId = masterId;
        }

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