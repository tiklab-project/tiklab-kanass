package io.thoughtware.kanass.home.statistic.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.kanass.project.project.model.Project;

import javax.validation.constraints.NotNull;

/**
 * 项目下事项数量模型
 */
@ApiModel
@Join
public class ProjectWorkItemStat {
    @NotNull
    @ApiProperty(name="project",desc="项目",eg="@text32",required = true)
    private Project project;

    @NotNull
    @ApiProperty(name="processWorkItemCount",desc="进行中事项数量")
    private Integer processWorkItemCount;

    @NotNull
    @ApiProperty(name="endWorkItemCount",desc="完成事项数量")
    private Integer endWorkItemCount;


    public Integer getProcessWorkItemCount() {
        return processWorkItemCount;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setProcessWorkItemCount(Integer processWorkItemCount) {
        this.processWorkItemCount = processWorkItemCount;
    }

    public Integer getEndWorkItemCount() {
        return endWorkItemCount;
    }

    public void setEndWorkItemCount(Integer endWorkItemCount) {
        this.endWorkItemCount = endWorkItemCount;
    }
}
