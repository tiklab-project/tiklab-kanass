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
public class ProjectTestRepositoryQuery {

        @ApiProperty(name ="projectId",desc = "项目id,精确匹配")
        private String projectId;

        @ApiProperty(name ="testRepositoryId",desc = "项目id,精确匹配")
        private String testRepositoryId;

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
}