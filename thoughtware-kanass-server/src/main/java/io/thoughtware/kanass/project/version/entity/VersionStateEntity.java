package io.thoughtware.kanass.project.version.entity;


import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 迭代状态实例
 */
@Entity
@Table(name="pmc_version_status ")
public class VersionStateEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 迭代状态名称
    @Column(name = "name",length = 64)
    private String name;

    // 迭代状态描述
    @Column(name = "description",length = 64)
    private String description;

    // 迭代状态排序
    @Column(name = "sort")
    private Integer sort;

    // 迭代分组
    @Column(name = "grouper",length = 32)
    private String grouper;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getGrouper() {
        return grouper;
    }

    public void setGrouper(String grouper) {
        this.grouper = grouper;
    }

}
