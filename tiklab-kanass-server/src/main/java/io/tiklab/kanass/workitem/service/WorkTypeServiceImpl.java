package io.tiklab.kanass.workitem.service;

import io.tiklab.flow.flow.model.Flow;
import io.tiklab.flow.flow.model.FlowModelRelation;
import io.tiklab.flow.flow.model.FlowModelRelationQuery;
import io.tiklab.flow.flow.service.FlowModelRelationService;
import io.tiklab.flow.flow.service.FlowService;
import io.tiklab.form.form.model.FormModelRelation;
import io.tiklab.form.form.model.FormModelRelationQuery;
import io.tiklab.form.form.service.FormModelRelationService;
import io.tiklab.ids.tenant.common.TenantHolder;
import io.tiklab.kanass.common.ErrorCode;
import io.tiklab.kanass.workitem.model.*;
import io.tiklab.core.exception.SystemException;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.form.form.model.Form;
import io.tiklab.form.form.model.FormQuery;
import io.tiklab.form.form.service.FormService;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.workitem.dao.WorkTypeDao;
import io.tiklab.kanass.workitem.entity.WorkTypeEntity;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

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
    WorkTypeDmService workTypeDmService;

    @Autowired
    FlowService flowService;

    @Autowired
    WorkTypeService workTypeService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    FormService formService;

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
    public void updateWorkType1(){
//        List<WorkType> allWorkType = workTypeService.findAllWorkType();
//        for (WorkType workType : allWorkType) {
//            String id = workType.getFlow().getForm().getId();
//            Form form = new Form();
//            form.setId(id);
//            workType.setForm(form);
//            workTypeService.updateWorkType(workType);
//        }
//
//        List<WorkTypeDm> allWorkTypeDm = workTypeDmService.findAllWorkTypeDm();
//        System.out.println("走了");
//        for (WorkTypeDm workTypeDm : allWorkTypeDm) {
//            String id = workTypeDm.getFlow().getId();
//            Flow flow = flowService.findFlow(id);
//            if(!Objects.isNull(flow)){
//                String formId = flow.getForm().getId();
//                Form form = new Form();
//                form.setId(formId);
//                workTypeDm.setForm(form);
//                workTypeDmService.updateWorkTypeDm(workTypeDm);
//            }
//        }
    }


    @Override
    public String deleteWorkType(@NotNull String id) {
        // 查找当前事项类型有项目内事项类型关联
        WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
        workTypeDmQuery.setWorkTypeId(id);
        List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
        if(workTypeDmList != null && workTypeDmList.size()>0){
            throw new SystemException(ErrorCode.DELETE_CODE,"类型使用中，不可删除");
        }else {
            workTypeDao.deleteWorkType(id);

            FlowModelRelationQuery flowModelRelationQuery = new FlowModelRelationQuery();
            flowModelRelationQuery.setModelId(id);
            flowModelRelationService.deleteFlowModelRelationByCondition(flowModelRelationQuery);
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

    @Override
    public Integer findAllWorkTypeNum() {
        Integer allWorkTypeNum = workTypeDao.findAllWorkTypeNum();
        return allWorkTypeNum;
    }
}