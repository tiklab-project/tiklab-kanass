package io.tiklab.kanass.workitem.service;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderTypeEnum;
import io.tiklab.core.page.Page;
import io.tiklab.core.page.Pagination;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.flow.flow.model.Flow;
import io.tiklab.flow.flow.service.FlowService;
import io.tiklab.flow.transition.model.Transition;
import io.tiklab.flow.transition.model.TransitionQuery;
import io.tiklab.flow.transition.service.TransitionService;
import io.tiklab.form.field.model.FieldEx;
import io.tiklab.form.field.model.FieldQuery;
import io.tiklab.form.field.service.FieldService;
import io.tiklab.form.form.model.Form;
import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.module.model.ModuleQuery;
import io.tiklab.kanass.project.module.service.ModuleService;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.project.stage.model.Stage;
import io.tiklab.kanass.project.stage.model.StageQuery;
import io.tiklab.kanass.project.stage.service.StageService;
import io.tiklab.kanass.project.version.model.ProjectVersion;
import io.tiklab.kanass.project.version.model.ProjectVersionQuery;
import io.tiklab.kanass.project.version.service.ProjectVersionService;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.sprint.model.SprintQuery;
import io.tiklab.kanass.sprint.service.SprintService;
import io.tiklab.kanass.workitem.model.*;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.todotask.todo.model.TaskType;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Exporter
public class WorkItemCreateMetaDataServiceImpl implements WorkItemCreateMetaDataService{

    private static final Logger log = LoggerFactory.getLogger(WorkItemCreateMetaDataServiceImpl.class);
    @Autowired
    private FieldService fieldService;

    @Autowired
    private WorkTypeService workTypeService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private SprintService sprintService;

    @Autowired
    private ProjectVersionService projectVersionService;

    @Autowired
    private DmUserService dmUserService;

    @Autowired
    private StageService stageService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private WorkTypeDmService workTypeDmService;

    @Autowired
    private WorkItemService workItemService;

    @Autowired
    private FlowService flowService;

    @Autowired
    private WorkAttachService workAttachService;

    @Autowired
    private TransitionService transitionService;

    @Autowired
    private WorkItemRoleFunctionDmService workItemRoleFunctionDmService;

