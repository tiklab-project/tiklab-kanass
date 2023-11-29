package io.tiklab.teamwire.project.wiki.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.user.user.model.User;

import java.sql.Timestamp;

@ApiModel
@Join
@Mapper
public class WikiRepository extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;


    @ApiProperty(name="name",desc="name")
    private String name;

    //@NotNull
    @ApiProperty(name="typeId",desc="typeId")
    private String typeId="1";

    @ApiProperty(name="master",desc="负责人",eg="@selectOne")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    //权限范围 0 全部可见 1成员可见
    @ApiProperty(name="limits",desc="limits")
    private String limits;

    @ApiProperty(name="iconUrl",desc="iconUrl")
    private String iconUrl;

    @ApiProperty(name="categoryNum",desc="iconUrl")
    private Integer categoryNum;

    @ApiProperty(name="documentNum",desc="iconUrl")
    private Integer documentNum;



    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="desc",desc="desc")
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
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public Integer getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(Integer categoryNum) {
        this.categoryNum = categoryNum;
    }

    public Integer getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(Integer documentNum) {
        this.documentNum = documentNum;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
