package io.tiklab.teamwire.workitem.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 事项状态实例
 */
@Entity @Table(name="pmc_work_status")
public class WorkStatusEntity implements Serializable {

    @Id
    @GeneratorValue(length = 8)
    @Column(name = "id",length = 8)
    private String id;

    // 事项状态名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    // 事项描述描述
    @Column(name = "description",length = 64)
    private String desc;

    // 排序
    @Column(name = "sort",length = 4)
    private Integer sort;

    // 事项状态分组 系统，自定义
    @Column(name = "grouper",length = 32,notNull = true)
    private String group;

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
