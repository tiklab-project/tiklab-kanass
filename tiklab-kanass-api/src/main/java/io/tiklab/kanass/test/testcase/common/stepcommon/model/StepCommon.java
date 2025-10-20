package io.tiklab.kanass.test.testcase.common.stepcommon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.kanass.test.testcase.func.cases.model.FuncUnitStep;

import java.sql.Timestamp;

/**
 * 公共步骤 模型
 */
@ApiModel
@Mapper
@Join
public class StepCommon extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="caseId",desc="caseId")
    private String caseId;

    @ApiProperty(name="type",desc="步骤类型")
    private String type;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    @ApiProperty(name="oldSort",desc="原排序")
    private Integer oldSort;

    @ApiProperty(name="result",desc="用于从测试计划进入功能用例结果展示")
    private Integer result;

    private FuncUnitStep funcUnitStep;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getOldSort() {
        return oldSort;
    }

    public void setOldSort(Integer oldSort) {
        this.oldSort = oldSort;
    }

    public FuncUnitStep getFuncUnitStep() {
        return funcUnitStep;
    }

    public void setFuncUnitStep(FuncUnitStep funcUnitStep) {
        this.funcUnitStep = funcUnitStep;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
