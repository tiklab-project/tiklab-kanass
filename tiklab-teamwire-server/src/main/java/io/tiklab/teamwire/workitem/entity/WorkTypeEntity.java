package io.tiklab.teamwire.workitem.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 事项类型实例
 */
@Entity
@Table(name="pmc_work_type")
public class WorkTypeEntity implements Serializable {

    @Id
    @GeneratorValue(length = 8)
    @Column(name = "id",length = 8)
    private String id;

    // 事项类型名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    // 事项类型描述
    @Column(name = "description",length = 64)
    private String desc;

    // 事项类型排序
    @Column(name = "sort",length = 4)
    private Integer sort;

    //
    @Column(name = "scope",length = 32)
    private Integer scope = 0;

    // 事项关联表单
    @Column(name = "form_id",length = 32)
    private String formId;

    // 事项关联流程
    @Column(name = "flow_id",length = 32)
    private String flowId;

    @Column(name = "icon_url",length = 64)
    private String iconUrl;

    @Column(name = "grouper",length = 64)
    private String grouper;

    @Column(name = "code",length = 32)
    private String code;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrouper() {
        return grouper;
    }

    public void setGrouper(String grouper) {
        this.grouper = grouper;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }
}
