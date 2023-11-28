package io.tiklab.teamwire.workitem.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 事项优先级实例
 */
@Entity
@Table(name="pmc_work_priority")
public class WorkPriorityEntity implements Serializable {

    @Id
    @GeneratorValue(length = 8)
    @Column(name = "id",length = 8)
    private String id;

    // 优先级名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    // 优先级描述
    @Column(name = "description",length = 64)
    private String desc;

    // 优先级排序
    @Column(name = "sort",length = 4)
    private Integer sort;

    // 优先级分组，系统，自定义
    @Column(name = "grouper",length = 32,notNull = true)
    private String group;

    // 优先级图标
    @Column(name = "icon_url",length = 64)
    private String iconUrl = "";

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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
