package io.tiklab.kanass.sprint.dao;

import io.tiklab.kanass.sprint.entity.SprintEntity;
import io.tiklab.kanass.sprint.entity.SprintFocusEntity;
import io.tiklab.kanass.sprint.model.SprintQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void deleteSprintFocus(String sprintId){
        // 删除迭代与事项的关系
        String sql = "UPDATE pmc_work_item SET sprint_id = NULL WHERE sprint_id = '" + sprintId + "'";
        jpaTemplate.getJdbcTemplate().execute(sql);
          // 删除关注的迭代
        sql = "DELETE FROM pmc_sprint_focus where sprint_id = '" + sprintId + "'";
        int update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除关注的迭代成功");
        }else {
            logger.info("删除关注的迭代失败");
        }

        // 删除迭代燃尽图数据
        sql = "DELETE FROM pmc_sprint_burndowm where sprint_id = '" + sprintId + "'";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除迭代燃尽图数据成功");
        }else {
            logger.info("删除迭代燃尽图数据失败");
        }

        // 删除最近点击的迭代
        sql = "DELETE FROM pmc_recent where model_id = '" + sprintId + "'";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除迭代燃尽图数据成功");
        }else {
            logger.info("删除迭代燃尽图数据失败");
        }


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
        QueryBuilders queryBuilders = QueryBuilders.createQuery(SprintEntity.class, "sp")
                .eq("sp.projectId", sprintQuery.getProjectId())
                .eq("sp.master", sprintQuery.getMaster())
                .like("sp.sprintName", sprintQuery.getSprintName())
                .eq("sp.sprintStateId", sprintQuery.getSprintStateId())
                .in("sp.sprintStateId", sprintQuery.getSprintStateIds())
                .eq("sp.builder", sprintQuery.getBuilderId())
                .orders(sprintQuery.getOrderParams());

        if(sprintQuery.getFollowersId() != null){
            queryBuilders = queryBuilders.leftJoin(SprintFocusEntity.class, "sf", "sf.sprintId=sp.id")
                    .eq("sf.masterId", sprintQuery.getFollowersId());

        }
        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findList(queryCondition, SprintEntity.class);
    }

    public List<SprintEntity> findSelectSprintList(SprintQuery sprintQuery) {
        String currentSprintId = sprintQuery.getCurrentSprintId();
        String projectId = sprintQuery.getProjectId();
        String sql = "SELECT * FROM pmc_sprint WHERE id != '" + currentSprintId + "' and " +
                "sprint_state_id != '222222' and project_id = '" + projectId + "' order by start_time desc";
        List<SprintEntity> sprintEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SprintEntity.class));
        return sprintEntityList;
    }

    /**
     * 根据条件按照分页查找迭代
     * @param sprintQuery
     * @return
     */
    public Pagination<SprintEntity> findSprintPage(SprintQuery sprintQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(SprintEntity.class, "sp")
                .eq("sp.projectId", sprintQuery.getProjectId())
                .eq("sp.master", sprintQuery.getMaster())
                .like("sp.sprintName", sprintQuery.getSprintName())
                .eq("sp.sprintStateId", sprintQuery.getSprintStateId())
                .in("sp.sprintStateId", sprintQuery.getSprintStateIds())
                .eq("sp.builder", sprintQuery.getBuilderId())
                .orders(sprintQuery.getOrderParams())
                .pagination(sprintQuery.getPageParam());

        if(sprintQuery.getFollowersId() != null){
            queryBuilders = queryBuilders.leftJoin(SprintFocusEntity.class, "sf", "sf.sprintId=sp.id")
                    .eq("sf.masterId", sprintQuery.getFollowersId());
        }
        QueryCondition queryCondition = queryBuilders.get();
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

    public List<SprintEntity> findWorkSprintList(String workId){
        String sql = "select sr.* from pmc_sprint sr LEFT JOIN pmc_work_sprint " +
                "ws on sr.id = ws.sprint_id WHERE ws.work_item_id = '" + workId  + "'";
        List SprintEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SprintEntity.class));
        return  SprintEntityList;
    }

    public Map<String, Integer> findSprintCount(SprintQuery sprintQuery) {
        String userId = sprintQuery.getBuilderId();
        String projectId = sprintQuery.getProjectId();
        Map<String, Integer>  countMap = new HashMap<>();

        String sql1 = "select count(*) as count from pmc_sprint where project_id = '" + projectId + "' ";
        if (StringUtils.isNotBlank(sprintQuery.getSprintName())){
            sql1 = sql1.concat(" and sprint_name like '%" + sprintQuery.getSprintName() + "%'");
        }
        if (sprintQuery.getSprintStateIds() != null && sprintQuery.getSprintStateIds().length != 0){
            sql1 = sql1.concat(" and sprint_state_id in ('" + StringUtils.join(sprintQuery.getSprintStateIds(), "','") + "')");
        }
        Integer total = jpaTemplate.getJdbcTemplate().queryForObject(sql1, new Object[]{}, Integer.class);
        countMap.put("total", total);

        String sql2 = "select count(*) as count from pmc_sprint where project_id = '" + projectId + "' and builder = '" + userId + "' ";
        if (StringUtils.isNotBlank(sprintQuery.getSprintName())){
            sql2 = sql2.concat(" and sprint_name like '%" + sprintQuery.getSprintName() + "%'");
        }
        if (sprintQuery.getSprintStateIds() != null && sprintQuery.getSprintStateIds().length != 0){
            sql2 = sql2.concat(" and sprint_state_id in ('" + StringUtils.join(sprintQuery.getSprintStateIds(), "','") + "')");
        }
        Integer myCreated = jpaTemplate.getJdbcTemplate().queryForObject(sql2, new Object[]{}, Integer.class);
        countMap.put("myCreated", myCreated);

        String sql3 = "select count(*) as count from pmc_sprint_focus psf left join pmc_sprint ps on ps.id = psf.sprint_id " +
                " where ps.project_id = '" + projectId + "' and ps.builder = '" + userId + "' ";
        if (StringUtils.isNotBlank(sprintQuery.getSprintName())){
            sql2 = sql2.concat(" and ps.sprint_name like '%" + sprintQuery.getSprintName() + "%'");
        }
        if (sprintQuery.getSprintStateIds() != null && sprintQuery.getSprintStateIds().length != 0){
            sql2 = sql2.concat(" and ps.sprint_state_id in ('" + StringUtils.join(sprintQuery.getSprintStateIds(), "','") + "')");
        }
        Integer myFocus = jpaTemplate.getJdbcTemplate().queryForObject(sql3, new Object[]{}, Integer.class);
        countMap.put("myFocus", myFocus);

        return countMap;
    }
}