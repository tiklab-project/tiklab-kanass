package io.tiklab.teamwire.workitem.dao;

import io.tiklab.flow.statenode.entity.StateNodeEntity;
import io.tiklab.teamwire.workitem.entity.WorkStatusEntity;
import io.tiklab.teamwire.workitem.model.WorkStatusQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 事项状态数据访问
 */
@Repository
public class WorkStatusDao{

    private static Logger logger = LoggerFactory.getLogger(WorkStatusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;


    /**
     * 创建事项状态
     * @param workStatusEntity
     * @return
     */
    public String createWorkStatus(WorkStatusEntity workStatusEntity) {
        //设置序号
        Integer max = findMax(workStatusEntity.getGroup());
        workStatusEntity.setSort(max+1);

        return jpaTemplate.save(workStatusEntity,String.class);
    }

    /**
     * 查找序号最大值
     * @param group
     * @return
     */
    Integer findMax(String group){
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkStatusEntity.class)
                .eq("group",group)
                .max("sort")
                .get();
        Integer max = jpaTemplate.findObject(queryCondition,Integer.class);
        if(max == null){
            return 0;
        }
        return max;
    }

    /**
     * 更新事项状态
     * @param workStatusEntity
     */
    public void updateWorkStatus(WorkStatusEntity workStatusEntity){
        jpaTemplate.update(workStatusEntity);
    }

    /**
     * 删除事项状态
     * @param id
     */
    public void deleteWorkStatus(String id){
        jpaTemplate.delete(WorkStatusEntity.class,id);
    }

    /**
     * 根据id查找事项状态
     * @param id
     * @return
     */
    public WorkStatusEntity findWorkStatus(String id){
        return jpaTemplate.findOne(WorkStatusEntity.class,id);
    }

    public List<WorkStatusEntity> findWorkStatusList(List<String> idList) {
        return jpaTemplate.findList(WorkStatusEntity.class,idList);
    }

    /**
    * 查找所有事项状态列表
    * @return
    */
    public List<WorkStatusEntity> findAllWorkStatus() {
        return jpaTemplate.findAll(WorkStatusEntity.class);
    }

    /**
     * 根据条件查询事项状态列表
     * @param workStatusQuery
     * @return
     */
    public List<WorkStatusEntity> findWorkStatusList(WorkStatusQuery workStatusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkStatusEntity.class)
                .like("name", workStatusQuery.getName())
                .orders(workStatusQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkStatusEntity.class);
    }

    /**
     * 根据状态类型查找状态列表
     * @param workStatusQuery
     * @return
     */
    public List<WorkStatusEntity> findWorkStatusListBySorts(WorkStatusQuery workStatusQuery) {
        List<Integer> list = workStatusQuery.getSorts();
        Integer[] workItemStatusTypeIds = new Integer[list.size()];
        workItemStatusTypeIds = list.toArray(workItemStatusTypeIds);

        QueryCondition queryCondition = QueryBuilders.createQuery(StateNodeEntity.class)
                .in("node_status",workItemStatusTypeIds)
                .get();

        List<WorkStatusEntity> WorkItemStatusList = jpaTemplate.findList(queryCondition, WorkStatusEntity.class);
        return WorkItemStatusList;
    }

    /**
     * 按分页查询事项状态列表
     * @param workStatusQuery
     * @return
     */
    public Pagination<WorkStatusEntity> findWorkStatusPage(WorkStatusQuery workStatusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkStatusEntity.class)
                .like("name", workStatusQuery.getName())
                .orders(workStatusQuery.getOrderParams())
                .pagination(workStatusQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkStatusEntity.class);
    }

}