    @Override
    public WorkItemCreateMetaData findCreateMetaData(WorkItemCreateMetaDataQuery query) {
        WorkItemCreateMetaData result = new WorkItemCreateMetaData();
        List<Order> orderParams = new ArrayList<>();
        Order order = new Order();

        String projectId = query.getProjectId();
        Project project = projectService.findProject(projectId);
        result.setProject(project);

        // 字段信息
        List<List<FieldEx>> fieldLists = new ArrayList<>();
        for (String fieldCode : query.getFieldCode()) {
            FieldQuery fieldQuery = new FieldQuery();
            fieldQuery.setCode(fieldCode);
            List<FieldEx> fieldList = fieldService.findFieldList(fieldQuery);
            fieldLists.add(fieldList);
        }
        result.setFieldList(fieldLists);

        // 表单信息
        WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
        workTypeDmQuery.setProjectId(projectId);
        List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
        WorkTypeDm workTypeDm;
        if (query.getFieldCode().contains("bugType")){
            workTypeDm = workTypeDmList.stream().filter(item -> item.getWorkType().getCode().equals("defect")).collect(Collectors.toList()).get(0);
            String formId = workTypeDm.getForm().getId();
            Form formConfig = workTypeService.findFormConfig(formId);
            result.setForm(formConfig);

            result.setWorkTypeDm(workTypeDm);
        } else if (query.getFieldCode().contains("demandType")) {
            workTypeDm = workTypeDmList.stream().filter(item -> item.getWorkType().getCode().equals("demand")).collect(Collectors.toList()).get(0);
            String formId = workTypeDm.getForm().getId();
            Form formConfig = workTypeService.findFormConfig(formId);
            result.setForm(formConfig);

            result.setWorkTypeDm(workTypeDm);
        }else if (query.getFieldCode().contains("taskType")){
            workTypeDm = workTypeDmList.stream().filter(item -> item.getWorkType().getCode().equals("task")).collect(Collectors.toList()).get(0);
            String formId = workTypeDm.getForm().getId();
            Form formConfig = workTypeService.findFormConfig(formId);
            result.setForm(formConfig);

            result.setWorkTypeDm(workTypeDm);
        }


        // 模块信息
        ModuleQuery moduleQuery = new ModuleQuery();
        order.setName("moduleName");
        order.setOrderType(OrderTypeEnum.asc);
        orderParams.add(order);
        moduleQuery.setProjectId(projectId);
        moduleQuery.setOrderParams(orderParams);
        List<Module> moduleList = moduleService.findModuleList(moduleQuery);
        result.setModuleList(moduleList);

        // 敏捷模型查询迭代信息，瀑布模型查询计划信息
        order = new Order();
        orderParams  = new ArrayList<>();
        order.setName("startTime");
        order.setOrderType(OrderTypeEnum.desc);
        if (project.getProjectType().getType().equals("scrum")){
            // 迭代信息
            SprintQuery sprintQuery = new SprintQuery();
            sprintQuery.setProjectId(projectId);
            sprintQuery.setOrderParams(orderParams);
            List<Sprint> selectSprintList = sprintService.findSelectSprintList(sprintQuery);
            result.setSprintList(selectSprintList);
        }else if (project.getProjectType().getType().equals("nomal")){
            StageQuery stageQuery = new StageQuery();
            stageQuery.setProjectId(projectId);
            stageQuery.setOrderParams(orderParams);
            List<Stage> stageList = stageService.findStageList(stageQuery);
            result.setStageList(stageList);
        }


        // 版本信息
        ProjectVersionQuery projectVersionQuery = new ProjectVersionQuery();
        projectVersionQuery.setProjectId(projectId);
        projectVersionQuery.setOrderParams(orderParams);
        List<ProjectVersion> versionList = projectVersionService.findSelectVersionList(projectVersionQuery);
        result.setVersionList(versionList);

        // 人员信息
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(projectId);
        Page page = new Page();
        page.setCurrentPage(1);
        page.setPageSize(20);
        dmUserQuery.setPageParam(page);
        Pagination<DmUser> dmUserPage = dmUserService.findDmUserPage(dmUserQuery);
        result.setDmUserPage(dmUserPage);

        return result;
    }

