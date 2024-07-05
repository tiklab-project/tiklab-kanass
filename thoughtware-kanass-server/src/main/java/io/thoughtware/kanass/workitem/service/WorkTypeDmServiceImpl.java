package io.thoughtware.kanass.workitem.service;

import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.flow.flow.model.*;
import io.thoughtware.flow.flow.service.FlowModelRelationService;
import io.thoughtware.form.form.model.*;
import io.thoughtware.form.form.service.FormModelRelationService;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.kanass.workitem.model.*;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.exception.SystemException;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.flow.flow.service.DmFlowService;
import io.thoughtware.flow.flow.service.FlowService;
import io.thoughtware.form.form.service.DmFormService;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.kanass.workitem.dao.WorkTypeDmDao;
import io.thoughtware.kanass.workitem.entity.WorkTypeDmEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
*  项目的事项类型服务
*/
@Service
@Exporter
public class WorkTypeDmServiceImpl implements WorkTypeDmService {

    @Autowired
    WorkTypeDmDao workTypeDmDao;

    @Autowired
    WorkTypeService workTypeService;

    @Autowired
    DmFlowService dmFlowService;

    @Autowired
    FlowModelRelationService flowModelRelationService;

    @Autowired
    FormModelRelationService formModelRelationService;

    @Autowired
    FlowService flowService;

    @Autowired
    DmFormService dmFormService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public WorkTypeDm createWorkTypeDm(@NotNull @Valid WorkTypeDm workTypeDm) {

        Flow flow = workTypeDm.getFlow();
        DmFlowQuery dmFlowQuery = new DmFlowQuery();
        dmFlowQuery.setDomainId(workTypeDm.getProjectId());
        dmFlowQuery.setGlobalFlowId(flow.getId());

        // 创建流程与事项类型关联记录
        FlowModelRelation flowModelRelation = new FlowModelRelation();

        // 查找当前项目是否已经复制过当前这个系统的流程
        DmFlow dmFlow = dmFlowService.existDmFlow(dmFlowQuery);
        if(dmFlow != null){
            // 若复制过，直接关联到事项类型
            workTypeDm.setFlow(dmFlow.getFlow());
            // 设置流程与事项类型的关联
            flowModelRelation.setFlowId(dmFlow.getFlow().getId());
        }else {
            // 若没复制过，则复制，并关联
            Flow flow1 = dmFlowService.cloneFlowById(flow.getId(), workTypeDm.getProjectId());
            workTypeDm.setFlow(flow1);
            // 设置流程与事项类型的关联,关联复制出来时间的流程
            flowModelRelation.setFlowId(flow1.getId());
        }

        Form form = workTypeDm.getForm();
        DmFormQuery dmFormQuery = new DmFormQuery();
        dmFormQuery.setDomainId(workTypeDm.getProjectId());
        dmFormQuery.setGlobalFormId(form.getId());
        DmForm dmForm = dmFormService.existDmForm(dmFormQuery);
        FormModelRelation formModelRelation = new FormModelRelation();

        if(dmForm != null){
            workTypeDm.setForm(dmForm.getForm());
            formModelRelation.setFormId(dmForm.getForm().getId());

        }else {
            String formId = dmFormService.cloneFormById(form.getId(),workTypeDm.getProjectId());
            Form form1 = new Form();
            form1.setId(formId);
            workTypeDm.setForm(form1);
            formModelRelation.setFormId(formId);
        }

        WorkTypeDmEntity workTypeDmEntity = BeanMapper.map(workTypeDm, WorkTypeDmEntity.class);
        String workTypeDm1 = workTypeDmDao.createWorkTypeDm(workTypeDmEntity);


        // 事项类型与流程关联
        flowModelRelation.setModelId(workTypeDm1);
        flowModelRelation.setModelName(workTypeDm.getWorkType().getName());
        flowModelRelation.setBgroup("kanass");
        flowModelRelation.setModelType("workTypeDm");
        flowModelRelationService.createFlowModelRelation(flowModelRelation);

        // 事项类型与表单关联
        formModelRelation.setModelId(workTypeDm1);
        formModelRelation.setModelName(workTypeDm.getWorkType().getName());
        formModelRelation.setBgroup("kanass");
        formModelRelation.setModelType("workTypeDm");
        formModelRelationService.createFormModelRelation(formModelRelation);
        workTypeDm.setId(workTypeDm1);
        return workTypeDm;
    }


