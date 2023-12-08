package io.thoughtware.kanass.project.epic.dao;

import io.thoughtware.kanass.project.epic.entity.EpicEntity;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.kanass.project.epic.model.EpicQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 史诗数据访问
 */
@Repository
public class EpicDao{

    private static Logger logger = LoggerFactory.getLogger(EpicDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建史诗
     * @param epicEntity
     * @return
     */
    public String createEpic(EpicEntity epicEntity) {
        return jpaTemplate.save(epicEntity,String.class);
    }

    /**
     * 更新史诗
     * @param epicEntity
     */
    public void updateEpic(EpicEntity epicEntity){
        jpaTemplate.update(epicEntity);
    }

    /**
     * 删除史诗
     * @param id
     */
    public void deleteEpic(String id){
        jpaTemplate.delete(EpicEntity.class,id);
    }

    public void deleteEpic(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找史诗
     * @param id
     * @return
     */
    public EpicEntity findEpic(String id){
        return jpaTemplate.findOne(EpicEntity.class,id);
    }

    /**
    * 查找全部史诗
    * @return
    */
    public List<EpicEntity> findAllEpic() {
        return jpaTemplate.findAll(EpicEntity.class);
    }

    /**
     * 根据多个id 查找史诗列表
     * @param idList
     * @return
     */
    public List<EpicEntity> findEpicList(List<String> idList) {
        return jpaTemplate.findList(EpicEntity.class,idList);
    }

    /**
     * 根据条件查找多个史诗列表
     * @param epicQuery
     * @return List<EpicEntity>
     */
    public List<EpicEntity> findEpicList(EpicQuery epicQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(EpicEntity.class)
                .eq("projectId", epicQuery.getProjectId())
                .eq("parentId", epicQuery.getParentId())
                .like("epicName", epicQuery.getEpicName())
                .orders(epicQuery.getOrderParams());

        if(epicQuery.getEpicParentNull() != null && epicQuery.getEpicParentNull()){
            queryBuilders = queryBuilders.isNull("parentId");
        }

        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findList(queryCondition,EpicEntity.class);
    }

    /**
     * 根据条件按分页查找史诗
     * @param epicQuery
     * @return
     */
    public Pagination<EpicEntity> findEpicPage(EpicQuery epicQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EpicEntity.class)
                .eq("projectId", epicQuery.getProjectId())
                .like("epicName", epicQuery.getEpicName())
                .orders(epicQuery.getOrderParams())
                .pagination(epicQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,EpicEntity.class);
    }
}