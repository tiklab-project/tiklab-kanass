package io.tiklab.kanass.project.test.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 计划搜索参数模型
 */
@ApiModel
public class ProjectTestRepositoryPlanQuery {

        @ApiProperty(name ="projectId",desc = "项目id,精确匹配")
        private String projectId;

        @ApiProperty(name ="testRepositoryId",desc = "项目id,精确匹配")
        private String testRepositoryId;

        @ApiProperty(name ="planId",desc = "测试计划id,精确匹配")
        private String planId;

        @ApiProperty(name ="planIds",desc = "测试计划id,精确匹配")
        private String[] planIds;

        @ApiProperty(name ="name",desc = "计划名称,模糊匹配")
        private String name;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();


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

        public String getTestRepositoryId() {
                return testRepositoryId;
        }

        public void setTestRepositoryId(String testRepositoryId) {
                this.testRepositoryId = testRepositoryId;
        }

        public String getPlanId() {
                return planId;
        }

        public void setPlanId(String planId) {
                this.planId = planId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String[] getPlanIds() {
                return planIds;
        }

        public void setPlanIds(String[] planIds) {
                this.planIds = planIds;
        }
}