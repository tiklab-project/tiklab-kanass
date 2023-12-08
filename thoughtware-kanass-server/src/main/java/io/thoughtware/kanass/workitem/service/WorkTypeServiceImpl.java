package io.thoughtware.kanass.workitem.service;

import io.thoughtware.kanass.workitem.model.*;
import io.thoughtware.core.exception.SystemException;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.form.form.model.Form;
import io.thoughtware.form.form.model.FormQuery;
import io.thoughtware.form.form.service.FormService;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.kanass.workitem.dao.WorkTypeDao;
import io.thoughtware.kanass.workitem.entity.WorkTypeEntity;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String createWorkType(@NotNull @Valid WorkType workType) {
        WorkTypeEntity workTypeEntity = BeanMapper.map(workType, WorkTypeEntity.class);
        String workTypeId = workTypeDao.createWorkType(workTypeEntity);
        return workTypeId;
    }

    @Override
    public void updateWorkType(@NotNull @Valid WorkType workType) {
        WorkTypeEntity workTypeEntity = BeanMapper.map(workType, WorkTypeEntity.class);

        workTypeDao.updateWorkType(workTypeEntity);
    }

    @Override
    public String deleteWorkType(@NotNull String id) {
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setWorkTypeId(id);
        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
        if(workItemList != null && workItemList.size()>0){
            throw new SystemException(3001,"类型使用中，不可删除");
        }else {
            workTypeDao.deleteWorkType(id);
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