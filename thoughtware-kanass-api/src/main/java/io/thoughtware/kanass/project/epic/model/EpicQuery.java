package io.thoughtware.kanass.project.epic.model;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.core.page.Page;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 史诗查询模型
 */
@ApiModel
public class EpicQuery {
    @ApiProperty(name ="projectId",desc = "史诗名称")
    private String projectId;

    @ApiProperty(name ="epicName",desc = "史诗名称")
    private String epicName;

    @ApiProperty(name ="parentId",desc = "史诗父级")
    private String parentId;

    @ApiProperty(name ="epicParentNull",desc = "是否有父级")
    private Boolean epicParentNull;


    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("epicName").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getEpicName() {
        return epicName;
    }

    public void setEpicName(String epicName) {
        this.epicName = epicName;
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

    public Boolean getEpicParentNull() {
        return epicParentNull;
    }

    public void setEpicParentNull(Boolean epicParentNull) {
        this.epicParentNull = epicParentNull;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}