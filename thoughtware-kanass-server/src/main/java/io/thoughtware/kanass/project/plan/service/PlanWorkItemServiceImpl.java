package io.thoughtware.kanass.project.plan.service;

import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.kanass.project.plan.dao.PlanWorkItemDao;
import io.thoughtware.kanass.project.plan.entity.PlanWorkItemEntity;
import io.thoughtware.kanass.project.plan.model.PlanWorkItem;
import io.thoughtware.kanass.project.plan.model.PlanWorkItemQuery;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 计划事项关联关系服务
*/
@Service
public class PlanWorkItemServiceImpl implements PlanWorkItemService {

    @Autowired
    PlanWorkItemDao planWorkItemDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createPlanWorkItem(@NotNull @Valid PlanWorkItem planWorkItem) {
        PlanWorkItemEntity planWorkItemEntity = BeanMapper.map(planWorkItem, PlanWorkItemEntity.class);

        return planWorkItemDao.createPlanWorkItem(planWorkItemEntity);
    }

    @Override
    public void updatePlanWorkItem(@NotNull @Valid PlanWorkItem planWorkItem) {
        PlanWorkItemEntity planWorkItemEntity = BeanMapper.map(planWorkItem, PlanWorkItemEntity.class);

        planWorkItemDao.updatePlanWorkItem(planWorkItemEntity);
    }

    @Override
    public void deletePlanWorkItem(@NotNull String id) {
        planWorkItemDao.deletePlanWorkItem(id);
    }

    @Override
    public void deletePlanWorkItem(PlanWorkItem planWorkItem) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(PlanWorkItemEntity.class)
                .eq("planId", planWorkItem.getPlanId())
                .eq("workItemId", planWorkItem.getWorkItem().getId())
                .get();

        planWorkItemDao.deletePlanWorkItem(deleteCondition);
    }

    @Override
    public PlanWorkItem findOne(String id) {
        PlanWorkItemEntity planWorkItemEntity = planWorkItemDao.findPlanWorkItem(id);

        PlanWorkItem planWorkItem = BeanMapper.map(planWorkItemEntity, PlanWorkItem.class);
        return planWorkItem;
    }

    @Override
    public List<PlanWorkItem> findList(List<String> idList) {
        List<PlanWorkItemEntity> planWorkItemEntityList =  planWorkItemDao.findPlanWorkItemList(idList);

        List<PlanWorkItem> planWorkItemList =  BeanMapper.mapList(planWorkItemEntityList,PlanWorkItem.class);
        return planWorkItemList;
    }

    @Override
    public PlanWorkItem findPlanWorkItem(@NotNull String id) {
        PlanWorkItem planWorkItem = findOne(id);

        joinTemplate.joinQuery(planWorkItem);

        return planWorkItem;
    }

    @Override
    public List<PlanWorkItem> findAllPlanWorkItem() {
        List<PlanWorkItemEntity> planWorkItemEntityList = planWorkItemDao.findAllPlanWorkItem();

        List<PlanWorkItem> planWorkItemList =  BeanMapper.mapList(planWorkItemEntityList,PlanWorkItem.class);

        joinTemplate.joinQuery(planWorkItemList);

        return planWorkItemList;
    }

    @Override
    public List<PlanWorkItem> findPlanWorkItemList(PlanWorkItemQuery planWorkItemQuery) {
        List<PlanWorkItemEntity> planWorkItemEntityList = planWorkItemDao.findPlanWorkItemList(planWorkItemQuery);

        List<PlanWorkItem> planWorkItemList = BeanMapper.mapList(planWorkItemEntityList,PlanWorkItem.class);

        joinTemplate.joinQuery(planWorkItemList);

        return planWorkItemList;
    }

    @Override
    public Pagination<PlanWorkItem> findPlanWorkItemPage(PlanWorkItemQuery planWorkItemQuery) {
        Pagination<PlanWorkItemEntity>  pagination = planWorkItemDao.findPlanWorkItemPage(planWorkItemQuery);

        List<PlanWorkItem> planWorkItemList = BeanMapper.mapList(pagination.getDataList(),PlanWorkItem.class);

        joinTemplate.joinQuery(planWorkItemList);

        return PaginationBuilder.build(pagination,planWorkItemList);
    }
}