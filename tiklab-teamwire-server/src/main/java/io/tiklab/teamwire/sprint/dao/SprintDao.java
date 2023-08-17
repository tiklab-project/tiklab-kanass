package io.tiklab.teamwire.sprint.dao;

import io.tiklab.teamwire.sprint.entity.SprintEntity;
import io.tiklab.teamwire.sprint.entity.SprintFocusEntity;
import io.tiklab.teamwire.sprint.model.SprintQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 迭代数据访问
 */
@Repository
public class SprintDao{

    private static Logger logger = LoggerFactory.getLogger(SprintDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建迭代
     * @param sprintEntity
     * @return
     */
    public String createSprint(SprintEntity sprintEntity) {
        return jpaTemplate.save(sprintEntity,String.class);
    }

    /**
     * 更新迭代
     * @param sprintEntity
     */
    public void updateSprint(SprintEntity sprintEntity){
        jpaTemplate.update(sprintEntity);
    }

    /**
     * 删除迭代
     * @param id
     */
    public void deleteSprint(String id){
        jpaTemplate.delete(SprintEntity.class,id);
    }

    /**
     * 根据迭代id查找迭代
     * @param id
     * @return
     */
    public SprintEntity findSprint(String id){
        return jpaTemplate.findOne(SprintEntity.class,id);
    }

    /**
    * 查找所有迭代列表
    * @return
    */
    public List<SprintEntity> findAllSprint() {
        return jpaTemplate.findAll(SprintEntity.class);
    }

    /**
     * 根据多个迭代id,查找迭代列表
     * @param idList
     * @return
     */
    public List<SprintEntity> findSprintList(List<String> idList) {
        return jpaTemplate.findList(SprintEntity.class,idList);
    }

    /**
     * 根据条件查找迭代列表
     * @param sprintQuery
     * @return
     */
    public List<SprintEntity> findSprintList(SprintQuery sprintQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(SprintEntity.class)
                .eq("projectId", sprintQuery.getProjectId())
                .eq("master", sprintQuery.getMaster())
                .like("sprintName", sprintQuery.getSprintName())
                .eq("sprintStateId", sprintQuery.getSprintStateId())
                .orders(sprintQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, SprintEntity.class);
    }

    /**
     * 根据条件按照分页查找迭代
     * @param sprintQuery
     * @return
     */
    public Pagination<SprintEntity> findSprintPage(SprintQuery sprintQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(SprintEntity.class)
                .eq("projectId", sprintQuery.getProjectId())
                .eq("master", sprintQuery.getMaster())
                .like("sprintName", sprintQuery.getSprintName())
                .eq("sprintStateId", sprintQuery.getSprintStateId())
                .orders(sprintQuery.getOrderParams())
                .pagination(sprintQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, SprintEntity.class);
    }

    /**
     * 根据条件查找我收藏的迭代
     * @param sprintQuery
     * @return
     */
    public List<SprintEntity> findFocusSprintList(SprintQuery sprintQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(SprintEntity.class, "sp")
                .leftJoin(SprintFocusEntity.class,"sf","sp.id=sf.sprintId")
                .eq("sf.masterId", sprintQuery.getMaster())
                .eq("sf.projectId", sprintQuery.getProjectId())
                .get();
        return jpaTemplate.findList(queryCondition,SprintEntity.class);
    }

    /**
     * 查找进行中的迭代
     * @return
     */
    public List<SprintEntity> findProgressSprint() {
        List<SprintEntity> SprintEntityList = null;
        String sql = "select * from pmc_sprint s";
        sql = sql.concat(" where s.sprint_state_id = 111111");
        SprintEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SprintEntity.class));

        return SprintEntityList;
    }
}