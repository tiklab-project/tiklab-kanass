package io.tiklab.kanass.workitem.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="pmc_work_item_function")
public class WorkItemFunctionEntity implements Serializable {
    @Id
    @GeneratorValue(length = 8)
    @Column(name = "id",length = 8)
    private String id;

    // 优先级名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;


    @Column(name = "code",length = 64)
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
