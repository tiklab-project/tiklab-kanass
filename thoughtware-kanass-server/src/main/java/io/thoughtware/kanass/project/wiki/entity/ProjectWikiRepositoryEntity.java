package io.thoughtware.kanass.project.wiki.entity;


import io.thoughtware.dal.jpa.annotation.*;

import java.sql.Date;

/**
 * 计划实例
 */
@Entity
@Table(name="pmc_project_wiki_repository")
public class ProjectWikiRepositoryEntity {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;
    
    //计划名字
    @Column(name = "wiki_repository_id",length = 12,notNull = true)
    private String wikiRepositoryId;

    //计划类型 id
    @Column(name = "project_id",length = 12,notNull = true)
    private String  projectId;

    @Column(name = "sort",length = 12,notNull = true)
    private Integer  sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWikiRepositoryId() {
        return wikiRepositoryId;
    }

    public void setWikiRepositoryId(String wikiRepositoryId) {
        this.wikiRepositoryId = wikiRepositoryId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
