package io.thoughtware.kanass.project.plan.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.core.page.Page;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;

import java.util.List;

/**
 * 计划搜索参数模型
 */
@ApiModel
public class PlanQuery {

        @ApiProperty(name ="planId",desc = "计划id，精准匹配")
        private String id;

        @ApiProperty(name ="planName",desc = "计划名称，模糊匹配")
        private String planName;

        @ApiProperty(name ="projectId",desc = "项目集id,精确匹配")
        private String projectId;

        @ApiProperty(name ="parentIdIsNull",desc = "parentId是否为空,true:为空")
        private Boolean parentIdIsNull;

        @ApiProperty(name ="parentId",desc = "父事项ID，精确匹配")
        private String parentId;

        @ApiProperty(name ="parentIdIn",desc = "父事项ID in范围，范围匹配")
        private String[] parentIdIn;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();


        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getPlanName() {
                return planName;
        }

        public void setPlanName(String planName) {
                this.planName = planName;
        }

        public String getProjectId() {
                return projectId;
        }

        public void setProjectId(String projectId) {
                this.projectId = projectId;
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

        public Boolean getParentIdIsNull() {
                return parentIdIsNull;
        }

        public void setParentIdIsNull(Boolean parentIdIsNull) {
                this.parentIdIsNull = parentIdIsNull;
        }

        public String getParentId() {
                return parentId;
        }

        public void setParentId(String parentId) {
                this.parentId = parentId;
        }

        public String[] getParentIdIn() {
                return parentIdIn;
        }

        public void setParentIdIn(String[] parentIdIn) {
                this.parentIdIn = parentIdIn;
        }
}