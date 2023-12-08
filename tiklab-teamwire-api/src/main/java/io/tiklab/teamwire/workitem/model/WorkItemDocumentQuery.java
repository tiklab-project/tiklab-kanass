package io.tiklab.teamwire.workitem.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;

import java.util.List;

/**
 * 事项文档查找条件模型
 */
@ApiModel
public class WorkItemDocumentQuery {
        @ApiProperty(name ="workItemId",desc = "事项id，精确匹配")
        private String workItemId;

        @ApiProperty(name ="documentId",desc = "文档id，精确匹配")
        private String documentId;

        @ApiProperty(name ="documentId",desc = "文档id，精确匹配")
        private String[] documentIds;

        //只用于project查询 sward 使用
        @ApiProperty(name ="name",desc = "文档名字")
        private String name;

        //只用于project查询 sward 使用
        @ApiProperty(name ="repositoryId",desc = "空间id")
        private String repositoryId;

        @ApiProperty(name ="repositoryIds",desc = "空间id")
        private String[] repositoryIds;

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

        public Page getPageParam() {
            return pageParam;
        }

        public void setPageParam(Page pageParam) {
            this.pageParam = pageParam;
        }

        public String getWorkItemId() {
            return workItemId;
        }

        public WorkItemDocumentQuery setWorkItemId(String workItemId) {
            this.workItemId = workItemId;
            return this;
        }

        public String getDocumentId() {
            return documentId;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRepositoryId() {
            return repositoryId;
        }

        public void setRepositoryId(String repositoryId) {
            this.repositoryId = repositoryId;
        }

        public String[] getRepositoryIds() {
            return repositoryIds;
        }

        public void setRepositoryIds(String[] repositoryIds) {
            this.repositoryIds = repositoryIds;
        }

    public String[] getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(String[] documentIds) {
        this.documentIds = documentIds;
    }
}