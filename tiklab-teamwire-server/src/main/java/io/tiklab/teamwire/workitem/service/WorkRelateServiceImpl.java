package io.tiklab.teamwire.workitem.service;

import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.teamwire.workitem.model.WorkRelate;
import io.tiklab.teamwire.workitem.model.WorkRelateQuery;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.teamwire.workitem.dao.WorkRelateDao;
import io.tiklab.teamwire.workitem.entity.WorkRelateEntity;

import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
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
    public WorkRelate findWorkRelate(@NotNull String id) {
        WorkRelateEntity workRelateEntity = workRelateDao.findWorkRelate(id);

        WorkRelate workRelate = BeanMapper.map(workRelateEntity, WorkRelate.class);

        joinTemplate.joinQuery(workRelate);

        return workRelate;
    }

    @Override
    public List<WorkRelate> findAllWorkRelate() {
        List<WorkRelateEntity> workRelateEntityList =  workRelateDao.findAllWorkRelate();

        List<WorkRelate> workRelateList =  BeanMapper.mapList(workRelateEntityList,WorkRelate.class);

        joinTemplate.joinQuery(workRelateList);

        return workRelateList;
    }

    @Override
    public List<Map<String, Object>> findWorkRelateList(WorkRelateQuery workRelateQuery) {
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

        joinTemplate.joinQuery(workRelateList);

        return PaginationBuilder.build(pagination,workRelateList);

    }
}