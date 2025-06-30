package io.tiklab.kanass.project.test.model;

import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper
public class ProjectTestRepositoryPlan {
    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testRepository",desc="所属仓库")
    @Mappings({
            @Mapping(source = "testRepository.id",target = "testRepositoryId")
    })
    @JoinField(key = "id")
    private TestRepository testRepository;

    @ApiProperty(name="testRepositoryId",desc="所属仓库ID")
    private String testRepositoryId;

    @NotNull
    @ApiProperty(name="project",desc="所属项目")
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
    })
    @JoinField(key = "id")
    private Project project;

    @ApiProperty(name="projectId",desc="所属项目ID")
    private String projectId;

    @NotNull
    @ApiProperty(name="testPlan",desc="所属测试计划")
    @Mappings({
            @Mapping(source = "testPlan.id",target = "planId")
    })
    @JoinField(key = "id")
    private TestPlan testPlan;

    @ApiProperty(name="planId",desc="所属测试计划ID")
    private String planId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestRepository getTestRepository() {
        return testRepository;
    }

    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }

    public String getTestRepositoryId() {
        return testRepositoryId;
    }

    public void setTestRepositoryId(String testRepositoryId) {
        this.testRepositoryId = testRepositoryId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
}
