package io.thoughtware.kanass.projectset.dao;

import io.thoughtware.kanass.projectset.entity.ProjectSetEntity;
import io.thoughtware.kanass.projectset.entity.ProjectSetFocusEntity;
import io.thoughtware.kanass.projectset.model.ProjectSetQuery;
import io.thoughtware.kanass.support.entity.RecentEntity;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.kanass.project.project.entity.ProjectEntity;
import io.thoughtware.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目集数据访问
 */
@Repository
public class ProjectSetDao{

    private static Logger logger = LoggerFactory.getLogger(ProjectSetDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建项目集
     * @param projectSetEntity
     * @return
     */
    public String createProjectSet(ProjectSetEntity projectSetEntity) {
        //设置序号
        Integer max = findMax();
        projectSetEntity.setSort(max+1);
        return jpaTemplate.save(projectSetEntity,String.class);
    }

    /**
     * 更新项目集
     * @param projectSetEntity
     */
    public void updateProjectSet(ProjectSetEntity projectSetEntity){
        jpaTemplate.update(projectSetEntity);
    }

    /**
     * 删除项目集
     * @param id
     */
    public void deleteProjectSet(String id){
        jpaTemplate.delete(ProjectSetEntity.class,id);
    }

    /**
     * 根据条件删除项目集
     * @param deleteCondition
     */
    public void deleteProjectSet(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找项目集
     * @param id
     * @return
     */
    public ProjectSetEntity findProjectSet(String id){
        return jpaTemplate.findOne(ProjectSetEntity.class,id);
    }

    /**
    * 查找所有项目集
    * @return
    */
    public List<ProjectSetEntity> findAllProjectSet() {

        return jpaTemplate.findAll(ProjectSetEntity.class);
    }

    /**
     * 根据多个id,查找项目集列表
     * @param idList
     * @return
     */
    public List<ProjectSetEntity> findProjectSetList(List<String> idList) {
        return jpaTemplate.findList(ProjectSetEntity.class,idList);
    }

    /**
     * 根据条件查询项目集列表
     * @param projectSetQuery
     * @return
     */
    public List<ProjectSetEntity> findProjectSetList(ProjectSetQuery projectSetQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectSetEntity.class)
                .like("name", projectSetQuery.getName())
                .eq("master", projectSetQuery.getMaster())
                .orders(projectSetQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ProjectSetEntity.class);
    }

    /**
     * 根据条件按分页查询项目集列表
     * @param projectSetQuery
     * @return
     */
    public Pagination<ProjectSetEntity> findProjectSetPage(ProjectSetQuery projectSetQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectSetEntity.class)
                .like("name", projectSetQuery.getName())
                .eq("master", projectSetQuery.getMaster())
                .orders(projectSetQuery.getOrderParams())
                .pagination(projectSetQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ProjectSetEntity.class);
    }

    /**
     * 根据条件查找关注的项目集列表
     * @param projectSetQuery
     * @return
     */
    public List<ProjectSetEntity> findFocusProjectSetList(ProjectSetQuery projectSetQuery){
        QueryCondition queryBuilders =  QueryBuilders.createQuery(ProjectSetEntity.class, "ps")
                .leftJoin(ProjectSetFocusEntity.class,"pf","pf.projectSetId=ps.id")
                .like("ps.name", projectSetQuery.getName())
                .eq("pf.masterId", projectSetQuery.getFocusMasterId())
                .get();
        return jpaTemplate.findList(queryBuilders, ProjectSetEntity.class);
    }

    /**
     * 查找最近查看的项目集列表
     * @param projectSetQuery
     * @return
     */
    public List<ProjectSetEntity> findRecentProjectSetList(ProjectSetQuery projectSetQuery){
        QueryCondition queryBuilders =  QueryBuilders.createQuery(ProjectSetEntity.class, "ps")
                .leftJoin(RecentEntity.class,"re","re.modelId=ps.id")
                .like("ps.name", projectSetQuery.getName())
                .eq("re.masterId", projectSetQuery.getRecentMasterId())
                .orders(projectSetQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryBuilders, ProjectSetEntity.class);
    }



    /**
     * 查询项目集下面的项目数量
     * @param
     * @return
     */
    public Integer findProjectNum(String projectSetId){
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectEntity.class)
                .eq("projectSetId",projectSetId)
                .count("projectSetId")
                .get();
        Integer num = jpaTemplate.findObject(queryCondition, Integer.class);
        return  num;
    }

    /**
     * 查找序号最大值
     * @param
     * @return
     */
    Integer findMax(){
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectSetEntity.class)
                .max("sort")
                .get();
        Integer max = jpaTemplate.findObject(queryCondition,Integer.class);
        if(max == null){
            return 0;
        }
        return max;
    }

    /**
     * 查找在项目ids 中的事项列表
     * @param
     * @return
     */
    public List<WorkItem> findWorkItemList(List<String> prjectIds){
        String sql = "SELECT * FROM pmc_work_item WHERE project_id in (:prjectIds)";
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("prjectIds", prjectIds);
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(getJdbcTemplate());
        List query = jdbc.query(sql, paramMap, new BeanPropertyRowMapper(WorkItem.class));
        return query;
    }



    public JdbcTemplate getJdbcTemplate() {

        return jpaTemplate.getJdbcTemplate();
    }
}