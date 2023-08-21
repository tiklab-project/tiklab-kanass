package io.tiklab.teamwire.project.project.dao;

import io.tiklab.teamwire.project.project.entity.ProjectEntity;
import io.tiklab.teamwire.project.project.entity.ProjectFocusEntity;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.model.ProjectQuery;
import io.tiklab.teamwire.support.entity.RecentEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.OrQueryCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.OrQueryBuilders;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.teamwire.workitem.entity.WorkItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 项目数据访问
 */
@Repository
public class ProjectDao{

    private static Logger logger = LoggerFactory.getLogger(ProjectDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建项目
     * @param projectEntity
     * @return
     */
    public String createProject(ProjectEntity projectEntity) {
        return jpaTemplate.save(projectEntity,String.class);
    }

    /**
     * 更新项目
     * @param projectEntity
     */
    public void updateProject(ProjectEntity projectEntity){
        jpaTemplate.update(projectEntity);
    }

    /**
     * 删除项目
     * @param id
     */
    public void deleteProject(String id){
        jpaTemplate.delete(ProjectEntity.class,id);
    }

    /**
     * 根据id查找项目
     * @param id
     * @return
     */
    public ProjectEntity findProject(String id){
        return jpaTemplate.findOne(ProjectEntity.class,id);
    }

    public List<ProjectEntity> findProjectList(List<String> idList) {
        return jpaTemplate.findList(ProjectEntity.class,idList);
    }

    /**
    * 查找所有项目
    * @return
    */
    public List<ProjectEntity> findAllProject() {
        return jpaTemplate.findAll(ProjectEntity.class);
    }

    /**
     * 根据查询对象查询项目列表
     * @param projectQuery
     * @return
     */
    public List<ProjectEntity> findProjectList(ProjectQuery projectQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(ProjectEntity.class);
        QueryCondition queryCondition = queryBuilders
                .like("projectName", projectQuery.getProjectName())
                .eq("projectSetId", projectQuery.getProjectSetId())
                .eq("master", projectQuery.getMaster())
                .eq("creator", projectQuery.getCreator())
                .eq("id", projectQuery.getProjectId())
                .eq("projectTypeId", projectQuery.getProjectTypeId())
                .eq("projectState", projectQuery.getProjectState())
                .eq("projectLimits",projectQuery.getProjectLimits())
                .in("id",projectQuery.getProjectIds())
                .orders(projectQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, ProjectEntity.class);
    }

    /**
     * 自动生成key
     */
    public String creatProjectKey(){
        int length = new Random().nextInt(7) + 2; // 生成长度在 2 到 8 之间的随机数
        StringBuilder result = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // 大写字母集合

        for (int i = 0; i < length; i++) {
            int randomIndex = new Random().nextInt(characters.length());
            result.append(characters.charAt(randomIndex));
        }

        return result.toString();
    }


    /**
     * 验证项目key是否存在
     * @param key
     * @return
     */
    public String projectKeyIsOnly(String key){
        String sql = "select * from pmc_project p";
        sql = sql.concat(" where p.project_key = ? ");
        List<ProjectEntity> ProjectEntityList = this.jpaTemplate.getJdbcTemplate().query(sql,new String[]{key}, new BeanPropertyRowMapper(ProjectEntity.class));

        if(!ProjectEntityList.isEmpty()){
            String projectName = ProjectEntityList.get(0).getProjectName();
            return projectName;
        }else {
            return null;
        }
    }

    /**
     * 验证项目key是否存在
     * @param name
     * @return
     */
    public Boolean projectNameIsOnly(String name){
        List<ProjectEntity> ProjectEntityList = null;
        String sql = "select * from pmc_project p";
        sql = sql.concat(" where p.project_name = ? ");
        ProjectEntityList = this.jpaTemplate.getJdbcTemplate().query(sql,new String[]{name}, new BeanPropertyRowMapper(ProjectEntity.class));
        if(ObjectUtils.isEmpty(ProjectEntityList)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 查询我负责的项目列表
     * @param masterId
     * @return
     */
    public List<ProjectEntity> findMaterProjectList(String masterId) {
        List<ProjectEntity> ProjectEntityList = null;
        String sql = "select * from pmc_project p";
        sql = sql.concat(" where p.master = ? ");
        ProjectEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{masterId}, new BeanPropertyRowMapper(ProjectEntity.class));
        return ProjectEntityList;
    }

    /**
     * 查询我参与的项目列表
     * @param masterId
     * @return
     */
    public List<ProjectEntity> findJoinProjectList(String masterId) {
        List<ProjectEntity> ProjectEntityList = null;
        String sql = "select * from orc_dm_user d,pmc_project p";
        sql = sql.concat(" where d.domain_id = p.id and d.user_id = ? ");
        ProjectEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{masterId}, new BeanPropertyRowMapper(ProjectEntity.class));
        return ProjectEntityList;
    }

    /**
     * 根据查询对象按分页查询项目列表
     * @return
     */
    public List<ProjectEntity> findProgressProjectList() {
        List<ProjectEntity> ProjectEntityList = null;
        String sql = "select * from pmc_project p";
        sql = sql.concat(" where p.project_state = 2 ");
        ProjectEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ProjectEntity.class));
        return ProjectEntityList;
    }

    /**
     * 查找我能查看的公开项目和有权限的私有项目
     * @param projectQuery
     * @return
     */
    public Pagination<ProjectEntity> findProjectPage(ProjectQuery projectQuery){
        QueryBuilders queryBuilders = QueryBuilders.createQuery(ProjectEntity.class, "rs");
        OrQueryCondition orQueryBuildCondition = OrQueryBuilders.instance()
                .eq("projectLimits",0)
                .in("id",projectQuery.getProjectIds())
                .get();
        QueryCondition queryCondition = queryBuilders.or(orQueryBuildCondition)
                .like("projectName", projectQuery.getProjectName())
                .eq("projectSetId", projectQuery.getProjectSetId())
                .eq("master", projectQuery.getMaster())
                .orders(projectQuery.getOrderParams())
                .pagination(projectQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ProjectEntity.class);
    }

    /**
     * 查找我最近查看的项目
     * @param projectQuery
     * @return
     */
    public List<ProjectEntity> findRecentProjectList(ProjectQuery projectQuery){
        QueryCondition queryBuilders =  QueryBuilders.createQuery(ProjectEntity.class, "rs")
                .leftJoin(RecentEntity.class,"rc","rc.modelId=rs.id")
                .eq("rc.model", "project")
                .like("rs.projectName", projectQuery.getProjectName())
                .eq("rs.projectSetId", projectQuery.getProjectSetId())
                .eq("rc.masterId", projectQuery.getRecentMasterId())
                .orders(projectQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryBuilders, ProjectEntity.class);
    }

    /**
     * 查找我收藏的项目
     * @param projectQuery
     * @return
     */
    public List<ProjectEntity> findFocusProjectList(ProjectQuery projectQuery){
        QueryCondition queryBuilders =  QueryBuilders.createQuery(ProjectEntity.class, "pj")
                .leftJoin(ProjectFocusEntity.class,"pf","pf.projectId=pj.id")
                .like("pj.projectName", projectQuery.getProjectName())
                .eq("pj.projectSetId", projectQuery.getProjectSetId())
                .eq("pf.masterId", projectQuery.getRecentMasterId())
                .orders(projectQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryBuilders, ProjectEntity.class);
    }

    public List<ProjectEntity> findProjectListByKeyWords(String keyWord){
        List<Object> queryList = new ArrayList<>();
        if (!keyWord.equals("")) {
            queryList.add("%" + keyWord + "%");
        }
        String projectSql = "select * from pmc_project where project_name like  ? ";
        List<ProjectEntity> projectList = this.jpaTemplate.getJdbcTemplate().query(projectSql, queryList.toArray(), new BeanPropertyRowMapper(ProjectEntity.class));

        return projectList;
    }
}