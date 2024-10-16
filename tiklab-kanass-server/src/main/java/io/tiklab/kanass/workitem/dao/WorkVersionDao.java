package io.tiklab.kanass.workitem.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.workitem.entity.WorkVersionEntity;
import io.tiklab.kanass.workitem.model.WorkVersionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class WorkVersionDao {

    private static Logger logger = LoggerFactory.getLogger(WorkVersionDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项类型
     * @param workVersionEntity
     * @return
     */
    public String createWorkVersion(WorkVersionEntity workVersionEntity) {
        return jpaTemplate.save(workVersionEntity,String.class);
    }

    /**
     * 更新事项类型
     * @param workVersionEntity
     */
    public void updateWorkVersion(WorkVersionEntity workVersionEntity){
        jpaTemplate.update(workVersionEntity);
    }

    /**
     * 删除事项类型
     * @param id
     */
    public void deleteWorkVersion(String id){
        jpaTemplate.delete(WorkVersionEntity.class,id);
    }
    public void deleteWorkVersionList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }
    /**
     * 根据id查找事项类型
     * @param id
     * @return
     */
    public WorkVersionEntity findWorkVersion(String id){
        return jpaTemplate.findOne(WorkVersionEntity.class,id);
    }

    /**
    * 查找所有事项类型
    * @return
    */
    public List<WorkVersionEntity> findAllWorkVersion() {
        return jpaTemplate.findAll(WorkVersionEntity.class);
    }

    public List<WorkVersionEntity> findWorkVersionList(List<String> idList) {
        return jpaTemplate.findList(WorkVersionEntity.class,idList);
    }

    /**
     * 根据条件查询事项类型列表
     * @param workVersionQuery
     * @return
     */
    public List<WorkVersionEntity> findWorkVersionList(WorkVersionQuery workVersionQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkVersionEntity.class)
                .eq("versionId", workVersionQuery.getVersionId())
                .eq("workItemId", workVersionQuery.getWorkItemId())
                .orders(workVersionQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkVersionEntity.class);
    }

    /**
     * 根据条件按分页查询事项类型列表
     * @param workVersionQuery
     * @return
     */
    public Pagination<WorkVersionEntity> findWorkVersionPage(WorkVersionQuery workVersionQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkVersionEntity.class)
                .eq("versionId", workVersionQuery.getVersionId())
                .orders(workVersionQuery.getOrderParams())
                .pagination(workVersionQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkVersionEntity.class);
    }

    public void createBatchWorkVersion(String valueStrings){
        String sql = "INSERT INTO pmc_work_version (id, work_item_id, version_id) VALUES " + valueStrings + ";";
        jpaTemplate.getJdbcTemplate().execute(sql);
    }

    public List<String> findVersionWorkItemNum(String ids) {
        String sql = "select version_id  from pmc_work_version where version_id in "+ ids;
        List<String> versionIdList = this.jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        return versionIdList;
    }
}