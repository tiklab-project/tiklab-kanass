package io.thoughtware.kanass.workitem.service;

import io.thoughtware.flow.flow.model.Flow;
import io.thoughtware.flow.flow.model.FlowModelRelation;
import io.thoughtware.flow.flow.model.FlowModelRelationQuery;
import io.thoughtware.flow.flow.service.FlowModelRelationService;
import io.thoughtware.form.form.model.FormModelRelation;
import io.thoughtware.form.form.model.FormModelRelationQuery;
import io.thoughtware.form.form.service.FormModelRelationService;
import io.thoughtware.kanass.workitem.model.*;
import io.thoughtware.core.exception.SystemException;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.form.form.model.Form;
import io.thoughtware.form.form.model.FormQuery;
import io.thoughtware.form.form.service.FormService;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.kanass.workitem.dao.WorkTypeDao;
import io.thoughtware.kanass.workitem.entity.WorkTypeEntity;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项类型服务
*/
@Service
public class WorkTypeServiceImpl implements WorkTypeService {

    @Autowired
    WorkTypeDao workTypeDao;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    FormService formService;

    @Autowired
    WorkTypeDmService workTypeDmService;

    @Autowired
    FlowModelRelationService flowModelRelationService;

    @Autowired
    FormModelRelationService formModelRelationService;

    @Override
    public String createWorkType(@NotNull @Valid WorkType workType) {
        WorkTypeEntity workTypeEntity = BeanMapper.map(workType, WorkTypeEntity.class);
        String workTypeId = workTypeDao.createWorkType(workTypeEntity);

        // 创建事项类型时候，创建类型与流程的关联记录
        FlowModelRelation flowModelRelation = new FlowModelRelation();
        flowModelRelation.setFlowId(workType.getFlow().getId());
        flowModelRelation.setModelId(workTypeId);
        flowModelRelation.setModelName(workType.getName());
        flowModelRelation.setModelType("workType");
        flowModelRelation.setBgroup("kanass");
        flowModelRelationService.createFlowModelRelation(flowModelRelation);

        FormModelRelation formModelRelation = new FormModelRelation();
        formModelRelation.setFormId(workType.getForm().getId());
        formModelRelation.setModelId(workTypeId);
        formModelRelation.setModelName(workType.getName());
        formModelRelation.setModelType("workType");
        formModelRelation.setBgroup("kanass");
        formModelRelationService.createFormModelRelation(formModelRelation);
        return workTypeId;
    }

    @Override
    public void updateWorkType(@NotNull @Valid WorkType workType) {
        WorkTypeEntity workTypeEntity = BeanMapper.map(workType, WorkTypeEntity.class);

        String workTypeId = workType.getId();
        Flow flow = workType.getFlow();
        if(!ObjectUtils.isEmpty(flow)){
            FlowModelRelationQuery flowModelRelationQuery = new FlowModelRelationQuery();
            flowModelRelationQuery.setModelId(workTypeId);
            List<FlowModelRelation> flowModelRelationList = flowModelRelationService.findFlowModelRelationList(flowModelRelationQuery);
            for (FlowModelRelation flowModelRelation : flowModelRelationList) {
                // 设置新的流程与当前事项类型的关系
                flowModelRelation.setFlowId(flow.getId());
                flowModelRelation.setModelName(workType.getName());
                flowModelRelationService.updateFlowModelRelation(flowModelRelation);
            }
        }
        Form form = workType.getForm();
        if(!ObjectUtils.isEmpty(form)){
            FormModelRelationQuery formModelRelationQuery = new FormModelRelationQuery();
            formModelRelationQuery.setModelId(workTypeId);

            List<FormModelRelation> formModelRelationList = formModelRelationService.findFormModelRelationList(formModelRelationQuery);
            for (FormModelRelation formModelRelation : formModelRelationList) {
                formModelRelation.setFormId(form.getId());
                formModelRelation.setModelName(workType.getName());
                formModelRelationService.updateFormModelRelation(formModelRelation);
            }
        }
        workTypeDao.updateWorkType(workTypeEntity);
    }

    @Override
    public String deleteWorkType(@NotNull String id) {
        // 查找当前事项类型有项目内事项类型关联
        WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
        workTypeDmQuery.setWorkTypeId(id);
        List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
        if(workTypeDmList != null && workTypeDmList.size()>0){
            throw new SystemException(3001,"类型使用中，不可删除");
        }else {
            workTypeDao.deleteWorkType(id);
            // 删除关联关系
            formModelRelationService.deleteFormModelRelationByModalId(id);
            flowModelRelationService.deleteFlowModelRelationByModelId(id);
            return "SUCCESS";
        }
    }



    @Override
    public WorkType findOne(String id) {
        WorkTypeEntity workTypeEntity = workTypeDao.findWorkType(id);

        return BeanMapper.map(workTypeEntity, WorkType.class);
    }

    @Override
    public List<WorkType> findList(List<String> idList) {
        List<WorkTypeEntity> workTypeEntityList =  workTypeDao.findWorkTypeList(idList);

        return BeanMapper.mapList(workTypeEntityList,WorkType.class);
    }

    @Override
    public WorkType findWorkType(@NotNull String id) {
        WorkType workType = findOne(id);

        joinTemplate.joinQuery(workType);
        return workType;
    }



    @Override
    public List<WorkType> findAllWorkType() {
        List<WorkTypeEntity> workTypeEntityList =  workTypeDao.findAllWorkType();

        List<WorkType> workTypeList = BeanMapper.mapList(workTypeEntityList,WorkType.class);

        joinTemplate.joinQuery(workTypeList);
        return workTypeList;
    }

    @Override
    public List<WorkType> findWorkTypeList(WorkTypeQuery workTypeQuery) {
        List<WorkTypeEntity> workTypeEntityList = workTypeDao.findWorkTypeList(workTypeQuery);

        List<WorkType> workTypeList = BeanMapper.mapList(workTypeEntityList,WorkType.class);
        for (WorkType workType : workTypeList) {
            String workTypeId = workType.getId();
            WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
            workTypeDmQuery.setWorkTypeId(workTypeId);
            List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
            int size = workTypeDmList.size();
            workType.setUseNumber(size);
        }

        joinTemplate.joinQuery(workTypeList);
        return workTypeList;
    }



    @Override
    public Pagination<WorkType> findWorkTypePage(WorkTypeQuery workTypeQuery) {

        Pagination<WorkTypeEntity>  pagination = workTypeDao.findWorkTypePage(workTypeQuery);

        List<WorkType> workTypeList = BeanMapper.mapList(pagination.getDataList(),WorkType.class);

        joinTemplate.joinQuery(workTypeList);

        return PaginationBuilder.build(pagination,workTypeList);
    }

    @Override
    public Form findFormConfig(String formId) {
        FormQuery formQuery = new FormQuery();
        formQuery.setId(formId);
        formQuery.setGroup("custom");
        //查找表单配置
        Form form = formService.findFormWithNest(formQuery);
        return form;
    }

    @Override
    public String findWorkTypeByCode(@NotNull String code) {

        List<WorkTypeEntity> workTypeEntityList = workTypeDao.findWorkTypeByCode(code);
        String id = workTypeEntityList.get(0).getId();

        return id;
    }
}