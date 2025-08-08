package io.tiklab.kanass.test.func.cases.dao;

import io.tiklab.kanass.test.func.cases.entity.FuncUnitStepEntity;
import io.tiklab.kanass.test.func.cases.model.FuncUnitStepQuery;
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
 * 功能用例下步骤 数据访问
 */
@Repository
public class FuncUnitStepDao{

    private static Logger logger = LoggerFactory.getLogger(FuncUnitStepDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建功能用例下步骤
     * @param funcUnitStepEntity
     * @return
     */
    public String createFuncUnitStep(FuncUnitStepEntity funcUnitStepEntity) {
        return jpaTemplate.save(funcUnitStepEntity,String.class);
    }

    /**
     * 更新功能用例下步骤
     * @param funcUnitStepEntity
     */
    public void updateFuncUnitStep(FuncUnitStepEntity funcUnitStepEntity){
        jpaTemplate.update(funcUnitStepEntity);
    }

    /**
     * 删除功能用例下步骤
     * @param id
     */
    public void deleteFuncUnitStep(String id){
        jpaTemplate.delete(FuncUnitStepEntity.class,id);
    }

    public void deleteFuncUnitStep(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找功能用例下步骤
     * @param id
     * @return
     */
    public FuncUnitStepEntity findFuncUnitStep(String id){
        return jpaTemplate.findOne(FuncUnitStepEntity.class,id);
    }

    /**
    * 查找所有功能用例下步骤
    * @return
    */
    public List<FuncUnitStepEntity> findAllFuncUnitStep() {
        return jpaTemplate.findAll(FuncUnitStepEntity.class);
    }

    public List<FuncUnitStepEntity> findFuncUnitStepList(List<String> idList) {
        return jpaTemplate.findList(FuncUnitStepEntity.class,idList);
    }

    /**
     * 根据查询参数查询功能用例下步骤列表
     * @param funcUnitStepQuery
     * @return
     */
    public List<FuncUnitStepEntity> findFuncUnitStepList(FuncUnitStepQuery funcUnitStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FuncUnitStepEntity.class)
                .eq("funcUnitId", funcUnitStepQuery.getFuncUnitId())
                .orders(funcUnitStepQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition,FuncUnitStepEntity.class);
    }

    /**
     * 根据查询参数按分页查询功能用例下步骤
     * @param funcUnitStepQuery
     * @return
     */
    public Pagination<FuncUnitStepEntity> findFuncUnitStepPage(FuncUnitStepQuery funcUnitStepQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FuncUnitStepEntity.class)
                .eq("funcUnitId", funcUnitStepQuery.getFuncUnitId())
                .orders(funcUnitStepQuery.getOrderParams())
                .pagination(funcUnitStepQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,FuncUnitStepEntity.class);
    }
}