package io.tiklab.teamwire.project.version.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teamwire.project.version.model.VersionStateQuery;
import io.tiklab.teamwire.project.version.entity.VersionStateEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 迭代状态数据访问
 */
@Repository
public class VersionStateDao {

    private static Logger logger = LoggerFactory.getLogger(VersionStateDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建迭代状态
     * @param sprintStateEntity
     * @return
     */
    public String createVersionState(VersionStateEntity sprintStateEntity) {
        return jpaTemplate.save(sprintStateEntity,String.class);
    }

    /**
     * 更新迭代状态
     * @param sprintStateEntity
     */
    public void updateVersionState(VersionStateEntity sprintStateEntity){
        jpaTemplate.update(sprintStateEntity);
    }

    /**
     * 删除迭代状态
     * @param id
     */
    public void deleteVersionState(String id){
        jpaTemplate.delete(VersionStateEntity.class,id);
    }

    public void deleteVersionState(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找迭代状态
     * @param id
     * @return
     */
    public VersionStateEntity findVersionState(String id){
        return jpaTemplate.findOne(VersionStateEntity.class,id);
    }

    /**
    * 查找所有迭代状态
    * @return
    */
    public List<VersionStateEntity> findAllVersionState() {
        return jpaTemplate.findAll(VersionStateEntity.class);
    }

    /**
     * 根据ids 查找迭代状态列表
     * @param idList
     * @return
     */
    public List<VersionStateEntity> findVersionStateList(List<String> idList) {
        return jpaTemplate.findList(VersionStateEntity.class,idList);
    }

    /**
     * 根据条件查询迭代状态列表
     * @param sprintStateQuery
     * @return
     */
    public List<VersionStateEntity> findVersionStateList(VersionStateQuery sprintStateQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(VersionStateEntity.class)
                .orders(sprintStateQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, VersionStateEntity.class);
    }

    /**
     * 根据条件按分页查询迭代状态列表
     * @param sprintStateQuery
     * @return
     */
    public Pagination<VersionStateEntity> findVersionStatePage(VersionStateQuery sprintStateQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(VersionStateEntity.class)
                .orders(sprintStateQuery.getOrderParams())
                .pagination(sprintStateQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, VersionStateEntity.class);
    }
}