    @Override
    public WorkTypeDm copyWorkTypeDm(@NotNull @Valid WorkTypeDm workTypeDm) {

        Flow flow = workTypeDm.getFlow();
        DmFlowQuery dmFlowQuery = new DmFlowQuery();
        dmFlowQuery.setDomainId(workTypeDm.getProjectId());
        dmFlowQuery.setGlobalFlowId(flow.getId());

        // 创建流程与事项类型关联记录
        FlowModelRelation flowModelRelation = new FlowModelRelation();

        // 查找当前项目是否已经复制过当前这个系统的流程
        DmFlow dmFlow = dmFlowService.existDmFlow(dmFlowQuery);
        if(dmFlow != null){
            // 若复制过，直接关联到事项类型
            workTypeDm.setFlow(dmFlow.getFlow());
            // 设置流程与事项类型的关联
            flowModelRelation.setFlowId(dmFlow.getFlow().getId());
        }else {
            // 若没复制过，则复制，并关联
            String oldFlow = flow.getId();
            String projectId = workTypeDm.getProjectId();

            String flowId = flowService.copyFlow(oldFlow, projectId);
            Flow newFlow = new Flow();
            newFlow.setId(flowId);
            workTypeDm.setFlow(newFlow);
//            // 设置流程与事项类型的关联,关联复制出来时间的流程
            flowModelRelation.setFlowId(flowId);
        }

        WorkTypeDmEntity workTypeDmEntity = BeanMapper.map(workTypeDm, WorkTypeDmEntity.class);
        String workTypeDm1 = workTypeDmDao.createWorkTypeDm(workTypeDmEntity);

        // 事项类型与流程关联
        flowModelRelation.setModelId(workTypeDm1);
        flowModelRelation.setModelName(workTypeDm.getWorkType().getName());
        flowModelRelation.setBgroup("kanass");
        flowModelRelation.setModelType("workTypeDm");
        flowModelRelationService.createFlowModelRelation(flowModelRelation);
        return workTypeDm;
    }

    @Override
    public void updateWorkTypeDm(@NotNull @Valid WorkTypeDm workTypeDm) {
        String id = workTypeDm.getId();
        FormModelRelationQuery formModelRelationQuery = new FormModelRelationQuery();
        formModelRelationQuery.setModelId(id);
        List<FormModelRelation> formModelRelationList = formModelRelationService.findFormModelRelationList(formModelRelationQuery);
        for (FormModelRelation formModelRelation : formModelRelationList) {
            String formId = workTypeDm.getForm().getId();
            formModelRelation.setFormId(formId);
            formModelRelationService.updateFormModelRelation(formModelRelation);
        }

        FlowModelRelationQuery flowModelRelationQuery = new FlowModelRelationQuery();
        flowModelRelationQuery.setModelId(id);
        List<FlowModelRelation> flowModelRelationList = flowModelRelationService.findFlowModelRelationList(flowModelRelationQuery);
        for (FlowModelRelation flowModelRelation : flowModelRelationList) {
            String flowId = workTypeDm.getFlow().getId();
            flowModelRelation.setFlowId(flowId);
            flowModelRelationService.updateFlowModelRelation(flowModelRelation);
        }

        WorkTypeDmEntity workTypeDmEntity = BeanMapper.map(workTypeDm, WorkTypeDmEntity.class);

        workTypeDmDao.updateWorkTypeDm(workTypeDmEntity);
    }

    @Override
    public void deleteWorkTypeDm(@NotNull String id) {
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setWorkTypeId(id);
        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
        int size = workItemList.size();
        if(workItemList != null && size>0){
            throw new SystemException(3001,"类型使用中，不可删除");
        }else {
            workTypeDmDao.deleteWorkTypeDm(id);
        }
    }

    @Override
    public void deleteWorkTypeDmCondition(@NotNull String workTypeId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkTypeDmEntity.class)
                .eq("workTypeId", workTypeId)
                .get();

