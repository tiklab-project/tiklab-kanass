package io.tiklab.teamwire.project.stage.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 项目阶段查找参数模型
 */
@ApiModel
public class StageQuery {
        @ApiProperty(name ="projectId",desc = "阶段名称，模糊匹配")
        private String projectId;
    
        @ApiProperty(name ="stageName",desc = "阶段名称，模糊匹配")
        private String stageName;
    
        @ApiProperty(name ="parentId",desc = "阶段父级，模糊匹配")
        private String parentId;

        @ApiProperty(name ="stageParentNull",desc = "是否有父级")
        private Boolean stageParentNull;

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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean getStageParentNull() {
        return stageParentNull;
    }

    public void setStageParentNull(Boolean stageParentNull) {
        this.stageParentNull = stageParentNull;
    }
}