package io.tiklab.kanass.test.func.cases.model;

import io.tiklab.kanass.test.test.model.TestCase;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 功能用例 模型
 */
@ApiModel
@Mapper(targetName = "io.tiklab.kanass.test.func.cases.entity.FuncUnitCaseEntity")
@Join
public class FuncUnitCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testCases",desc="用例")
    @Mappings({
            @Mapping(source = "testCase.id",target = "testCaseId")
    })
    @JoinField(key = "id")
    private TestCase testCase;

    @ApiProperty(name="precondition",desc="前置条件")
    private String  precondition;

    @ApiProperty(name="stepNum",desc="步骤数量")
    private int stepNum;

    @ApiProperty(name="stepNum",desc="缺陷数量")
    private int defectNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public String getPrecondition() {
        return precondition;
    }

    public void setPrecondition(String precondition) {
        this.precondition = precondition;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public int getDefectNum() {
        return defectNum;
    }

    public void setDefectNum(int defectNum) {
        this.defectNum = defectNum;
    }
}
