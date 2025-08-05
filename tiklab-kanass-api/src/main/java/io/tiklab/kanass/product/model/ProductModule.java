package io.tiklab.kanass.product.model;

import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Join
@Mapper
public class ProductModule {
    @ApiProperty(name="id",desc="模块ID")
    private String id;

    @ApiProperty(name="moduleName",desc="模块名称")
    private String moduleName;


    @ApiProperty(name="parent",desc="上级事项")
    @Mappings({
            @Mapping(source = "parent.id",target = "parentId")
    })
    @JoinField(key = "id")
    private ProductModule parent;

    @ApiProperty(name="children",desc="下级事项列表")
    private List<ProductModule> children;



    @ApiProperty(name="desc",desc="模块描述")
    private String desc;

    @NotNull
    @ApiProperty(name="product",desc="所属产品",required = true)
    @Mappings({
            @Mapping(source = "product.id",target = "productId")
    })
    @JoinField(key = "id")
    private Product product;
}
