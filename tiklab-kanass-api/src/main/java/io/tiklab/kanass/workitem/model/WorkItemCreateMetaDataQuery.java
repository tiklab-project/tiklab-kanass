package io.tiklab.kanass.workitem.model;

import io.tiklab.core.BaseModel;
import io.tiklab.form.form.model.Form;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 查询 创建事项时需要的元数据 的查询参数
 */
public class WorkItemCreateMetaDataQuery implements Serializable {

//    private static final long serialVersionUID = 45443153L;

    // 表单code
//    @NotNull
    private List<String> fieldCode;

    // 项目id
    @NotNull
    private String projectId;



    public List<String> getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(List<String> fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

}
