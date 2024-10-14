package io.tiklab.kanass.project.epic.service;

import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.kanass.project.epic.dao.EpicWorkItemDao;
import io.tiklab.kanass.project.epic.entity.EpicWorkItemEntity;
import io.tiklab.kanass.project.epic.model.Epic;
import io.tiklab.kanass.project.epic.model.EpicQuery;
import io.tiklab.kanass.project.epic.model.EpicWorkItem;
import io.tiklab.kanass.project.epic.model.EpicWorkItemQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.workitem.entity.WorkItemEntity;
import io.tiklab.kanass.workitem.service.WorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* EpicWorkItemServiceImpl
*/
@Service
public class EpicWorkItemServiceImpl implements EpicWorkItemService {

    @Autowired
    EpicWorkItemDao epicWorkItemDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    EpicService epicService;

    @Override
    public String createEpicWorkItem(@NotNull @Valid EpicWorkItem epicWorkItem) {
        EpicWorkItemEntity epicWorkItemEntity = BeanMapper.map(epicWorkItem, EpicWorkItemEntity.class);

        return epicWorkItemDao.createEpicWorkItem(epicWorkItemEntity);
    }

    @Override
    public void updateEpicWorkItem(@NotNull @Valid EpicWorkItem epicWorkItem) {
        EpicWorkItemEntity epicWorkItemEntity = BeanMapper.map(epicWorkItem, EpicWorkItemEntity.class);

        epicWorkItemDao.updateEpicWorkItem(epicWorkItemEntity);
    }

    @Override
    public void deleteEpicWorkItem(@NotNull String id) {
        epicWorkItemDao.deleteEpicWorkItem(id);
    }

    @Override
    public void deleteEpicWorkItem(EpicWorkItem epicWorkItem) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(EpicWorkItemEntity.class)
                .eq("epicId", epicWorkItem.getEpicId())
                .eq("workItemId", epicWorkItem.getWorkItem().getId())
                .get();

        epicWorkItemDao.deleteEpicWorkItem(deleteCondition);
    }

    @Override
    public EpicWorkItem findOne(String id) {
        EpicWorkItemEntity epicWorkItemEntity = epicWorkItemDao.findEpicWorkItem(id);

        EpicWorkItem epicWorkItem = BeanMapper.map(epicWorkItemEntity, EpicWorkItem.class);
        return epicWorkItem;
    }

    @Override
    public List<EpicWorkItem> findList(List<String> idList) {
        List<EpicWorkItemEntity> epicWorkItemEntityList =  epicWorkItemDao.findEpicWorkItemList(idList);

        List<EpicWorkItem> epicWorkItemList =  BeanMapper.mapList(epicWorkItemEntityList,EpicWorkItem.class);
        return epicWorkItemList;
    }

    @Override
    public EpicWorkItem findEpicWorkItem(@NotNull String id) {
        EpicWorkItem epicWorkItem = findOne(id);

        joinTemplate.joinQuery(epicWorkItem);
        return epicWorkItem;
    }

    @Override
    public List<EpicWorkItem> findAllEpicWorkItem() {
        List<EpicWorkItemEntity> epicWorkItemEntityList =  epicWorkItemDao.findAllEpicWorkItem();

        List<EpicWorkItem> epicWorkItemList =  BeanMapper.mapList(epicWorkItemEntityList,EpicWorkItem.class);

        joinTemplate.joinQuery(epicWorkItemList);
        return epicWorkItemList;
    }

    @Override
    public List<EpicWorkItem> findEpicWorkItemList(EpicWorkItemQuery epicWorkItemQuery) {
        List<EpicWorkItemEntity> epicWorkItemEntityList = epicWorkItemDao.findEpicWorkItemList(epicWorkItemQuery);

        List<EpicWorkItem> epicWorkItemList = BeanMapper.mapList(epicWorkItemEntityList,EpicWorkItem.class);

        joinTemplate.joinQuery(epicWorkItemList);

        return epicWorkItemList;
    }

    //查找需求集下面的子需求集和事项
    public Map<String, Object> findEpicChildWorkItemAndEpic(EpicWorkItemQuery epicWorkItemQuery) {
        Map<String, Object> workItemAndEpic = new HashMap<String, Object>();
        EpicQuery epicQuery = new EpicQuery();
        epicQuery.setParentId((epicWorkItemQuery.getEpicId()));
        List<Epic> epicListTree = epicService.findEpicListTree(epicQuery);
        for (Epic epic : epicListTree) {
            findEpicChild(epic);
        }
        workItemAndEpic.put("epic", epicListTree);
        List<WorkItem> workItemListByEpic = findWorkItemListByEpic(epicWorkItemQuery);
        workItemAndEpic.put("workItem", workItemListByEpic);
        return workItemAndEpic;
    }

    public void findEpicChild(Epic epic){
        EpicWorkItemQuery epicWorkItemQuery = new EpicWorkItemQuery();
        epicWorkItemQuery.setEpicId(epic.getId());
        List<WorkItem> workItemListByEpic = findWorkItemListByEpic(epicWorkItemQuery);
        epic.setChildrenWorkItem(workItemListByEpic);
        if(epic.getChildren() != null && epic.getChildren().size() > 0){
            List<Epic> children = epic.getChildren();
            for (Epic child : children) {
                findEpicChild(child);
            }

        }
    }

    @Override
    public List<WorkItem> findWorkItemListByEpic(EpicWorkItemQuery epicWorkItemQuery) {
        List<WorkItemEntity> workItemEntityList = epicWorkItemDao.findWorkItemListByEpic(epicWorkItemQuery);

        List<WorkItem> epicWorkItemList = BeanMapper.mapList(workItemEntityList,WorkItem.class);

        joinTemplate.joinQuery(epicWorkItemList);

        if(epicWorkItemList == null || epicWorkItemList.size() == 0){
            return epicWorkItemList;
        }

        //查询子事项列表
        List<WorkItem> childWorkItemList = workItemService.findChildWorkItemList(epicWorkItemList);

        //设置子事项
        if(childWorkItemList != null && childWorkItemList.size() > 0){
            for(WorkItem topWorkItem:epicWorkItemList){
                workItemService.setChildren(childWorkItemList,topWorkItem);
            }
        }

        return epicWorkItemList;
    }

    @Override
    public Pagination<EpicWorkItem> findEpicWorkItemPage(EpicWorkItemQuery epicWorkItemQuery) {
        Pagination<EpicWorkItemEntity>  pagination = epicWorkItemDao.findEpicWorkItemPage(epicWorkItemQuery);

        List<EpicWorkItem> epicWorkItemList = BeanMapper.mapList(pagination.getDataList(),EpicWorkItem.class);

        joinTemplate.joinQuery(epicWorkItemList);

        return PaginationBuilder.build(pagination,epicWorkItemList);
    }



}