package io.tiklab.kanass.test.common.stepcommon.dao;

import io.tiklab.kanass.test.common.stepcommon.entity.StepCommonEntity;
import io.tiklab.kanass.test.common.stepcommon.model.StepCommonQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公共步骤 数据访问
 */
@Repository
public class StepCommonDao {

    private static Logger logger = LoggerFactory.getLogger(StepCommonDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建公共步骤
     * @param stepCommonEntity
     * @return
     */
    public String createStepCommon(StepCommonEntity stepCommonEntity) {
        return jpaTemplate.save(stepCommonEntity,String.class);
    }

    /**
     * 更新公共步骤
     * @param stepCommonEntity
     */
    public void updateStepCommon(StepCommonEntity stepCommonEntity){
        jpaTemplate.update(stepCommonEntity);
    }

    /**
     * 删除公共步骤
     * @param id
     */
    public void deleteStepCommon(String id){
        jpaTemplate.delete(StepCommonEntity.class,id);
    }

    public void deleteStepCommon(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找公共步骤
     * @param id
     * @return
     */
    public StepCommonEntity findStepCommon(String id){
        return jpaTemplate.findOne(StepCommonEntity.class,id);
    }

    /**
     * 查询步骤数量
     * @param caseId
     * @return
     */
    public int findStepNum(String caseId) {
        String stepSql = "Select count(1) as total from test_case_step_common where case_id = '" + caseId+ "'";
        Integer stepNum = jpaTemplate.getJdbcTemplate().queryForObject(stepSql, new Object[]{}, Integer.class);

        return stepNum;
    }



    /**
    * 查找所有公共步骤
    * @return
    */
    public List<StepCommonEntity> findAllStepCommon() {
        return jpaTemplate.findAll(StepCommonEntity.class);
    }

    public List<StepCommonEntity> findStepCommonList(List<String> idList) {
        return jpaTemplate.findList(StepCommonEntity.class,idList);
    }

    /**
     * 根据查询参数查询公共步骤列表
     * @param stepCommonQuery
     * @return
     */
    public List<StepCommonEntity> findStepCommonList(StepCommonQuery stepCommonQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(StepCommonEntity.class)
                .eq("caseId",stepCommonQuery.getCaseId())
                .orders(stepCommonQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, StepCommonEntity.class);
    }

    /**
     * 根据查询参数按分页查询公共步骤
     * @param stepCommonQuery
     * @return
     */
    public Pagination<StepCommonEntity> findStepCommonPage(StepCommonQuery stepCommonQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(StepCommonEntity.class)
                .eq("caseId",stepCommonQuery.getCaseId())
                .orders(stepCommonQuery.getOrderParams())
                .pagination(stepCommonQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, StepCommonEntity.class);
    }
}