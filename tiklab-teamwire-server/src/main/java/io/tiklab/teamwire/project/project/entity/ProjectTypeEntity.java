package io.tiklab.teamwire.project.project.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;
import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 项目类型实例
 */
@Entity
@Table(name="pmc_project_type")
public class ProjectTypeEntity implements Serializable {

    @Id
    @GeneratorValue(length = 8)
    @Column(name = "id",length = 8)
    private String id;

    // 类型编码 scrum nomal
    @Column(name = "type",length = 64)
    private String type;

    // 项目类型名字
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    //项目类型图标地址
    @Column(name = "icon_url",length = 64)
    private String iconUrl = "";

    // 描述
    @Column(name = "description",length = 2048)
    private String desc;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
