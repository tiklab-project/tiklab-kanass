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
import net.sourceforge.pinyin4j.PinyinHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    public String creatProjectKey(String projectName){
        // 去掉名字中的数字
        String projectName1 = projectName.replaceAll("\\d", "");
        String pinYinHeadChar = getPinYinHeadChar(projectName1);
        int length = pinYinHeadChar.length();
        if(length > 8){
            pinYinHeadChar = pinYinHeadChar.substring(0, 8);
        }
        boolean repeatKey = isRepeatKey(pinYinHeadChar);
        if(!repeatKey){
            return pinYinHeadChar;
        }else {
            return creatRandomProjectKey();
        }

    }

    /**
     * 判断生成的key 是否重复
     */
    public boolean isRepeatKey(String key) {
        String sql = "select project_key from pmc_project";
        List<String> keys = this.jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        return keys.contains(key);
    }
    /**
     * 自动生成key
     */
    public String creatRandomProjectKey(){
        int length = new Random().nextInt(7) + 2; // 生成长度在 2 到 8 之间的随机数
        StringBuilder result = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // 大写字母集合

        for (int i = 0; i < length; i++) {
            int randomIndex = new Random().nextInt(characters.length());
            result.append(characters.charAt(randomIndex));
        }
        String key = result.toString();


       if(!isRepeatKey(key)){
            return key;
        }else{
            String newKey = creatRandomProjectKey();
            return newKey;
        }
    }

    /**
     * 提取每个汉字的首字母(大写)
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            }else {
                convert += word;
            }
        }

        return convert.toUpperCase();
    }

    /**
     * 判断是否是中文
     * @param str
     * @return
     */
    public static boolean isChineseByReg(String str) {
        String reg = "[\\u4e00-\\u9fa5]";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /*
     * 判断字符串是否为空
     */

//    public static boolean isNull(Object strData) {
//        if (strData == null || String.valueOf(strData).trim().equals("")) {
//            return true;
//        }
//        return false;
//    }

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
     * 查找我最近查看的项目
     * @param projectQuery
     * @return
     */
    public List<ProjectEntity> findRecentProjectListOrderByDate(ProjectQuery projectQuery){
        String master = projectQuery.getRecentMasterId();
        String sql = "select rs.id as id, rs.project_name as project_name, rs.project_type_id as project_type_id, rs.icon_url as icon_url from pmc_project rs left join pmc_recent rc on rs.id = rc.model_id where rc.model='project'\n" +
                "and rc.master_id='" + master + "' order by rc.recent_time desc";

        List<ProjectEntity> projectEntityList = jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ProjectEntity.class));

        return projectEntityList;
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

    public void deleteProjectAndRelation(String projectId){
        // 里程碑
        String sql = "DELETE FROM pmc_milestone where project_id = '" + projectId + "'";
        Integer update =  jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除里程碑成功");
        }else {
            logger.info("删除里程碑失败");
        }

        // 知识库
        sql = "DELETE FROM pmc_project_wiki_repository where project_id = '" + projectId + "'";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除知识库成功");
        }else {
            logger.info("删除知识库失败");
        }

        // 测试用例
        sql = "DELETE FROM pmc_project_test_repository where project_id = '" + projectId + "'";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除测试用例成功");
        }else {
            logger.info("删除测试用例失败");
        }

        // 事项类型
        sql = "DELETE FROM pmc_work_type_dm where project_id = '" + projectId + "'";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除事项类型成功");
        }else {
            logger.info("删除事项类型失败");
        }

        // 模块
        sql = "DELETE FROM pmc_module where project_id = '" + projectId + "'";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除模块成功");
        }else {
            logger.info("删除模块失败");
        }

        // 最近查看
        sql = "DELETE FROM pmc_recent where project_id = '" + projectId + "'";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除最近查看成功");
        }else {
            logger.info("删除最近查看失败");
        }

        // 项目燃尽图
        sql = "DELETE FROM pmc_project_burndowm where project_id = '" + projectId + "'";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除项目燃尽图成功");
        }else {
            logger.info("删除项目燃尽图失败");
        }

        // 关注的项目
        sql = "DELETE FROM pmc_project_focus where project_id = '" + projectId + "'";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除关注的项目成功");
        }else {
            logger.info("删除关注的项目失败");
        }
    }

    public void deleteProjectWorkItem(String projectId){
        String sql = "SELECT id FROM pmc_work_item where project_id = '" + projectId + "'";
        List<String> workItemIdList = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);

        if(workItemIdList.size() > 0){
            String workItemIds = workItemIdList.stream().map(id -> "'" + id + "'").collect(Collectors.joining(", "));
            // 删除关联事项
            sql = "DELETE FROM pmc_work_relate where work_item_id in (" + workItemIds + ")";
            int update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除事项的关联事项成功");
            }else {
                logger.info("删除事项的关联事项失败");
            }

            // 删除日志
            sql = "DELETE FROM pmc_work_log where work_item_id in (" + workItemIds + ")";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除事项的工时成功");
            }else {
                logger.info("删除事项的工时失败");
            }

            // 删除文档
            sql = "DELETE FROM pmc_work_item_document where work_item_id in (" + workItemIds + ")";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除事项的文档成功");
            }else {
                logger.info("删除事项的文档失败");
            }

            // 删除动态

            // 删除评论
            sql = "DELETE FROM pmc_work_comment where work_item_id in (" + workItemIds + ")";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除事项的评论成功");
            }else {
                logger.info("删除事项的评论失败");
            }

            // 删除测试用例
            sql = "DELETE FROM pmc_work_test_case where work_item_id in (" + workItemIds + ")";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除事项的测试用例成功");
            }else {
                logger.info("删除事项的评论失败");
            }

            // 删除附件
            sql = "DELETE FROM pmc_work_attach where work_item_id in (" + workItemIds + ")";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除事项的附件成功");
            }else {
                logger.info("删除事项的附件失败");
            }

            // 删除事项
            sql = "DELETE FROM pmc_work_item where project_id = '" + projectId + "'";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除事项成功");
            }else {
                logger.info("删除事项失败");
            }
        }else {
            return;
        }



    }

    public void deleteSprint(String projectId){
        String sql = "SELECT id FROM pmc_sprint where project_id = '" + projectId + "'";
        List<String> sprintIdList = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        if(sprintIdList.size() > 0){
            String sprintIds = sprintIdList.stream().map(id -> "'" + id + "'").collect(Collectors.joining(", "));
            // 删除关注的迭代
            sql = "DELETE FROM pmc_sprint_focus where sprint_id in (" + sprintIds + ")";
            int update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除关注的迭代成功");
            }else {
                logger.info("删除关注的迭代失败");
            }

            // 删除迭代燃尽图数据
            sql = "DELETE FROM pmc_sprint_burndowm where sprint_id in (" + sprintIds + ")";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除迭代燃尽图数据成功");
            }else {
                logger.info("删除迭代燃尽图数据失败");
            }

            // 删除迭代
            sql = "DELETE FROM pmc_sprint where project_id = '" + projectId + "'";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除迭代成功");
            }else {
                logger.info("删除迭代失败");
            }
        }else {
            return;
        }


    }

    public void deleteVersion(String projectId){
        String sql = "SELECT id FROM pmc_version where project_id = '" + projectId + "'";
        List<String> versionIdList = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        if(versionIdList.size() > 0){
            String versionIds = versionIdList.stream().map(id -> "'" + id + "'").collect(Collectors.joining(", "));

            // 删除关注的版本
            sql = "DELETE FROM pmc_version_focus where version_id in (" + versionIds + ")";
            int update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除关注的版本成功");
            }else {
                logger.info("删除关注的版本失败");
            }

            // 删除版本燃尽图数据
            sql = "DELETE FROM pmc_version_burndowm where version_id in (" + versionIds + ")";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除版本燃尽图数据成功");
            }else {
                logger.info("删除版本燃尽图数据失败");
            }

            // 删除版本
            sql = "DELETE FROM pmc_version where project_id = '" + projectId + "'";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除版本成功");
            }else {
                logger.info("删除版本失败");
            }
        }else {
            return;
        }


    }

    public void deleteStage(String projectId){
        String sql = "SELECT id FROM pmc_stage where project_id = '" + projectId + "'";
        List<String> stageIdList = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        if(stageIdList.size() > 0){
            String stageIds = stageIdList.stream().map(id -> "'" + id + "'").collect(Collectors.joining(", "));

            // 删除阶段的事项
            sql = "DELETE FROM pmc_stage_work_item where stage_id in (" + stageIds + ")";
            int update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除关注的版本成功");
            }else {
                logger.info("删除关注的版本失败");
            }

            // 删除阶段
            sql = "DELETE FROM pmc_stage where project_id = '" + projectId + "'";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除版本成功");
            }else {
                logger.info("删除版本失败");
            }
        }else {
            return;
        }


    }
}