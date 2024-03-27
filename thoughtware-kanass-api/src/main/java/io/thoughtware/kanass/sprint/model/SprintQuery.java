package io.thoughtware.kanass.sprint.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.core.page.Page;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;

import java.util.List;

/**
 * 迭代查询模型
 */
@ApiModel
public class SprintQuery {

    @ApiProperty(name ="projectId",desc = "所属项目ID，精确匹配")
    private String projectId;

    @ApiProperty(name ="master",desc = "所属负责人，精确匹配")
    private String master;

    @ApiProperty(name ="builderId",desc = "所属负责人，精确匹配")
    private String builderId;

    @ApiProperty(name ="sprintName",desc = "迭代名称，模糊匹配")
    private String sprintName;

    @ApiProperty(name ="followersId",desc = "迭代关注者id")
    private String followersId;

    @ApiProperty(name ="sprintStateId",desc = "迭代状态id，模糊匹配")
    private String sprintStateId;

    @ApiProperty(name ="sprintStateIds",desc = "迭代状态id，模糊匹配")
    private String[] sprintStateIds;

    @ApiProperty(name ="currentSprintId",desc = "迭代状态id，模糊匹配")
    private String currentSprintId;

    @ApiProperty(name ="notSprintStateId",desc = "迭代状态id，模糊匹配")
    private String notSprintStateId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("sprintName").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
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

    public String getSprintStateId() {
        return sprintStateId;
    }

    public void setSprintStateId(String sprintStateId) {
        this.sprintStateId = sprintStateId;
    }

    public String getCurrentSprintId() {
        return currentSprintId;
    }

    public void setCurrentSprintId(String currentSprintId) {
        this.currentSprintId = currentSprintId;
    }

    public String getNotSprintStateId() {
        return notSprintStateId;
    }

    public void setNotSprintStateId(String notSprintStateId) {
        this.notSprintStateId = notSprintStateId;
    }

    public String[] getSprintStateIds() {
        return sprintStateIds;
    }

    public void setSprintStateIds(String[] sprintStateIds) {
        this.sprintStateIds = sprintStateIds;
    }

    public String getBuilderId() {
        return builderId;
    }

    public void setBuilderId(String builderId) {
        this.builderId = builderId;
    }

    public String getFollowersId() {
        return followersId;
    }

    public void setFollowersId(String followersId) {
        this.followersId = followersId;
    }
}
