package io.thoughtware.kanass.workitem.dao;

import io.thoughtware.kanass.workitem.entity.WorkTypeEntity;
import io.thoughtware.kanass.workitem.model.WorkTypeQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class WorkTypeDao{

    private static Logger logger = LoggerFactory.getLogger(WorkTypeDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项类型
     * @param workTypeEntity
     * @return
     */
    public String createWorkType(WorkTypeEntity workTypeEntity) {
        return jpaTemplate.save(workTypeEntity,String.class);
    }

    /**
     * 更新事项类型
     * @param workTypeEntity
     */
    public void updateWorkType(WorkTypeEntity workTypeEntity){
        jpaTemplate.update(workTypeEntity);
    }

    /**
     * 删除事项类型
     * @param id
     */
    public void deleteWorkType(String id){
        jpaTemplate.delete(WorkTypeEntity.class,id);
    }

    /**
     * 根据id查找事项类型
     * @param id
     * @return
     */
    public WorkTypeEntity findWorkType(String id){
        return jpaTemplate.findOne(WorkTypeEntity.class,id);
    }

    /**
    * 查找所有事项类型
    * @return
    */
    public List<WorkTypeEntity> findAllWorkType() {
        return jpaTemplate.findAll(WorkTypeEntity.class);
    }

    public List<WorkTypeEntity> findWorkTypeList(List<String> idList) {
        return jpaTemplate.findList(WorkTypeEntity.class,idList);
    }

    /**
     * 根据条件查询事项类型列表
     * @param workTypeQuery
     * @return
     */
    public List<WorkTypeEntity> findWorkTypeList(WorkTypeQuery workTypeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkTypeEntity.class, "wt")
                .like("wt.name", workTypeQuery.getName())
                .eq("wt.code",workTypeQuery.getCode())
                .eq("wt.grouper",workTypeQuery.getGrouper())
                .eq("wt.scope", workTypeQuery.getScope())
                .notIn("wt.id", workTypeQuery.getSelectIds())
                .orders(workTypeQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkTypeEntity.class);
    }

    /**
     * 根据编码查找事项类型
     * @param code
     * @return
     */
    public List<WorkTypeEntity> findWorkTypeByCode(String code) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkTypeEntity.class)
                .eq("code",code)
                .get();
        return jpaTemplate.findList(queryCondition, WorkTypeEntity.class);
    }

    /**
     * 根据条件按分页查询事项类型列表
     * @param workTypeQuery
     * @return
     */
    public Pagination<WorkTypeEntity> findWorkTypePage(WorkTypeQuery workTypeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkTypeEntity.class)
                .like("name", workTypeQuery.getName())
                .eq("grouper", workTypeQuery.getGrouper())
                .eq("code",workTypeQuery.getCode())
                .orders(workTypeQuery.getOrderParams())
                .pagination(workTypeQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkTypeEntity.class);
    }

}