package io.thoughtware.kanass.project.wiki.model;

import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.kanass.project.project.model.Project;

import javax.validation.constraints.NotNull;

/**
 * 计划模型
 */
@ApiModel
@Join
@Mapper
public class ProjectWikiRepository extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="wikiRepository",desc="所属项目",eg="@selectOne")
    @Mappings({
            @Mapping(source = "wikiRepository.id",target = "wikiRepositoryId")
    })
    @JoinQuery(key = "id")
    private KanassRepository wikiRepository;

    @NotNull
    @ApiProperty(name="project",desc="所属项目",eg="@selectOne")
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
    })
    @JoinQuery(key = "id")
    private Project project;

    @ApiProperty(name="sort",desc="排序",eg="@int16")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KanassRepository getWikiRepository() {
        return wikiRepository;
    }

    public void setWikiRepository(KanassRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
