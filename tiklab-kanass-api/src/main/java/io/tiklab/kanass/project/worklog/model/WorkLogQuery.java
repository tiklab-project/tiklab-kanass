package io.tiklab.kanass.project.worklog.model;

import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;

import java.util.Date;
import java.util.List;

/**
 * 工作日志搜索参数模型
 */
@ApiModel
public class WorkLogQuery {

    @ApiProperty(name ="workItemId",desc = "事项ID，精确匹配")
    private String workItemId;

    @ApiProperty(name ="workItemIds",desc = "事项ID，精确匹配")
    private String[] workItemIds;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("workItemId").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name ="userName",desc = "记录人id")
    private  String worker;

    @ApiProperty(name ="workers",desc = "记录人id集合")
    private  String[] workers;

    @ApiProperty(name ="projectId",desc = "项目id，精确匹配")
    private String projectId;

    @ApiProperty(name ="projectSetId",desc = "项目id，精确匹配")
    private String projectSetId;

    @ApiProperty(name ="startTime",desc="开始时间")
    private Date startTime;

    @ApiProperty(name ="endTime",desc="结束时间")
    private Date endTime;

    @ApiProperty(name ="pageNum",desc="每页展示数量 (还只是用于工时统计页)")
    private Integer pageNum=10;

    @ApiProperty(name ="currentPage",desc="当前页 （只是用于工时统计页）")
    private Integer currentPage;

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
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

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String[] getWorkers() {
        return workers;
    }
    public void setWorkers(String[] workers) {
        this.workers = workers;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getProjectSetId() {
        return projectSetId;
    }

    public void setProjectSetId(String projectSetId) {
        this.projectSetId = projectSetId;
    }

    public String[] getWorkItemIds() {
        return workItemIds;
    }

    public void setWorkItemIds(String[] workItemIds) {
        this.workItemIds = workItemIds;
    }
}