        workTypeDmDao.deleteWorkTypeDm(deleteCondition);
    }



    @Override
    public WorkTypeDm findOne(String id) {
        WorkTypeDmEntity workTypeDmEntity = workTypeDmDao.findWorkTypeDm(id);

        WorkTypeDm workTypeDm = BeanMapper.map(workTypeDmEntity, WorkTypeDm.class);
        return workTypeDm;
    }

    @Override
    public List<WorkTypeDm> findList(List<String> idList) {
        List<WorkTypeDmEntity> workTypeDmEntityList =  workTypeDmDao.findWorkTypeDmList(idList);

        List<WorkTypeDm> workTypeDmList =  BeanMapper.mapList(workTypeDmEntityList,WorkTypeDm.class);
        return workTypeDmList;
    }

    @Override
    public WorkTypeDm findWorkTypeDm(@NotNull String id) {
        WorkTypeDm workTypeDm = findOne(id);

        joinTemplate.joinQuery(workTypeDm);

        return workTypeDm;
    }

    @Override
    public List<WorkTypeDm> findAllWorkTypeDm() {
        List<WorkTypeDmEntity> workTypeDmEntityList =  workTypeDmDao.findAllWorkTypeDm();

        List<WorkTypeDm> workTypeDmList =  BeanMapper.mapList(workTypeDmEntityList,WorkTypeDm.class);

        joinTemplate.joinQuery(workTypeDmList);

        return workTypeDmList;
    }

    @Override
    public List<WorkTypeDm> findWorkTypeDmListNoRepeat(WorkTypeDmQuery workTypeDmQuery) {
        List<WorkTypeDmEntity> workTypeDmEntityList = workTypeDmDao.findWorkTypeDmList(workTypeDmQuery);
        List<WorkTypeDmEntity> collect = workTypeDmEntityList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(WorkTypeDmEntity::getWorkTypeId))), ArrayList::new));
        List<WorkTypeDm> workTypeDmList = BeanMapper.mapList(collect,WorkTypeDm.class);

        joinTemplate.joinQuery(workTypeDmList);

        return workTypeDmList;
    }

    @Override
    public List<WorkTypeDm> findWorkTypeDmList(WorkTypeDmQuery workTypeDmQuery) {
        List<WorkTypeDmEntity> workTypeDmEntityList = workTypeDmDao.findWorkTypeDmList(workTypeDmQuery);
//        List<WorkTypeDmEntity> collect = workTypeDmEntityList.stream().collect(
//                Collectors.collectingAndThen(
//                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(WorkTypeDmEntity::getWorkTypeId))), ArrayList::new));
        List<WorkTypeDm> workTypeDmList = BeanMapper.mapList(workTypeDmEntityList,WorkTypeDm.class);

        joinTemplate.joinQuery(workTypeDmList);

        return workTypeDmList;
    }

    @Override
    public Pagination<WorkTypeDm> findWorkTypeDmPage(WorkTypeDmQuery workTypeDmQuery) {
        Pagination<WorkTypeDmEntity>  pagination = workTypeDmDao.findWorkTypeDmPage(workTypeDmQuery);

        List<WorkTypeDm> workTypeDmList = BeanMapper.mapList(pagination.getDataList(),WorkTypeDm.class);

        joinTemplate.joinQuery(workTypeDmList);

        return PaginationBuilder.build(pagination,workTypeDmList);
    }

    @Override
    public WorkTypeDm findDmWorkTypeByCode(String projectId, String code) {
        WorkTypeQuery workTypeQuery = new WorkTypeQuery();
        workTypeQuery.setCode(code);
        List<WorkType> workTypeList = workTypeService.findWorkTypeList(workTypeQuery);
        String id = workTypeList.get(0).getId();

        WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
        workTypeDmQuery.setWorkTypeId(id);
        workTypeDmQuery.setProjectId(projectId);

        List<WorkTypeDm> workTypeDmList = findWorkTypeDmList(workTypeDmQuery);
        WorkTypeDm workTypeDm = workTypeDmList.get(0);

        return workTypeDm;
    }

    @Override
    public String findDemandWorkType(String projectId, String code) {
        return null;
    }
}