package io.tiklab.kanass.test.func.cases.dao;

import io.tiklab.kanass.test.func.cases.entity.FuncUnitCaseEntity;
import io.tiklab.kanass.test.func.cases.model.FuncUnitCaseQuery;
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
 * 功能用例 数据访问
 */
@Repository
public class FuncUnitCaseDao {

    private static Logger logger = LoggerFactory.getLogger(FuncUnitCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建功能用例
     * @param funcUnitCaseEntity
     * @return
     */
    public String createFuncUnitCase(FuncUnitCaseEntity funcUnitCaseEntity) {
        return jpaTemplate.save(funcUnitCaseEntity,String.class);
    }

    /**
     * 更新功能用例
     * @param funcUnitCaseEntity
     */
    public void updateFuncUnitCase(FuncUnitCaseEntity funcUnitCaseEntity){
        jpaTemplate.update(funcUnitCaseEntity);
    }

    /**
     * 删除功能用例
     * @param id
     */
    public void deleteFuncUnitCase(String id){
        jpaTemplate.delete(FuncUnitCaseEntity.class,id);
    }

    public void deleteFuncUnitCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找功能用例
     * @param id
     * @return
     */
    public FuncUnitCaseEntity findFuncUnitCase(String id){
        return jpaTemplate.findOne(FuncUnitCaseEntity.class,id);
    }

    /**
    * 查找所有功能用例
    * @return
    */
    public List<FuncUnitCaseEntity> findAllFuncUnitCase() {
        return jpaTemplate.findAll(FuncUnitCaseEntity.class);
    }

    public List<FuncUnitCaseEntity> findFuncUnitCaseList(List<String> idList) {
        return jpaTemplate.findList(FuncUnitCaseEntity.class,idList);
    }

    /**
     * 根据查询参数查询列表功能用例
     * @param funcUnitCaseQuery
     * @return
     */
    public List<FuncUnitCaseEntity> findFuncUnitCaseList(FuncUnitCaseQuery funcUnitCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FuncUnitCaseEntity.class)
                .eq("testCaseId", funcUnitCaseQuery.getTestCaseId())
                .orders(funcUnitCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FuncUnitCaseEntity.class);
    }

    /**
     * 根据查询参数按分页查询功能用例
     * @param funcUnitCaseQuery
     * @return
     */
    public Pagination<FuncUnitCaseEntity> findFuncUnitCasePage(FuncUnitCaseQuery funcUnitCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FuncUnitCaseEntity.class)
                .eq("testCaseId", funcUnitCaseQuery.getTestCaseId())
                .pagination(funcUnitCaseQuery.getPageParam())
                .orders(funcUnitCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, FuncUnitCaseEntity.class);
    }
}