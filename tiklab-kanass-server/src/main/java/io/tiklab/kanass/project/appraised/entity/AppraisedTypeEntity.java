package io.tiklab.kanass.project.appraised.entity;

import io.tiklab.dal.jpa.annotation.*;

@Entity
@Table(name="pmc_appraised_type")
public class AppraisedTypeEntity {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 评审类型名称
    @Column(name = "name",length = 64)
    private String name;

    // 评审类型描述
    @Column(name = "description",length = 64)
    private String description;

    // 评审类型排序
    @Column(name = "sort")
    private Integer sort;

    // 分组
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
