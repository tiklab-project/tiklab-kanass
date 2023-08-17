package io.tiklab.teamwire.support.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;


@ApiModel
@Join
@Mapper
public class SystemUrl {
    @ApiProperty(name="id",desc="id",required = true)
    private String id;


    @ApiProperty(name="name",desc="标题",required = true)
    private String name;

    @ApiProperty(name="systemUrl",desc="标题",required = true)
    private String systemUrl;

    @ApiProperty(name="webUrl",desc="前端地址")
    private String webUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemUrl() {
        return systemUrl;
    }

    public void setSystemUrl(String systemUrl) {
        this.systemUrl = systemUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
