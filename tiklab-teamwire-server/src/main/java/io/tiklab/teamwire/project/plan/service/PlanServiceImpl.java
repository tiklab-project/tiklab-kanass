package io.tiklab.teamwire.project.plan.service;

import io.tiklab.teamwire.project.plan.dao.PlanDao;
import io.tiklab.teamwire.project.plan.entity.PlanEntity;
import io.tiklab.teamwire.project.plan.model.Plan;
import io.tiklab.teamwire.project.plan.model.PlanQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.teamwire.workitem.model.WorkItemQuery;
import io.tiklab.teamwire.workitem.service.WorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* 计划服务
*/
@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    PlanDao planDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    WorkItemService workItemService;

    @Override
    public String createPlan(@NotNull @Valid Plan plan) {
        PlanEntity planEntity = BeanMapper.map(plan, PlanEntity.class);

        return planDao.createPlan(planEntity);
    }

    @Override
    public void updatePlan(@NotNull @Valid Plan plan) {
        PlanEntity planEntity = BeanMapper.map(plan, PlanEntity.class);

        planDao.updatePlan(planEntity);
    }

    @Override
    public void deletePlan(@NotNull String id) {
        planDao.deletePlan(id);
    }

    @Override
    public Plan findOne(String id) {
        PlanEntity planEntity = planDao.findPlan(id);

        Plan plan = BeanMapper.map(planEntity, Plan.class);
        return plan;
    }

    @Override
    public List<Plan> findList(List<String> idList) {
        List<PlanEntity> planEntityList =  planDao.findPlanList(idList);

        List<Plan> planList =  BeanMapper.mapList(planEntityList,Plan.class);
        return planList;
    }

    @Override
    public Plan findPlan(@NotNull String id) {
        Plan plan = findOne(id);

        joinTemplate.joinQuery(plan);

        return plan;
    }

    @Override
    public List<Plan> findAllPlan() {
        List<PlanEntity> planEntityList =  planDao.findAllPlan();

        List<Plan> planList =  BeanMapper.mapList(planEntityList,Plan.class);

        joinTemplate.joinQuery(planList);

        return planList;
    }

    @Override
    public List<Plan> findPlanList(PlanQuery planQuery) {
        List<PlanEntity> planEntityList = planDao.findPlanList(planQuery);

        List<Plan> planList = BeanMapper.mapList(planEntityList,Plan.class);

        joinTemplate.joinQuery(planList);

        return planList;
    }

    @Override
    public List<Plan> findPlanList(PlanQuery planQuery, boolean isJoinQuery) {
        List<PlanEntity> planEntityList = planDao.findPlanList(planQuery);

        List<Plan> planList = BeanMapper.mapList(planEntityList,Plan.class);


        if(isJoinQuery){
            joinTemplate.joinQuery(planList);
        }
        return planList;
    }

    @Override
    public Pagination<Plan> findPlanPage(PlanQuery planQuery) {
        Pagination<PlanEntity>  pagination = planDao.findPlanPage(planQuery);

        List<Plan> planList = BeanMapper.mapList(pagination.getDataList(),Plan.class);

        joinTemplate.joinQuery(planList);

        return PaginationBuilder.build(pagination,planList);
    }

    @Override
    public Pagination<Plan> findPlanPageTree(PlanQuery planQuery) {
        //按分页查找列表
        Pagination<Plan> pg = findPlanPage(planQuery);
        List<Plan> topPlanList = pg.getDataList();
        if(topPlanList == null || topPlanList.size() == 0){
            return pg;
        }
        //查询所有子事项列表
        List<WorkItem> workItemList = findPlanWorkItemList(topPlanList);
        //查询所有子计划列表
        List<Plan> childPlanList = findChildPlanList(topPlanList);

        //设置子事项
        if(workItemList != null && workItemList.size() > 0){
            for(Plan topPlan:topPlanList){
                setWorkItemList(topPlan);
            }
        }
        //设置子事项
        if(childPlanList != null && childPlanList.size() > 0){
            for(Plan topPlan:topPlanList){
                setChildren(childPlanList,topPlan);
            }
        }
        return pg;
    }

    /**
     * 根据上级事项列表查询所有子事项列表
     * @param planList
     * @return
     */
    List<Plan> findChildPlanList(List<Plan> planList){
        //拼接ids查询范围
        List<String> idList = new ArrayList<>();
        for(Plan plan:planList){
            idList.add(plan.getId());
        }
        String[] ids = new String[idList.size()];
        ids = idList.toArray(ids);

        //查询所有子事项
        PlanQuery planQuery = new PlanQuery();
        planQuery.setParentIdIn(ids);

        List<Plan> childPlanList = findPlanList(planQuery,false);
        return childPlanList;
    }


    /**
     * 根据计划列表查询所有子事项列表
     * @param planList
     * @return
     */
    List<WorkItem> findPlanWorkItemList(List<Plan> planList){
        //拼接ids查询范围
        List<String> idList = new ArrayList<>();
        for(Plan plan:planList){
            idList.add(plan.getId());
        }
        String[] ids = new String[idList.size()];
        ids = idList.toArray(ids);

        //查找关联计划的事项
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setPlanIdIn(ids);
        List<WorkItem> childPlanList = workItemService.findWorkItemList(workItemQuery,false);
        return childPlanList;
    }

    /**
     * 查找并设置子事项列表
     * @param matchPlanList
     * @param parentPlan
     * @return
     */
    void setChildren(List<Plan> matchPlanList, Plan parentPlan){
        List<Plan> childList = matchPlanList.stream()
                .filter(plan -> (plan.getParentPlan() != null && plan.getParentPlan().getId() != null && plan.getParentPlan().getId().equals(parentPlan.getId())))
                .collect(Collectors.toList());

        if(childList != null && childList.size()>0){
            List<Plan> list = new ArrayList<>();
            for(Plan childPlan:childList){
                PlanQuery planQuery = new PlanQuery();
                planQuery.setId(childPlan.getId());
                Pagination<Plan> pg = findPlanPageTree(planQuery);
                List<Plan> planList = pg.getDataList();
                list.add(planList.get(0));
            }
            parentPlan.setChildren(list);
        }
    }

    /**
     * 查找并设置子事项列表
     * @param parentPlan
     * @return
     */
    void setWorkItemList(Plan parentPlan){
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setPlanId(parentPlan.getId());
        List<WorkItem> workItemList = workItemService.findPlanWorkItemPage(workItemQuery);
        setWorkItemChildren(workItemList);
        parentPlan.setWorkItemList(workItemList);

    }

    /**
     * 查找事项的子级事项
     * @param workItemList
     */
    void setWorkItemChildren(List<WorkItem> workItemList){
        for(WorkItem workItem:workItemList){
            WorkItemQuery workItemQuery1 = new WorkItemQuery();
            workItemQuery1.setParentId(workItem.getId());
            List<WorkItem> wi = workItemService.findWorkItemListTree(workItemQuery1);
            workItem.setChildren(wi);
            for(WorkItem workItemChildren:wi){
                if(workItemChildren.getChildren() != null && workItemChildren.getChildren().size() > 0){
                    setWorkItemChildren(wi);
                }else {
                    return ;
                }

            }
        }
    }
}