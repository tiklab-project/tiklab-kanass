package io.tiklab.kanass.project.appraised.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.project.appraised.entity.AppraisedTypeEntity;
import io.tiklab.kanass.project.appraised.model.AppraisedTypeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppraisedTypeDao {
    private static Logger logger = LoggerFactory.getLogger(AppraisedTypeDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建迭代状态
     * @param appraisedTypeEntity
     * @return
     */
    public String createAppraisedType(AppraisedTypeEntity appraisedTypeEntity) {
        return jpaTemplate.save(appraisedTypeEntity,String.class);
    }

    /**
     * 更新迭代状态
     * @param appraisedTypeEntity
     */
    public void updateAppraisedType(AppraisedTypeEntity appraisedTypeEntity){
        jpaTemplate.update(appraisedTypeEntity);
    }

    /**
     * 删除迭代状态
     * @param id
     */
    public void deleteAppraisedType(String id){
        jpaTemplate.delete(AppraisedTypeEntity.class,id);
    }

    public void deleteAppraisedType(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找迭代状态
     * @param id
     * @return
     */
    public AppraisedTypeEntity findAppraisedType(String id){
        return jpaTemplate.findOne(AppraisedTypeEntity.class,id);
    }

    /**
     * 查找所有迭代状态
     * @return
     */
    public List<AppraisedTypeEntity> findAllAppraisedType() {
        return jpaTemplate.findAll(AppraisedTypeEntity.class);
    }

    /**
     * 根据ids 查找迭代状态列表
     * @param idList
     * @return
     */
    public List<AppraisedTypeEntity> findAppraisedTypeList(List<String> idList) {
        return jpaTemplate.findList(AppraisedTypeEntity.class,idList);
    }

    /**
     * 根据条件查询迭代状态列表
     * @param appraisedTypeQuery
     * @return
     */
    public List<AppraisedTypeEntity> findAppraisedTypeList(AppraisedTypeQuery appraisedTypeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppraisedTypeEntity.class)
                .orders(appraisedTypeQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AppraisedTypeEntity.class);
    }

    /**
     * 根据条件按分页查询迭代状态列表
     * @param appraisedTypeQuery
     * @return
     */
    public Pagination<AppraisedTypeEntity> findAppraisedTypePage(AppraisedTypeQuery appraisedTypeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppraisedTypeEntity.class)
                .orders(appraisedTypeQuery.getOrderParams())
                .pagination(appraisedTypeQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, AppraisedTypeEntity.class);
    }
}
