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
public class WorkItemRoleFunctionDmQuery {

        @ApiProperty(name="domainId",desc="domainId")
        private String domainId;

        @ApiProperty(name="workTypeId",desc="事项类型id")
        private String workTypeId;

        @ApiProperty(name="functionType",desc="功能id")
        private String functionType;


        @ApiProperty(name="roleId",desc="角色id")
        private String roleId;

        @ApiProperty(name="roleIds",desc="角色ids")
        private String[] roleIds;

        @ApiProperty(name="userId",desc="人员id")
        private String userId;

        @ApiProperty(name="workId",desc="事项id")
        private String workId;

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

        public String getWorkTypeId() {
                return workTypeId;
        }

        public void setWorkTypeId(String workTypeId) {
                this.workTypeId = workTypeId;
        }

        public String getDomainId() {
                return domainId;
        }

        public void setDomainId(String domainId) {
                this.domainId = domainId;
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

        public String getUserId() {
                return userId;
        }

        public void setUserId(String userId) {
                this.userId = userId;
        }

        public String getWorkId() {
                return workId;
        }

        public void setWorkId(String workId) {
                this.workId = workId;
        }

        public String[] getRoleIds() {
                return roleIds;
        }

        public void setRoleIds(String[] roleIds) {
                this.roleIds = roleIds;
        }
}