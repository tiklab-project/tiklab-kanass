package io.tiklab.kanass.project.appraised.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 项目评审查找参数模型
 */
@ApiModel
public class AppraisedQuery {

    @ApiProperty(name ="id",desc = "评审id，精确匹配")
    private String id;

    @ApiProperty(name ="ids",desc = "评审id列表，精确匹配")
    private List<String> ids;
    
    @ApiProperty(name ="projectId",desc = "所属项目ID，精确匹配")
    private String projectId;
    
    @ApiProperty(name ="name",desc = "评审名称，模糊匹配")
    private String name;

    @ApiProperty(name ="appraisedState",desc = "评审状态")
    private String appraisedState;

    @ApiProperty(name ="appraisedStates",desc = "评审状态列表")
    private String[] appraisedStates;


    @ApiProperty(name ="masterId",desc = "评审负责人")
    private String masterId;

    @ApiProperty(name ="builderId",desc = "评审创建人")
    private String builderId;


    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

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

    public String getAppraisedState() {
        return appraisedState;
    }

    public void setAppraisedState(String appraisedState) {
        this.appraisedState = appraisedState;
    }

    public String[] getAppraisedStates() {
        return appraisedStates;
    }

    public void setAppraisedStates(String[] appraisedStates) {
        this.appraisedStates = appraisedStates;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getBuilderId() {
        return builderId;
    }

    public void setBuilderId(String builderId) {
        this.builderId = builderId;
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
}