package io.thoughtware.kanass.project.stage.model;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.core.page.Page;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 项目阶段查找参数模型
 */
@ApiModel
public class StageQuery {

    @ApiProperty(name ="id",desc = "阶段id")
    private String id;
    @ApiProperty(name ="projectId",desc = "阶段名称，模糊匹配")
    private String projectId;

    @ApiProperty(name ="stageName",desc = "阶段名称，模糊匹配")
    private String stageName;

    @ApiProperty(name ="parentId",desc = "阶段父级，模糊匹配")
    private String parentId;

    @ApiProperty(name ="stageParentNull",desc = "是否有父级")
    private Boolean stageParentNull;

    @ApiProperty(name ="rootId",desc = "是否有父级")
    private String rootId;

    @ApiProperty(name ="rootIds",desc = "是否有父级")
    private String[] rootIds;

    @ApiProperty(name ="deep",desc = "深度")
    private Integer deep;

    @ApiProperty(name ="deeps",desc = "深度s")
    private Integer[] deeps;

    @ApiProperty(name ="tree_path",desc = "是否有父级")
    private String treePath;

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

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    public String[] getRootIds() {
        return rootIds;
    }

    public void setRootIds(String[] rootIds) {
        this.rootIds = rootIds;
    }

    public Integer getDeep() {
        return deep;
    }

    public void setDeep(Integer deep) {
        this.deep = deep;
    }

    public Integer[] getDeeps() {
        return deeps;
    }

    public void setDeeps(Integer[] deeps) {
        this.deeps = deeps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }
}