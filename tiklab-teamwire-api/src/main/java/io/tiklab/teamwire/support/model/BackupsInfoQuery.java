package io.tiklab.teamwire.support.model;

import io.tiklab.postin.annotation.ApiProperty;

import java.io.Serializable;

public class BackupsInfoQuery implements Serializable {

    @ApiProperty(name ="type",desc = "类型")
    private String  type;


    public String getType() {
        return type;
    }

    public BackupsInfoQuery setType(String type) {
        this.type = type;
        return this;
    }
}
