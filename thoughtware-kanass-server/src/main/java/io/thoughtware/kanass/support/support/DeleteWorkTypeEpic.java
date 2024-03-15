package io.thoughtware.kanass.support.support;

import io.thoughtware.flow.flow.model.FlowModelRelation;
import io.thoughtware.flow.flow.model.FlowModelRelationQuery;
import io.thoughtware.flow.flow.service.FlowModelRelationService;
import io.thoughtware.form.form.model.FormModelRelation;
import io.thoughtware.form.form.model.FormModelRelationQuery;
import io.thoughtware.form.form.service.FormModelRelationService;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.service.ProjectService;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
public class DeleteWorkTypeEpic implements ApplicationRunner {

    @Autowired
    ProjectService projectService;
    @Autowired
    WorkTypeDmService workTypeDmService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    FlowModelRelationService flowModelRelationService;

    @Autowired
    FormModelRelationService formModelRelationService;

    @Autowired
    WorkTypeService workTypeService;

    private static Logger logger = LoggerFactory.getLogger(DeleteWorkTypeEpic.class);

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        initWorkTypeEpicForProject();
    }

    public void initWorkTypeEpicForProject(){
        // 更新事项的类型
        for (Project project : projectService.findAllProject()) {
            String id = project.getId();
            WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
            workTypeDmQuery.setProjectId(id);
            workTypeDmQuery.setCode("demand");
            List<WorkTypeDm> demandWorkTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
            WorkTypeDm demandWorkTypeDm = demandWorkTypeDmList.get(0);
            String workTypeId = demandWorkTypeDm.getWorkType().getId();
            String dmWorkTypeId = demandWorkTypeDm.getId();
            workItemService.updateEpicWork(id, workTypeId, dmWorkTypeId);
        }

        // 删除
        workTypeDmService.deleteWorkTypeDmCondition("13240f85");

        // 删除事项类型与表单，流程的关联表
        FlowModelRelationQuery flowModelRelationQuery = new FlowModelRelationQuery();
        flowModelRelationQuery.setModelName("需求集");
        List<FlowModelRelation> flowModelRelationList = flowModelRelationService.findFlowModelRelationList(flowModelRelationQuery);
        for (FlowModelRelation flowModelRelation : flowModelRelationList) {
            flowModelRelationService.deleteFlowModelRelation(flowModelRelation.getId());
        }

        FormModelRelationQuery formModelRelationQuery = new FormModelRelationQuery();
        formModelRelationQuery.setModelName("需求集");
        List<FormModelRelation> formModelRelationList = formModelRelationService.findFormModelRelationList(formModelRelationQuery);
        for (FormModelRelation formModelRelation : formModelRelationList) {
            formModelRelationService.deleteFormModelRelation(formModelRelation.getId());
        }

        workTypeService.deleteWorkTypeById("13240f85");
    }
}
