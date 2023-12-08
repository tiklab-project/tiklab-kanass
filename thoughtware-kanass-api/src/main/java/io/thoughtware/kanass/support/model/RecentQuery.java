package io.thoughtware.kanass.support.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.page.Page;
import io.thoughtware.core.order.OrderBuilders;


import java.util.List;

/**
 * 查找最近访问的添加模型
 */
@ApiModel
public class RecentQuery {
        @ApiProperty(name ="modelId",desc = "所属模型ID，精确匹配")
        private String modelId;

        @ApiProperty(name ="masterId",desc = "点击人，精确匹配")
        private String masterId;

        @ApiProperty(name ="model",desc = "所属模型，精确匹配")
        private String model;

        @ApiProperty(name ="projectId",desc = "所属模型，精确匹配")
        private String projectId;
        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().desc("recentTime").get();

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

        public String getModelId() {
            return modelId;
        }

        public void setModelId(String modelId) {
            this.modelId = modelId;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}