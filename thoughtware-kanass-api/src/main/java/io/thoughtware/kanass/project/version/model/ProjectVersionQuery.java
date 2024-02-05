package io.thoughtware.kanass.project.version.model;

import io.thoughtware.core.page.Page;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;

import java.util.List;

/**
 * 项目版本搜索模型
 */
@ApiModel
public class ProjectVersionQuery {

    @ApiProperty(name ="projectId",desc = "所属项目ID，精确匹配")
    private String projectId;

    @ApiProperty(name ="currentVersionId",desc = "所属项目ID，精确匹配")
    private String currentVersionId;
    @ApiProperty(name ="name",desc = "版本名称，模糊匹配")
    private String name;

    @ApiProperty(name ="versionState",desc = "版本名称，模糊匹配")
    private String versionState;

    @ApiProperty(name ="masterId",desc = "版本名称，模糊匹配")
    private String masterId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getVersionState() {
        return versionState;
    }

    public void setVersionState(String versionState) {
        this.versionState = versionState;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getCurrentVersionId() {
        return currentVersionId;
    }

    public void setCurrentVersionId(String currentVersionId) {
        this.currentVersionId = currentVersionId;
    }
}