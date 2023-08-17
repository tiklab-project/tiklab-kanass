package io.tiklab.teamwire.workitem.dao;

import io.tiklab.teamwire.workitem.entity.WorkTypeDmEntity;
import io.tiklab.teamwire.workitem.entity.WorkTypeEntity;
import io.tiklab.teamwire.workitem.model.WorkTypeDmQuery;
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
 * 项目的事项类型数据访问
 */
@Repository
public class WorkTypeDmDao{

    private static Logger logger = LoggerFactory.getLogger(WorkTypeDmDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项类型
     * @param workTypeDmEntity
     * @return
     */
    public String createWorkTypeDm(WorkTypeDmEntity workTypeDmEntity) {
        return jpaTemplate.save(workTypeDmEntity,String.class);
    }

    /**
     * 更新事项类型
     * @param workTypeDmEntity
     */
    public void updateWorkTypeDm(WorkTypeDmEntity workTypeDmEntity){
        jpaTemplate.update(workTypeDmEntity);
    }

    /**
     * 删除事项类型
     * @param id
     */
    public void deleteWorkTypeDm(String id){
        jpaTemplate.delete(WorkTypeDmEntity.class,id);
    }

    public void deleteWorkTypeDm(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找事项类型
     * @param id
     * @return
     */
    public WorkTypeDmEntity findWorkTypeDm(String id){
        return jpaTemplate.findOne(WorkTypeDmEntity.class,id);
    }

    /**
    * 查找所有事项类型列表
    * @return
    */
    public List<WorkTypeDmEntity> findAllWorkTypeDm() {
        return jpaTemplate.findAll(WorkTypeDmEntity.class);
    }

    public List<WorkTypeDmEntity> findWorkTypeDmList(List<String> idList) {
        return jpaTemplate.findList(WorkTypeDmEntity.class,idList);
    }

    /**
     * 根据条件查询事项类型列表
     * @param workTypeDmQuery
     * @return
     */
    public List<WorkTypeDmEntity> findWorkTypeDmList(WorkTypeDmQuery workTypeDmQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkTypeDmEntity.class, "wd")
                .leftJoin( WorkTypeEntity.class, "wt","wd.workTypeId=wt.id")
                .eq("wd.projectId",workTypeDmQuery.getProjectId())
                .eq("wd.workTypeId",workTypeDmQuery.getWorkTypeId())
                .eq("wt.grouper", workTypeDmQuery.getGrouper())
                .eq("wt.code", workTypeDmQuery.getCode())
                .orders(workTypeDmQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkTypeDmEntity.class);
    }

    /**
     * 根据条件按分页查询事项类型列表
     * @param workTypeDmQuery
     * @return
     */
    public Pagination<WorkTypeDmEntity> findWorkTypeDmPage(WorkTypeDmQuery workTypeDmQuery) {
        return jpaTemplate.findPage(workTypeDmQuery,WorkTypeDmEntity.class);
    }
}