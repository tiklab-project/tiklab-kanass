package io.thoughtware.kanass.starter.config;

import io.thoughtware.flow.flow.model.FlowModelRelation;
import io.thoughtware.flow.flow.model.FlowModelRelationQuery;
import io.thoughtware.flow.flow.service.FlowModelRelationService;
import io.thoughtware.form.field.model.FieldEx;
import io.thoughtware.form.field.model.SelectItem;
import io.thoughtware.form.field.model.SelectItemRelation;
import io.thoughtware.form.field.service.SelectItemRelationService;
import io.thoughtware.form.form.model.Form;
import io.thoughtware.form.form.model.FormField;
import io.thoughtware.form.form.model.FormModelRelation;
import io.thoughtware.form.form.model.FormModelRelationQuery;
import io.thoughtware.form.form.service.FormFieldService;
import io.thoughtware.form.form.service.FormModelRelationService;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.model.WorkTypeDm;
import io.thoughtware.kanass.workitem.model.WorkTypeDmQuery;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.kanass.workitem.service.WorkTypeDmService;
import io.thoughtware.kanass.workitem.service.WorkTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class FormFieldInit implements ApplicationRunner {

    @Autowired
    WorkTypeDmService workTypeDmService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    FormFieldService formFieldService;

    @Autowired
    SelectItemRelationService selectItemRelationService;

    private static Logger logger = LoggerFactory.getLogger(FormFieldInit.class);

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("0");
        initWorkTypeFormFieid("778222e0", "a34d7fc3", 16 );
        initWorkTypeFormFieid("98121701", "0763e387", 16 );
        initWorkTypeFormFieid("7055ebc6", "c4579b11", 18 );
        initSelectItemRelation();
    }



    public void initWorkTypeFormFieid(String workTypeId, String fieldId, int sort){
        // 任务类型
        WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
        workTypeDmQuery.setWorkTypeId(workTypeId);
        List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
        // 跟关联的表单添加任务类型字段
        for (WorkTypeDm workTypeDm : workTypeDmList) {
            Form form = workTypeDm.getForm();
            FormField formField = new FormField();
            formField.setForm(form);

            FieldEx fieldEx = new FieldEx();
            fieldEx.setId(fieldId);
            formField.setField(fieldEx);
            formField.setSort(sort);
            formFieldService.createFormField(formField);
        }
    }

    public void initSelectItemRelation(){
        List<WorkItem> allWorkItem = workItemService.findAllWorkItem();
        for (WorkItem workItem : allWorkItem) {
            String eachType = workItem.getEachType();
            if(eachType != null){
                String workTypeCode = workItem.getWorkTypeCode();
                SelectItemRelation selectItemRelation = new SelectItemRelation();
                selectItemRelation.setSelectItemId(eachType);
                selectItemRelation.setRelationId(workItem.getId());
                switch (workTypeCode){
                    case "defect":
                        selectItemRelation.setFieldId("0763e387");
                        break;
                    case "demand":
                        selectItemRelation.setFieldId("c4579b11");
                        break;
                    case "task":
                        selectItemRelation.setFieldId("a34d7fc3");
                        break;
                    default:
                        break;
                }
                selectItemRelationService.createSelectItemRelation(selectItemRelation);
            }

            SelectItem workPriority = workItem.getWorkPriority();
            if(workPriority != null){
                String id = workPriority.getId();
                SelectItemRelation selectItemRelation = new SelectItemRelation();
                selectItemRelation.setSelectItemId(id);
                selectItemRelation.setRelationId(workItem.getId());
                selectItemRelation.setFieldId("187d7a58");
                selectItemRelationService.createSelectItemRelation(selectItemRelation);
            }
        }

    }
}
