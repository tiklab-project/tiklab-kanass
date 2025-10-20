package io.tiklab.kanass.workitem.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 事项评论搜索条件模型
 */
@ApiModel
public class WorkItemRoleFunctionQuery {

        @ApiProperty(name="roleId",desc="角色id")
        private String roleId;

        @ApiProperty(name="workTypeId",desc="事项类型id")
        private String workTypeId;

        @ApiProperty(name="workTypeIds",desc="事项类型ids")
        private String[] workTypeIds;

        @ApiProperty(name="functionType",desc="功能类型")
        private String functionType;

        @ApiProperty(name="functionListId",desc="更新的功能的ids")
        private String[] functionListId;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();



        public String getWorkTypeId() {
                return workTypeId;
        }

        public void setWorkTypeId(String workTypeId) {
                this.workTypeId = workTypeId;
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

        public String getRoleId() {
                return roleId;
        }

        public void setRoleId(String roleId) {
                this.roleId = roleId;
        }

        public String getFunctionType() {
                return functionType;
        }

        public void setFunctionType(String functionType) {
                this.functionType = functionType;
        }

        public String[] getFunctionListId() {
                return functionListId;
        }

        public void setFunctionListId(String[] functionListId) {
                this.functionListId = functionListId;
        }

        public String[] getWorkTypeIds() {
                return workTypeIds;
        }

        public void setWorkTypeIds(String[] workTypeIds) {
                this.workTypeIds = workTypeIds;
        }
}