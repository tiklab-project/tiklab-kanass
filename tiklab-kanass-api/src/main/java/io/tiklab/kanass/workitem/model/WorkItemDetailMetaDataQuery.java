package io.tiklab.kanass.workitem.model;

import io.tiklab.core.BaseModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 查询 创建事项时需要的元数据 的查询参数
 */
public class WorkItemDetailMetaDataQuery implements Serializable {


    // 表单code
    @NotNull
    private String workItemId;

//    // 项目id
//    @NotNull
//    private String projectId;

//    // 表单code  先传事项类型 再传优先级
//    @NotNull
//    private List<String> fieldCode;


    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

//    public String getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(String projectId) {
//        this.projectId = projectId;
//    }

//    public List<String> getFieldCode() {
//        return fieldCode;
//    }
//
//    public void setFieldCode(List<String> fieldCode) {
//        this.fieldCode = fieldCode;
//    }
}
