package io.thoughtware.kanass.workitem.model;

import io.thoughtware.kanass.project.test.model.ProjectTestCase;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

/**
 * 事项文档模型
 */
@ApiModel
@Mapper
@Join
public class WorkTestCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="workItemId",desc="事项id",required = true)
    private String workItemId;

    @NotNull
    @ApiProperty(name="testCaseId",desc="文档id",required = true)
    private String testCaseId;

    private ProjectTestCase projectTestCase;
    @NotNull
    @ApiProperty(name="repositoryId",desc="知识库id",required = true)
    private String repositoryId;

    private String projectId;

    @NotNull
    @ApiProperty(name="workItem",desc="关联事项",required = true)
    @Mappings({
            @Mapping(source = "workItem.id",target = "workItemId")
    })
    @JoinQuery(key = "id")
    private WorkItem workItem;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ProjectTestCase getProjectTestCase() {
        return projectTestCase;
    }

    public void setProjectTestCase(ProjectTestCase projectTestCase) {
        this.projectTestCase = projectTestCase;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }
}
