package io.thoughtware.kanass.project.wiki.model;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.core.page.Page;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 计划搜索参数模型
 */
@ApiModel
public class ProjectWikiRepositoryQuery {

        @ApiProperty(name ="projectId",desc = "项目id,精确匹配")
        private String projectId;

        @ApiProperty(name ="wikiRepositoryId",desc = "项目id,精确匹配")
        private String wikiRepositoryId;

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

        public String getWikiRepositoryId() {
                return wikiRepositoryId;
        }

        public void setWikiRepositoryId(String wikiRepositoryId) {
                this.wikiRepositoryId = wikiRepositoryId;
        }
}