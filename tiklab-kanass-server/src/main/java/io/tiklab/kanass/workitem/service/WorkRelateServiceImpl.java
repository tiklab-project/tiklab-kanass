package io.tiklab.kanass.workitem.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkRelate;
import io.tiklab.kanass.workitem.model.WorkRelateQuery;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.workitem.dao.WorkRelateDao;
import io.tiklab.kanass.workitem.entity.WorkRelateEntity;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 关联事项服务
*/
@Service
public class WorkRelateServiceImpl implements WorkRelateService {

    @Autowired
    WorkRelateDao workRelateDao;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createWorkRelate(@NotNull @Valid WorkRelate workRelate) {
        WorkRelateEntity workRelateEntity = BeanMapper.map(workRelate, WorkRelateEntity.class);

        return workRelateDao.createWorkRelate(workRelateEntity);
    }

    @Override
    public void updateWorkRelate(@NotNull @Valid WorkRelate workRelate) {
        WorkRelateEntity workRelateEntity = BeanMapper.map(workRelate, WorkRelateEntity.class);

        workRelateDao.updateWorkRelate(workRelateEntity);
    }

    @Override
    public void deleteWorkRelate(@NotNull String id) {
        workRelateDao.deleteWorkRelate(id);
    }

    @Override
    public void deleteWorkRelateCondition(@NotNull @Valid WorkRelateQuery workRelateQuery) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkRelateEntity.class)
                .in("workItemId", workRelateQuery.getWorkItemIds())
                .eq("workItemId", workRelateQuery.getWorkItemId())
                .get();
        workRelateDao.deleteWorkRelateCondition(deleteCondition);
    }

    @Override
    public WorkRelate findWorkRelate(@NotNull String id) {
        WorkRelateEntity workRelateEntity = workRelateDao.findWorkRelate(id);

        WorkRelate workRelate = BeanMapper.map(workRelateEntity, WorkRelate.class);

        joinTemplate.joinQuery(workRelate, new String[]{"workItem", "relateWorkItem"});

        return workRelate;
    }

    @Override
    public List<WorkRelate> findAllWorkRelate() {
        List<WorkRelateEntity> workRelateEntityList =  workRelateDao.findAllWorkRelate();

        List<WorkRelate> workRelateList =  BeanMapper.mapList(workRelateEntityList,WorkRelate.class);

        joinTemplate.joinQuery(workRelateList, new String[]{"workItem", "relateWorkItem"});

        return workRelateList;
    }

    @Override
    public List<Map<String, Object>> findWorkRelateList(WorkRelateQuery workRelateQuery) {
        //查询工作关联实体列表
        List<WorkRelateEntity> workRelateListEntity = workRelateDao.findWorkRelateList(workRelateQuery);
        List<Map<String, Object>> workRelateList = new ArrayList<Map<String, Object>>();
        if(workRelateListEntity != null){
            for(WorkRelateEntity workRelate:workRelateListEntity){
                HashMap<String, Object> workRelateMap = new HashMap<>();
                String relateItemId = workRelate.getRelateItemId();
                WorkItem workItem = workItemService.findWorkItem(relateItemId);
                workRelateMap.put("workItem", workItem);
                workRelateMap.put("id", workRelate.getId());
                workRelateMap.put("workItemId", workRelate.getWorkItemId());
                workRelateList.add(workRelateMap);
            }
        }

        return workRelateList;
    }


    @Override
    public Pagination<WorkRelate> findWorkRelatePage(WorkRelateQuery workRelateQuery) {

        Pagination<WorkRelateEntity>  pagination = workRelateDao.findWorkRelatePage(workRelateQuery);

        List<WorkRelate> workRelateList = BeanMapper.mapList(pagination.getDataList(),WorkRelate.class);

        joinTemplate.joinQuery(workRelateList, new String[]{"workItem", "relateWorkItem"});

        return PaginationBuilder.build(pagination,workRelateList);

    }
}