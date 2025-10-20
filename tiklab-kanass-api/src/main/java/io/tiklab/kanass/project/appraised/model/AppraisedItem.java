package io.tiklab.kanass.project.appraised.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.kanass.project.wiki.model.ProjectDocument;
import io.tiklab.kanass.test.testcase.test.model.TestCase;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;

import java.sql.Timestamp;
import java.util.List;

/**
 * 评审与事项关联模型
 */
@ApiModel
@Mapper(targetName = "io.tiklab.kanass.appraised.entity.AppraisedItemEntity")
@Join
public class AppraisedItem {
    @ApiProperty(name = "id", desc = "唯一ID")
    private String id;

    @ApiProperty(name = "appraised", desc = "所属评审")
    @Mappings({
            @Mapping(source = "appraised.id", target = "appraisedId")
    })
    @JoinField(key = "id")
    private Appraised appraised;

    // 多个事项ID
    private List<String> targetIds;

    @ApiProperty(name = "targetId", desc = "评审对象id")
    private String targetId;

    // 需求评审--事项
    private WorkItem workItem;

    // 决策、技术评审--文档
    private ProjectDocument projectDocument;

    // 用例评审--功能用例
    private TestCase testCase;

    // 0未评审 1通过 2未通过 3建议
    @ApiProperty(name = "appraisedItemState", desc = "评审状态")
    private String appraisedItemState;

    @ApiProperty(name="appraisedType",desc="评审类型")
    @Mappings({
            @Mapping(source = "appraisedType.id",target = "appraisedTypeId")
    })
    @JoinField(key = "id")
    private AppraisedType appraisedType;

    @ApiProperty(name = "createTime", desc = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @ApiProperty(name = "updateTime", desc = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Appraised getAppraised() {
        return appraised;
    }

    public void setAppraised(Appraised appraised) {
        this.appraised = appraised;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }

    public String getAppraisedItemState() {
        return appraisedItemState;
    }

    public void setAppraisedItemState(String appraisedItemState) {
        this.appraisedItemState = appraisedItemState;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public ProjectDocument getProjectDocument() {
        return projectDocument;
    }

    public void setProjectDocument(ProjectDocument projectDocument) {
        this.projectDocument = projectDocument;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public AppraisedType getAppraisedType() {
        return appraisedType;
    }

    public void setAppraisedType(AppraisedType appraisedType) {
        this.appraisedType = appraisedType;
    }

    public List<String> getTargetIds() {
        return targetIds;
    }

    public void setTargetIds(List<String> targetIds) {
        this.targetIds = targetIds;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
