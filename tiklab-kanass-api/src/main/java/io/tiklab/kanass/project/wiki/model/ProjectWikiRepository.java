package io.tiklab.kanass.project.wiki.model;

import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.kanass.project.project.model.Project;

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
