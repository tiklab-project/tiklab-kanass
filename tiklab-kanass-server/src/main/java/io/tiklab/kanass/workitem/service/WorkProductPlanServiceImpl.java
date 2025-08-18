package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.kanass.workitem.dao.WorkProductPlanDao;
import io.tiklab.kanass.workitem.entity.WorkProductPlanEntity;
import io.tiklab.kanass.workitem.model.WorkProductPlan;
import io.tiklab.kanass.workitem.model.WorkProductPlanQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 事项状态服务
 */
@Service
public class WorkProductPlanServiceImpl implements WorkProductPlanService {

    @Autowired
    WorkProductPlanDao workProductPlanDao;

    @Override
    public String createWorkProductPlan(@NotNull @Valid WorkProductPlan workProductPlan) {
        String productPlanId = workProductPlan.getProductPlanId();
        if (CollectionUtils.isNotEmpty(workProductPlan.getWorkItemIds())){
            for (String workItemId : workProductPlan.getWorkItemIds()) {
                WorkProductPlanEntity workProductPlanEntity = new WorkProductPlanEntity();
                workProductPlanEntity.setProductPlanId(productPlanId);
                workProductPlanEntity.setWorkItemId(workItemId);

                workProductPlanDao.createWorkProductPlan(workProductPlanEntity);
                workProductPlanDao.updateWorkItemProductPlan(workItemId, productPlanId);
            }
            return "";
        }else {
            WorkProductPlanEntity workProductPlanEntity = BeanMapper.map(workProductPlan, WorkProductPlanEntity.class);

            return workProductPlanDao.createWorkProductPlan(workProductPlanEntity);
        }


    }

    @Override
    public void createBatchWorkProductPlan(@NotNull String valueStrings) {
        workProductPlanDao.createBatchWorkProductPlan(valueStrings);

    }
    @Override
    public void updateWorkProductPlan(@NotNull @Valid WorkProductPlan workProductPlan) {
        WorkProductPlanEntity workProductPlanEntity = BeanMapper.map(workProductPlan, WorkProductPlanEntity.class);

        workProductPlanDao.updateWorkProductPlan(workProductPlanEntity);
    }

    @Override
    public void deleteWorkProductPlan(@NotNull String id) {
        workProductPlanDao.deleteWorkProductPlan(id);
    }

    @Override
    public void deleteWorkProductPlan(WorkProductPlanQuery workProductPlanQuery) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkProductPlanEntity.class)
                .eq("workItemId", workProductPlanQuery.getWorkItemId())
                .in("workItemId", workProductPlanQuery.getWorkItemIds())
                .get();
        workProductPlanDao.deleteWorkProductPlanList(deleteCondition);
        if (workProductPlanQuery.getWorkItemIds() != null){
            for (String workItemId : workProductPlanQuery.getWorkItemIds()) {
                workProductPlanDao.updateWorkItemProductPlan(workItemId, null);
            }
        }else {
            workProductPlanDao.updateWorkItemProductPlan(workProductPlanQuery.getWorkItemId(), null);
        }
    }

    @Override
    public WorkProductPlan findOne(String id) {
        WorkProductPlanEntity workProductPlanEntity = workProductPlanDao.findWorkProductPlan(id);

        return BeanMapper.map(workProductPlanEntity, WorkProductPlan.class);
    }

    @Override
    public List<WorkProductPlan> findList(List<String> idList) {
        List<WorkProductPlanEntity> workProductPlanEntityList = workProductPlanDao.findWorkProductPlanList(idList);

        return BeanMapper.mapList(workProductPlanEntityList,WorkProductPlan.class);
    }

    @Override
    public WorkProductPlan findWorkProductPlan(@NotNull String id) {
        return findOne(id);
    }

    @Override
    public List<WorkProductPlan> findAllWorkProductPlan() {
        List<WorkProductPlanEntity> workProductPlanEntityList =  workProductPlanDao.findAllWorkProductPlan();

        return BeanMapper.mapList(workProductPlanEntityList,WorkProductPlan.class);
    }

    @Override
    public List<WorkProductPlan> findWorkProductPlanList(WorkProductPlanQuery workProductPlanQuery) {
        List<WorkProductPlanEntity> workProductPlanEntityList = workProductPlanDao.findWorkProductPlanList(workProductPlanQuery);

        return BeanMapper.mapList(workProductPlanEntityList,WorkProductPlan.class);
    }



    @Override
    public Pagination<WorkProductPlan> findWorkProductPlanPage(WorkProductPlanQuery workProductPlanQuery) {

        Pagination<WorkProductPlanEntity>  pagination = workProductPlanDao.findWorkProductPlanPage(workProductPlanQuery);

        List<WorkProductPlan> workProductPlanList = BeanMapper.mapList(pagination.getDataList(),WorkProductPlan.class);

        return PaginationBuilder.build(pagination,workProductPlanList);

    }

    @Override
    public List<String> findProductPlanWorkItemIds(String productPlanId) {
        List<String> workItemIds = workProductPlanDao.findProductPlanWorkItemIds(productPlanId);
        return workItemIds;
    }


    @Override
    public List<Map<String, String>> findProductPlanWorkItemNum(String productPlanIds) {
        List<Map<String, String>> workItemIds = workProductPlanDao.findProductPlanWorkItemNum(productPlanIds);
        return workItemIds;
    }

}