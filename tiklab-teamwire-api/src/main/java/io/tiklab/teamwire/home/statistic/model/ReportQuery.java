package io.tiklab.teamwire.home.statistic.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.page.Page;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 报表查询参数模型
 */
@ApiModel
public class ReportQuery {
        @ApiProperty(name="reportType",desc="报表类型")
        private String reportType;

        @ApiProperty(name ="type",desc = "报表分类")
        private String type;

        @ApiProperty(name ="programId",desc = "报表所属项目集id")
        private String programId;

        @ApiProperty(name ="projectId",desc = "报表所属项目id")
        private String projectId;

        @ApiProperty(name ="sprintId",desc = "报表所属迭代id")
        private String sprintId;

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

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}