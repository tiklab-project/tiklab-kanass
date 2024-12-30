package io.tiklab.kanass.project.epic.service;

import io.tiklab.kanass.project.epic.dao.EpicDao;
import io.tiklab.kanass.project.epic.entity.EpicEntity;
import io.tiklab.kanass.project.epic.model.Epic;
import io.tiklab.kanass.project.epic.model.EpicQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 需求池（史诗）接口，弃用
*/
@Service
public class EpicServiceImpl implements EpicService {

    @Autowired
    EpicDao epicDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createEpic(@NotNull @Valid Epic epic) {
        EpicEntity epicEntity = BeanMapper.map(epic, EpicEntity.class);

        return epicDao.createEpic(epicEntity);
    }

    @Override
    public void updateEpic(@NotNull @Valid Epic epic) {
        EpicEntity epicEntity = BeanMapper.map(epic, EpicEntity.class);

        epicDao.updateEpic(epicEntity);
    }

    @Override
    public void deleteEpic(@NotNull String id) {
        epicDao.deleteEpic(id);
    }

    @Override
    public Epic findOne(String id) {
        EpicEntity epicEntity = epicDao.findEpic(id);

        Epic epic = BeanMapper.map(epicEntity, Epic.class);
        return epic;
    }

    @Override
    public List<Epic> findList(List<String> idList) {
        List<EpicEntity> epicEntityList =  epicDao.findEpicList(idList);

        List<Epic> epicList =  BeanMapper.mapList(epicEntityList,Epic.class);
        return epicList;
    }

    @Override
    public Epic findEpic(@NotNull String id) {
        Epic epic = findOne(id);

        joinTemplate.joinQuery(epic);
        return epic;
    }

    @Override
    public List<Epic> findAllEpic() {
        List<EpicEntity> epicEntityList =  epicDao.findAllEpic();

        List<Epic> epicList =  BeanMapper.mapList(epicEntityList,Epic.class);

        joinTemplate.joinQuery(epicList);
        return epicList;
    }

    @Override
    public List<Epic> findEpicList(EpicQuery epicQuery) {
        List<EpicEntity> epicEntityList = epicDao.findEpicList(epicQuery);

        List<Epic> epicList = BeanMapper.mapList(epicEntityList,Epic.class);

        joinTemplate.joinQuery(epicList);

        return epicList;
    }

    /**
     * 获取需求池的属性结构，
     * @param epicQuery
     * @return
     */
    @Override
    public List<Epic> findEpicListTree(EpicQuery epicQuery) {
//        epicQuery.setEpicParentNull(true);
        List<Epic> epicList = findEpicList(epicQuery);

        if(epicList == null || epicList.size() == 0){
            return epicList;
        }
        for (Epic epic : epicList) {
            List<Epic> childEpicList = findChildEpicList(epicQuery, epic.getId());
            epic.setChildren(childEpicList);
        }
        return epicList;
    }

    public List<Epic> findChildEpicList(EpicQuery epicQuery, String parentId) {
        epicQuery.setEpicParentNull(false);
        epicQuery.setParentId(parentId);
        List<Epic> epicList = findEpicList(epicQuery);

        if(epicList == null || epicList.size() == 0){
            return epicList;
        }

        for (Epic epic : epicList) {
            List<Epic> childEpicList = findChildEpicList(epicQuery, epic.getId());
            epic.setChildren(childEpicList);
        }
        return epicList;
    }

    @Override
    public Pagination<Epic> findEpicPage(EpicQuery epicQuery) {
        Pagination<EpicEntity> pagination = epicDao.findEpicPage(epicQuery);

        List<Epic> epicList = BeanMapper.mapList(pagination.getDataList(),Epic.class);

        joinTemplate.joinQuery(epicList);

        return PaginationBuilder.build(pagination,epicList);
    }
}