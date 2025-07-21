package io.tiklab.kanass.project.appraised.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;
import io.tiklab.kanass.project.appraised.model.AppraisedWorkItem;

import java.util.Date;

/**
 * 事项记录
 */
@ApiModel
@Mapper
@Join
public class AppraisedHistory {
    @ApiProperty(name = "id", desc = "唯一ID")
    private String id;

    @ApiProperty(name = "workAppraised", desc = "评审记录")
    @Mappings({
            @Mapping(source = "workAppraised.id", target = "workAppraisedId")
    })
    @JoinField(key = "id")
    private AppraisedWorkItem workAppraised;


    // 0未评审 1通过 2未通过 3建议
    @ApiProperty(name = "workItemAppraisedState", desc = "评审状态")
    private String workItemAppraisedState;

    @ApiProperty(name="creater",desc="创建人")
    @Mappings({
            @Mapping(source = "creater.id",target = "creater")
    })
    @JoinField(key = "id")
    private User creater;

    @ApiProperty(name = "advice", desc = "评审意见")
    private String advice;

    @ApiProperty(name="createTime",desc="创建时间")
    private java.util.Date createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    private java.util.Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppraisedWorkItem getWorkAppraised() {
        return workAppraised;
    }

    public void setWorkAppraised(AppraisedWorkItem workAppraised) {
        this.workAppraised = workAppraised;
    }

    public String getWorkItemAppraisedState() {
        return workItemAppraisedState;
    }

    public void setWorkItemAppraisedState(String workItemAppraisedState) {
        this.workItemAppraisedState = workItemAppraisedState;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }
}
