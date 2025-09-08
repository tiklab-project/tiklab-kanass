package io.tiklab.kanass.project.wiki.model;

import io.tiklab.core.BaseModel;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class NodeQuery extends BaseModel {
    @ApiProperty(name ="shareId",desc = "空间id")
    private String shareId;

    @ApiProperty(name ="id",desc = "node id")
    private String id;

    @ApiProperty(name ="Ids",desc = "空间id")
    private Object[] Ids;

    @ApiProperty(name ="notIds",desc = "空间id")
    private Object[] notIds;

    @ApiProperty(name ="repositoryId",desc = "空间id")
    private String repositoryId;

    @ApiProperty(name ="repositoryIds",desc = "空间ids")
    private String[] repositoryIds;

    @ApiProperty(name ="parentId",desc = "父级目录id")
    private String parentId;

    @ApiProperty(name ="name",desc = "name")
    private String name;

    @ApiProperty(name ="parentIds",desc = "父级目录ids")
    private String[] parentIds;

    @ApiProperty(name ="dimension",desc = "层级")
    private Integer dimension;
    @ApiProperty(name ="dimensions",desc = "层级")
    private Integer[] dimensions;

    @ApiProperty(name ="parentIsNull",desc = "是否为空")
    private Boolean parentIsNull;

    @ApiProperty(name ="treePath",desc = "路径")
    private String treePath;

    @ApiProperty(name ="masterId",desc = "负责人")
    private String masterId;

    @ApiProperty(name ="type",desc = "类型")
    private String type;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().desc("sort").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    // 1草稿  2评审中  3评审通过
    @ApiProperty(name ="nodeStatus",desc = "节点状态")
    private Integer nodeStatus;

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

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }



    public Boolean getParentIsNull() {
        return parentIsNull;
    }

    public void setParentIsNull(Boolean parentIsNull) {
        this.parentIsNull = parentIsNull;
    }

    public Integer[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(Integer[] dimensions) {
        this.dimensions = dimensions;
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public String[] getParentIds() {
        return parentIds;
    }

    public void setParentIds(String[] parentIds) {
        this.parentIds = parentIds;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String[] getRepositoryIds() {
        return repositoryIds;
    }

    public void setRepositoryIds(String[] repositoryIds) {
        this.repositoryIds = repositoryIds;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public Object[] getIds() {
        return Ids;
    }

    public void setIds(Object[] ids) {
        Ids = ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object[] getNotIds() {
        return notIds;
    }

    public void setNotIds(Object[] notIds) {
        this.notIds = notIds;
    }

    public Integer getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(Integer nodeStatus) {
        this.nodeStatus = nodeStatus;
    }
}