package io.tiklab.kanass.project.version.dao;

import io.tiklab.kanass.project.version.entity.ProjectVersionEntity;
import io.tiklab.kanass.project.version.entity.VersionFocusEntity;
import io.tiklab.kanass.project.version.model.ProjectVersionQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
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
 * 项目版本数据访问
 */
@Repository
public class ProjectVersionDao {

    private static Logger logger = LoggerFactory.getLogger(ProjectVersionDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建项目版本
     * @param projectVersionEntity
     * @return
     */
    public String createVersion(ProjectVersionEntity projectVersionEntity) {
        return jpaTemplate.save(projectVersionEntity,String.class);
    }

    /**
     * 更新项目版本
     * @param projectVersionEntity
     */
    public void updateVersion(ProjectVersionEntity projectVersionEntity){
        jpaTemplate.update(projectVersionEntity);
    }

    /**
     * 删除项目版本
     * @param id
     */
    public void deleteVersion(String id){
        jpaTemplate.delete(ProjectVersionEntity.class,id);
    }

    /**
     * 根据id查找项目版本
     * @param id
     * @return
     */
    public ProjectVersionEntity findVersion(String id){
        return jpaTemplate.findOne(ProjectVersionEntity.class,id);
    }

    /**
    * 查找所有项目版本
    * @return
    */
    public List<ProjectVersionEntity> findAllVersion() {
        return jpaTemplate.findAll(ProjectVersionEntity.class);
    }

    /**
     * 根据多个id 查找项目版本列表
     * @param idList
     * @return
     */
    public List<ProjectVersionEntity> findVersionList(List<String> idList) {
        return jpaTemplate.findList(ProjectVersionEntity.class,idList);
    }

    /**
     * 根据条件查找项目版本列表
     * @param ProjectVersionQuery
     * @return
     */
    public List<ProjectVersionEntity> findVersionList(ProjectVersionQuery ProjectVersionQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectVersionEntity.class, "pv")
                .leftJoin(VersionFocusEntity.class, "vf", "vf.versionId=pv.id")
                .eq("pv.projectId", ProjectVersionQuery.getProjectId())
                .eq("pv.versionState", ProjectVersionQuery.getVersionState())
                .like("pv.name", ProjectVersionQuery.getName())
                .orders(ProjectVersionQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ProjectVersionEntity.class);
    }

    /**
     * 根据条件按照分页查找项目版本列表
     * @param projectVersionQuery
     * @return
     */
    public Pagination<ProjectVersionEntity> findVersionPage(ProjectVersionQuery projectVersionQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(ProjectVersionEntity.class, "pv")
                .eq("pv.projectId", projectVersionQuery.getProjectId())
                .like("pv.name", projectVersionQuery.getName())
                .eq("pv.versionState", projectVersionQuery.getVersionState())
                .eq("pv.master", projectVersionQuery.getMasterId())
                .eq("pv.builder", projectVersionQuery.getBuilderId())
                .in("pv.versionState", projectVersionQuery.getVersionStates())
                .orders(projectVersionQuery.getOrderParams())
                .pagination(projectVersionQuery.getPageParam());
        if(projectVersionQuery.getFollowersId() != null){
            queryBuilders = queryBuilders.leftJoin(VersionFocusEntity.class, "vf", "vf.versionId=pv.id")
                    .eq("vf.masterId", projectVersionQuery.getFollowersId());

        }
        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findPage(queryCondition, ProjectVersionEntity.class);
    }

    public List<ProjectVersionEntity> findVersionFocusList(ProjectVersionQuery projectVersionQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectVersionEntity.class,"pv")
                .leftJoin(VersionFocusEntity.class, "vefo","vefo.versionId=pv.id")
                .eq("vefo.masterId", projectVersionQuery.getMasterId ())
                .eq("vefo.projectId", projectVersionQuery.getProjectId())
                .orders(projectVersionQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ProjectVersionEntity.class);
    }

    public List<ProjectVersionEntity> findSelectVersionList(ProjectVersionQuery projectVersionQuery) {
        String currentVersionId = projectVersionQuery.getCurrentVersionId();
        String projectId = projectVersionQuery.getProjectId();
        String sql = "SELECT * FROM pmc_version WHERE id != '" + currentVersionId + "' and " +
                "version_state != '222222' and project_id = '" + projectId + "' order by start_time desc";
        List<ProjectVersionEntity> versionEntityList = this.jpaTemplate.getJdbcTemplate().
                query(sql, new BeanPropertyRowMapper(ProjectVersionEntity.class));
        return versionEntityList;
    }

    public List<ProjectVersionEntity> findWorkVersionList(String workId){
        String sql = "select ve.* from pmc_version ve LEFT JOIN pmc_work_version wv " +
                "on ve.id = wv.version_id WHERE wv.work_item_id = '" + workId  + "'";
        List<ProjectVersionEntity> projectVersionEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ProjectVersionEntity.class));
        return  projectVersionEntityList;
    }


    public Map<String, Integer> findVersionCount(ProjectVersionQuery projectVersionQuery) {
        String userId = projectVersionQuery.getBuilderId();
        String projectId = projectVersionQuery.getProjectId();
        Map<String, Integer>  countMap = new HashMap<>();

        String sql1 = "select count(*) as count from pmc_version where project_id = '" + projectId + "' ";
        if (StringUtils.isNotBlank(projectVersionQuery.getName())){
            sql1 = sql1.concat(" and name like '%" + projectVersionQuery.getName() + "%'");
        }
        if (projectVersionQuery.getVersionStates() != null && projectVersionQuery.getVersionStates().length != 0){
            sql1 = sql1.concat(" and version_state in ('" + StringUtils.join(projectVersionQuery.getVersionStates(), "','") + "')");
        }
        Integer total = jpaTemplate.getJdbcTemplate().queryForObject(sql1, new Object[]{}, Integer.class);
        countMap.put("total", total);

        String sql2 = "select count(*) as count from pmc_version where project_id = '" + projectId + "' and builder = '" + userId + "' ";
        if (StringUtils.isNotBlank(projectVersionQuery.getName())){
            sql2 = sql2.concat(" and name like '%" + projectVersionQuery.getName() + "%'");
        }
        if (projectVersionQuery.getVersionStates() != null && projectVersionQuery.getVersionStates().length != 0){
            sql2 = sql2.concat(" and version_state in ('" + StringUtils.join(projectVersionQuery.getVersionStates(), "','") + "')");
        }
        Integer myCreated = jpaTemplate.getJdbcTemplate().queryForObject(sql2, new Object[]{}, Integer.class);
        countMap.put("myCreated", myCreated);

        String sql3 = "select count(*) as count from pmc_version_focus pvf left join pmc_version pv on pv.id = pvf.version_id " +
                " where pv.project_id = '" + projectId + "' and pv.builder = '" + userId + "' ";
        if (StringUtils.isNotBlank(projectVersionQuery.getName())){
            sql2 = sql2.concat(" and pv.name like '%" + projectVersionQuery.getName() + "%'");
        }
        if (projectVersionQuery.getVersionStates() != null && projectVersionQuery.getVersionStates().length != 0){
            sql2 = sql2.concat(" and pv.version_state in ('" + StringUtils.join(projectVersionQuery.getVersionStates(), "','") + "')");
        }
        Integer myFocus = jpaTemplate.getJdbcTemplate().queryForObject(sql3, new Object[]{}, Integer.class);
        countMap.put("myFocus", myFocus);

        return countMap;
    }

}