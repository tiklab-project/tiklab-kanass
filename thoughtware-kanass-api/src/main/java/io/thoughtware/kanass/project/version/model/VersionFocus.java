package io.thoughtware.kanass.project.version.model;

import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

/**
 * 收藏的迭代模型
 */
@ApiModel
@Mapper
public class VersionFocus extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

//    @ApiProperty(name="versionId",desc="收藏的迭代id")
//    private String versionId;



    @ApiProperty(name="version",desc = "项目类型")
    @Mappings({
            @Mapping(source = "version.id",target = "versionId")
    })
    @JoinQuery(key = "id")
    private ProjectVersion version;

    @ApiProperty(name="masterId",desc="迭代创建人")
    private String masterId;

    @ApiProperty(name="projectId",desc="迭代所属项目")
    private String projectId;

    @ApiProperty(name="sort",desc="迭代排序")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ProjectVersion getVersion() {
        return version;
    }

    public void setVersion(ProjectVersion version) {
        this.version = version;
    }
}