    @Override
    public WorkItemDetailMetaData findDetailMetaData(WorkItemDetailMetaDataQuery query) {
        WorkItemDetailMetaData result = new WorkItemDetailMetaData();
        List<Order> orderParams = new ArrayList<>();
        Order order = new Order();

        long time1 = System.currentTimeMillis();
        log.info("------------开始------------");

        String workItemId = query.getWorkItemId();
        WorkItem workItem = workItemService.findWorkItemAndSprintVersion(workItemId);
        result.setWorkItem(workItem);
        long time2 = System.currentTimeMillis();
        log.info("findWorkItemAndSprintVersion 花费时间:{}----------------",time2-time1);

        List<String> fieldCodeList = new ArrayList<>();
        if (workItem.getWorkTypeCode().equals("defect")){
            fieldCodeList.add("bugType");
        } else if (workItem.getWorkTypeCode().equals("demand")) {
            fieldCodeList.add("demandType");
        }else if (workItem.getWorkTypeCode().equals("task")){
            fieldCodeList.add("taskType");
        }
        fieldCodeList.add("workPriority");

        // 字段信息
        CompletableFuture<List<List<FieldEx>>> fieldExFuture = CompletableFuture.supplyAsync(() -> {
            List<List<FieldEx>> fieldLists = new ArrayList<>();
            for (String fieldCode : fieldCodeList) {
                FieldQuery fieldQuery = new FieldQuery();
                fieldQuery.setCode(fieldCode);
                List<FieldEx> fieldList = fieldService.findFieldList(fieldQuery);
                fieldLists.add(fieldList);
            }
            return fieldLists;
        });

        String projectId = workItem.getProject().getId();
        Project project = projectService.findProject(projectId);

        long time3 = System.currentTimeMillis();
        log.info("findProject 花费时间:{}----------------",time3-time2);

        // 表单、流程信息
        WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
        workTypeDmQuery.setProjectId(projectId);
        List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
        result.setWorkTypeDmList(workTypeDmList);

        long time4 = System.currentTimeMillis();
        log.info("findWorkTypeDmList 花费时间:{}----------------",time4-time3);

        WorkTypeDm workTypeDm = new WorkTypeDm();
        if (workItem.getWorkTypeCode().equals("defect")){
            workTypeDm = workTypeDmList.stream().filter(item -> item.getWorkType().getCode().equals("defect")).collect(Collectors.toList()).get(0);
        } else if (workItem.getWorkTypeCode().equals("demand")) {
            workTypeDm = workTypeDmList.stream().filter(item -> item.getWorkType().getCode().equals("demand")).collect(Collectors.toList()).get(0);
        }else if (workItem.getWorkTypeCode().equals("task")){
            workTypeDm = workTypeDmList.stream().filter(item -> item.getWorkType().getCode().equals("task")).collect(Collectors.toList()).get(0);
        }
        // 表单
        WorkTypeDm finalWorkTypeDm = workTypeDm;
        CompletableFuture<Form> formFuture = CompletableFuture.supplyAsync(() -> {
            String formId = finalWorkTypeDm.getForm().getId();
            Form formConfig = workTypeService.findFormConfig(formId);
            return formConfig;
        });

        //  可关联父事项、可关联前置事项
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setId(workItemId);
        workItemQuery.setProjectId(projectId);
        workItemQuery.setWorkTypeId(workTypeDm.getId());
        CompletableFuture<Pagination<WorkItem>> canBeParentFuture = CompletableFuture.supplyAsync(() -> {
            Pagination<WorkItem> canBeParentWorkItemList = workItemService.findCanBeRelationParentWorkItemList(workItemQuery);
            return canBeParentWorkItemList;
        });
        CompletableFuture<Pagination<WorkItem>> canBePerFuture = CompletableFuture.supplyAsync(() -> {
            Pagination<WorkItem> canBeRelationPerWorkItemList = workItemService.findCanBeRelationPerWorkItemList(workItemQuery);
            return canBeRelationPerWorkItemList;
        });


        long time5 = System.currentTimeMillis();
        log.info("findFormConfig 花费时间:{}----------------",time5-time4);

        // 流程
        String flowId = workTypeDm.getFlow().getId();
        Flow flow = flowService.findFlow(flowId);
        result.setFlow(flow);

        long time6 = System.currentTimeMillis();
        log.info("findFlow 花费时间:{}----------------",time6-time5);



        long time7 = System.currentTimeMillis();
        log.info("findFieldList 花费时间:{}----------------",time7-time6);

        // 模块信息
        ModuleQuery moduleQuery = new ModuleQuery();
        order.setName("moduleName");
        order.setOrderType(OrderTypeEnum.asc);
        orderParams.add(order);
        moduleQuery.setProjectId(projectId);
        moduleQuery.setOrderParams(orderParams);
        List<Module> moduleList = moduleService.findModuleList(moduleQuery);
        result.setModuleList(moduleList);

        long time8 = System.currentTimeMillis();
        log.info("findModuleList 花费时间:{}----------------",time8-time7);

        // 敏捷模型查询迭代信息，瀑布模型查询计划信息
        order = new Order();
        orderParams  = new ArrayList<>();
        order.setName("startTime");
        order.setOrderType(OrderTypeEnum.desc);
        if (project.getProjectType().getType().equals("scrum")){
            // 迭代信息
            SprintQuery sprintQuery = new SprintQuery();
            sprintQuery.setProjectId(projectId);
            sprintQuery.setOrderParams(orderParams);
            List<Sprint> selectSprintList = sprintService.findSelectSprintList(sprintQuery);
            result.setSprintList(selectSprintList);

            List<Sprint> workSprintList = sprintService.findWorkSprintList(workItemId);
            result.setWorkSprintList(workSprintList);
        }else if (project.getProjectType().getType().equals("nomal")){
            StageQuery stageQuery = new StageQuery();
            stageQuery.setProjectId(projectId);
            stageQuery.setOrderParams(orderParams);
            List<Stage> stageList = stageService.findStageList(stageQuery);
            result.setStageList(stageList);
        }

        long time9 = System.currentTimeMillis();
        log.info("findSelectSprintList 花费时间:{}----------------",time9-time8);

        // 版本信息
        ProjectVersionQuery projectVersionQuery = new ProjectVersionQuery();
        projectVersionQuery.setProjectId(projectId);
        projectVersionQuery.setOrderParams(orderParams);
        List<ProjectVersion> versionList = projectVersionService.findSelectVersionList(projectVersionQuery);
        result.setVersionList(versionList);

        long time10 = System.currentTimeMillis();
        log.info("findSelectVersionList 花费时间:{}----------------",time10-time9);

        List<ProjectVersion> workVersionList = projectVersionService.findWorkVersionList(workItemId);
        result.setWorkVersionList(workVersionList);

        long time11 = System.currentTimeMillis();
        log.info("findWorkVersionList 花费时间:{}----------------",time11-time10);


        // 附件
        WorkAttachQuery workAttachQuery = new WorkAttachQuery();
        workAttachQuery.setWorkItemId(workItemId);
        List<WorkAttach> workAttachList = workAttachService.findWorkAttachList(workAttachQuery);
        result.setWorkAttachList(workAttachList);

        long time12 = System.currentTimeMillis();
        log.info("findWorkAttachList 花费时间:{}----------------",time12-time11);


//        Pagination<WorkItem> canBeRelationParentWorkItemList = workItemService.findCanBeRelationParentWorkItemList(workItemQuery);
//        Pagination<WorkItem> canBeRelationPerWorkItemList = workItemService.findCanBeRelationPerWorkItemList(workItemQuery);


        long time13 = System.currentTimeMillis();
        log.info("findCanBeRelationParentWorkItemList 花费时间:{}----------------",time13-time12);

        // 人员信息
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(projectId);
        Page page = new Page();
        page.setCurrentPage(1);
        page.setPageSize(20);
        dmUserQuery.setPageParam(page);
        Pagination<DmUser> dmUserPage = dmUserService.findDmUserPage(dmUserQuery);
        result.setDmUserPage(dmUserPage);

        long time14 = System.currentTimeMillis();
        log.info("findDmUserPage 花费时间:{}----------------",time14-time13);


//        String userId = LoginContext.getLoginId();
//        // 流转信息
//        TransitionQuery  transitionQuery = new TransitionQuery();
//        transitionQuery.setDomainId(workItemId);
//        transitionQuery.setFlowId(workTypeDm.getFlow().getId());
//        transitionQuery.setUserId(userId);
//        transitionQuery.setFromNodeId(workItem.getWorkStatusNode().getId());
//        List<Transition> transitionListByBusiness = transitionService.findTransitionListByBusiness(transitionQuery);
//        result.setTransitionListByBusiness(transitionListByBusiness);
//
//        // 根据查询事项关联各个模型的数量，ex: 关联事项，下级事项， 日志， 文档，动态等
//        HashMap<String, Integer> workItemRelationModelCount = workItemService.findWorkItemRelationModelCount(workItemId, workTypeDm.getWorkType().getCode());
//        result.setWorkItemRelationModelCount(workItemRelationModelCount);
//
//        // 权限code
//        WorkItemRoleFunctionDmQuery workItemRoleFunctionDmQuery  = new WorkItemRoleFunctionDmQuery();
//        workItemRoleFunctionDmQuery.setWorkId(workItemId);
//        workItemRoleFunctionDmQuery.setUserId(userId);
//        workItemRoleFunctionDmQuery.setDomainId(projectId);
//        workItemRoleFunctionDmQuery.setWorkTypeId(workTypeDm.getId());
//        List<String> workItemRoleFunctionDmCode = workItemRoleFunctionDmService.findWorkItemRoleFunctionDmCode(workItemRoleFunctionDmQuery);
//        result.setWorkItemRoleFunctionDmCode(workItemRoleFunctionDmCode);

        Form formConfig = formFuture.join();
        result.setForm(formConfig);

        Pagination<WorkItem> canBeRelationParentWorkItem = canBeParentFuture.join();
        Pagination<WorkItem> canBeRelationPerWorkItem = canBePerFuture.join();
        result.setCanBeRelationParentWorkItemList(canBeRelationParentWorkItem);
        result.setCanBeRelationPerWorkItemList(canBeRelationPerWorkItem);

        List<List<FieldEx>> fieldLists = fieldExFuture.join();
        result.setFieldList(fieldLists);

        long time15 = System.currentTimeMillis();
        log.info("总花费时间:{}----------------",time15-time1);

        return result;
    }
}
