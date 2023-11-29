package io.tiklab.teamwire.project.test.model;

import io.tiklab.teston.repository.model.Repository;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 空间关注 模型
 * @pi.model: Category
 */
@ApiModel
@Mapper(targetName  = "io.tiklab.teston.category.entity.CategoryEntity")
@Join
public class Category extends BaseModel{

    private static final long serialVersionUID = 6590165929566174830L;

    /**
     * @pi.name: id
     * @pi.value: categoryId
     */
    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    /**
     * @pi.name: name
     * @pi.value: 分组名称
     */
    @NotNull
    @ApiProperty(name="name",desc="name",required = true)
    private java.lang.String name;

    /**
     * @pi.model: Repository
     */
    @NotNull
    @ApiProperty(name="repository",desc="仓库id")
    @Mappings({
            @Mapping(source = "repository.id",target = "repositoryId")
    })
    @JoinQuery(key = "id")
    private Repository repository;

    /**
     * @pi.name: parentId
     * @pi.value: 所属父级
     */
    @ApiProperty(name="parentId",desc="上级分类")
    private String parentId;

    /**
     * @pi.name: sort
     * @pi.value: 排序
     */
    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    /**
     * @pi.name: desc
     * @pi.value: 描述
     */
    @ApiProperty(name="desc",desc="描述")
    private java.lang.String desc;

    /**
     * @pi.name: children
     * @pi.value: []
     */
    @ApiProperty(name="children",desc="下级分类列表")
    private List<Category> children;

    /**
     * @pi.name: children
     * @pi.value: []
     */
    @ApiProperty(name="nodeList",desc="分类用例")
    private List<TestCase> nodeList=new ArrayList<>();

    /**
     * @pi.name: caseNum
     * @pi.value: 目录下的用例数
     */
    @ApiProperty(name="caseNum",desc="目录下的用例数")
    private java.lang.Integer caseNum;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public List<TestCase> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<TestCase> nodeList) {
        this.nodeList = nodeList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
