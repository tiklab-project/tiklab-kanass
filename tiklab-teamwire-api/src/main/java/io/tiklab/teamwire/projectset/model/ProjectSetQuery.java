package io.tiklab.teamwire.projectset.model;

import io.tiklab.core.page.Page;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 项目集查询参数模型
 */
@ApiModel
public class ProjectSetQuery {
        @ApiProperty(name ="name",desc = "项目集名称，模糊匹配")
        private String name;

        @ApiProperty(name ="master",desc = "项目集负责人id，精确匹配")
        private String master;

        @ApiProperty(name = "creator",desc = "项目集创建者")
        private String creator;

        @ApiProperty(name ="recentMasterId",desc = "点击人")
        private String recentMasterId;

        @ApiProperty(name ="recentMasterId",desc = "点击人")
        private String focusMasterId;

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

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getMaster() {
                return master;
        }

        public void setMaster(String master) {
                this.master = master;
        }

        @Override
        public String toString() {
                return "ProjectSetQuery{" +
                        "orderParams=" + orderParams +
                        ", pageParam=" + pageParam +
                        ", name='" + name + '\'' +
                        ", master='" + master + '\'' +
                        '}';
        }

        public String getCreator() {
                return creator;
        }

        public void setCreator(String creator) {
                this.creator = creator;
        }

        public String getRecentMasterId() {
                return recentMasterId;
        }

        public void setRecentMasterId(String recentMasterId) {
                this.recentMasterId = recentMasterId;
        }

        public String getFocusMasterId() {
                return focusMasterId;
        }

        public void setFocusMasterId(String focusMasterId) {
                this.focusMasterId = focusMasterId;
        }
}