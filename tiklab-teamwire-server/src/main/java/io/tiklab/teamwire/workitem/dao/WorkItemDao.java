package io.tiklab.teamwire.workitem.dao;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderTypeEnum;
import io.tiklab.core.page.Page;
import io.tiklab.teamwire.project.epic.entity.EpicWorkItemEntity;
import io.tiklab.teamwire.project.plan.entity.PlanWorkItemEntity;
import io.tiklab.teamwire.workitem.entity.WorkItemEntity;
import io.tiklab.teamwire.workitem.model.WorkItemQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jdbc.JdbcTemplate;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.OrQueryCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.OrQueryBuilders;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 事项数据访问
 */
@Repository
public class WorkItemDao{

    private static Logger logger = LoggerFactory.getLogger(WorkItemDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项
     * @param workItemEntity
     * @return
     */
    public String createWorkItem(WorkItemEntity workItemEntity) {
        return jpaTemplate.save(workItemEntity,String.class);
    }

    /**
     * 更新事项
     * @param workItemEntity
     */
    public void updateWorkItem(WorkItemEntity workItemEntity){
        jpaTemplate.update(workItemEntity);
    }


    /**
     * 删除事项
     * @param id
     */
    public void deleteWorkItem(String id){
        jpaTemplate.delete(WorkItemEntity.class,id);
    }

    /**
     * 根据id查找事项
     * @param id
     * @return
     */
    public WorkItemEntity findWorkItem(String id){
        return jpaTemplate.findOne(WorkItemEntity.class,id);
    }

    /**
    * 查找所有事项
    * @return
    */
    public List<WorkItemEntity> findAllWorkItem() {
        return jpaTemplate.findAll(WorkItemEntity.class);
    }

    /**
     * 根据ids 查找事项列表
     * @param idList
     * @return
     */
    public List<WorkItemEntity> findWorkItemList(List<String> idList) {
        return jpaTemplate.findList(WorkItemEntity.class,idList);
    }

    /**
     * 根据条件查找事项列表
     * @param workItemQuery
     * @return
     */
    public List<WorkItemEntity> findWorkItemList(WorkItemQuery workItemQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(WorkItemEntity.class,"wi")
                .like("wi.title",workItemQuery.getTitle())
                .eq("wi.projectId",workItemQuery.getProjectId())
                .in("wi.parentId", workItemQuery.getParentIdIn())
                .eq("wi.versionId", workItemQuery.getVersionId())
                .eq("wi.workTypeSysId", workItemQuery.getWorkTypeId())
                .eq("wi.workStatusId", workItemQuery.getWorkStatusId())
                .eq("wi.workStatusCode", workItemQuery.getWorkStatusCode())
                .eq("wi.workTypeCode", workItemQuery.getWorkTypeCode())
                .eq("wi.sprintId", workItemQuery.getSprintId())
                .notIn("wi.id", workItemQuery.getIdNotIn())
                .orders(workItemQuery.getOrderParams());

        //是否属于项目版本
        if(workItemQuery.getVersionIdIsNull() != null) {
            if(workItemQuery.getVersionIdIsNull() == true){
                queryBuilders = queryBuilders.isNull("wi.versionId");
            }else {
                queryBuilders = queryBuilders.isNotNull("wi.versionId");
            }
        }

        if(workItemQuery.getSprintIdIsNull() != null) {
            if(workItemQuery.getSprintIdIsNull() == true){
                queryBuilders = queryBuilders.isNull("wi.sprintId");
            }else {
                queryBuilders = queryBuilders.isNotNull("wi.sprintId");
            }
        }

        if(workItemQuery.getWorkStatusIds() != null){
            List<String> workStatusIds = workItemQuery.getWorkStatusIds();
            String[] arr = workStatusIds.toArray(new String[workStatusIds.size()]);
            queryBuilders.in("wi.workStatusId", arr);
        }

        if(workItemQuery.getProjectIds() != null){
            List<String> projectIds = workItemQuery.getProjectIds();
            String[] arr = projectIds.toArray(new String[projectIds.size()]);
            queryBuilders.in("wi.projectId", arr);
        }

        if(workItemQuery.getWorkTypeIds() != null){
            List<String> workTypeIdsIds = workItemQuery.getWorkTypeIds();
            String[] arr = workTypeIdsIds.toArray(new String[workTypeIdsIds.size()]);
            queryBuilders.in("wi.workTypeId", arr);
        }

        if(workItemQuery.getAssignerIds() != null){
            List<String> assignerIds = workItemQuery.getAssignerIds();
            String[] arr = assignerIds.toArray(new String[assignerIds.size()]);
            queryBuilders.in("wi.assignerId", arr);
        }

        if(workItemQuery.getWorkTypeNoInIds() != null){
            List<String> workStatusNoInIds = workItemQuery.getWorkTypeNoInIds();
            String[] arr = workStatusNoInIds.toArray(new String[workStatusNoInIds.size()]);
            queryBuilders.notIn("wi.workTypeId", arr);
        }

        if(workItemQuery.getParentIdIsNull() != null){
            if(workItemQuery.getParentIdIsNull() == true){
                queryBuilders = queryBuilders.isNull("wi.parentId");
            }else {
                queryBuilders = queryBuilders.isNotNull("wi.parentId");
            }
        }

        if(workItemQuery.getRootIds() != null){
            List<String> rootIds = workItemQuery.getRootIds();
            String[] arr = rootIds.toArray(new String[rootIds.size()]);
            queryBuilders.in("wi.rootId", arr);
        }

        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findList(queryCondition, WorkItemEntity.class);
    }

    public Integer findWorkItemListCount(WorkItemQuery workItemQuery) {
//        QueryBuilders queryBuilders = QueryBuilders.createQuery(WorkItemEntity.class,"wi")
//                .eq("wi.projectId",workItemQuery.getProjectId())
//                .orders(workItemQuery.getOrderParams());
        String sql = "select count(1) as count from pmc_work_item t where t.project_id = '" + workItemQuery.getProjectId() + "'";
        Integer integer = this.jpaTemplate.getJdbcTemplate().queryForObject(sql, new String[]{}, Integer.class);
        return integer;
    }

    /**
     * 根据条件按照分页查找事项列表
     * @param workItemQuery
     * @return
     */
    public Pagination<WorkItemEntity> findWorkItemPage(WorkItemQuery workItemQuery){
        QueryBuilders queryBuilders = QueryBuilders.createQuery(WorkItemEntity.class,"wi")
                .eq("wi.id", workItemQuery.getId())
                .eq("wi.parentId", workItemQuery.getParentId())
                .eq("wi.versionId", workItemQuery.getVersionId())
                .eq("wi.workTypeId", workItemQuery.getWorkTypeId())
                .eq("wi.workTypeSysId", workItemQuery.getWorkTypeSysId())
                .eq("wi.projectId", workItemQuery.getProjectId())
                .eq("wi.assignerId", workItemQuery.getAssignerId())
                .notIn("wi.id", workItemQuery.getIdNotIn())
                .orders(workItemQuery.getOrderParams())
                .pagination(workItemQuery.getPageParam());

        if(workItemQuery.getWorkTypeIds() != null){
            List<String> workTypeIdsIds = workItemQuery.getWorkTypeIds();
            String[] arr = workTypeIdsIds.toArray(new String[workTypeIdsIds.size()]);
            queryBuilders.in("wi.workTypeSysId", arr);
        }

        if(workItemQuery.getWorkStatusIds() != null){
            List<String> workStatusIds = workItemQuery.getWorkStatusIds();
            String[] arr = workStatusIds.toArray(new String[workStatusIds.size()]);
            queryBuilders.in("wi.workStatusNodeId", arr);
        }

        if(workItemQuery.getLikeId() != null && workItemQuery.getTitle() != null){
            OrQueryCondition orQueryCondition = OrQueryBuilders.instance()
                    .like("id", workItemQuery.getLikeId())
                    .like("title", workItemQuery.getTitle())
                    .get();
            queryBuilders = queryBuilders.or(orQueryCondition);
        }
        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findPage(queryCondition, WorkItemEntity.class);
    }

    /**
     * 查找项目下id最大的事项id 数字
     */
    public Integer findMaxIdWorkItem(String projectId){
        String sql = "select max(order_num) as maxId from pmc_work_item wi where wi.project_id = ?";
        Integer maxOrderNum = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{projectId}, Integer.class);
        return maxOrderNum;
    }
    /**
     * 史诗下可添加的子事项
     * @param workItemQuery
     * @return
     */
    public Pagination<WorkItemEntity> findEpicSelectWorkItemList(WorkItemQuery workItemQuery) {
        //史诗的类型id是 3
        String sql = "select * from pmc_work_item wi";
        sql = sql.concat(" where wi.project_id = ? and wi.work_type_code != 'epic' and ( wi.parent_id != ? or wi.parent_id is null )" );

        String parentId = workItemQuery.getParentId();
        String projectId = workItemQuery.getProjectId();

        List<Object> objects = new ArrayList<>();
        objects.add(projectId);
        objects.add(parentId);

        //模糊匹配
        if(!StringUtils.isEmpty(workItemQuery.getTitle())){
            objects.add(workItemQuery.getTitle());
            sql = sql.concat(" and wi.title like '%' ? '%' ");
        }
        int size = objects.size();
        Object[] objects1 = new Object[size];
        Object[] objects2 = objects.toArray(objects1);

        Pagination<WorkItemEntity> workItemEntityPagination =
                this.jpaTemplate.getJdbcTemplate().findPage(sql,objects2,workItemQuery.getPageParam(),new BeanPropertyRowMapper(WorkItemEntity.class));
        return workItemEntityPagination;
    }

    /**
     *  缺陷，任务，需求下可添加的子事项
     * @param workItemQuery
     * @return
     */
    public Pagination<WorkItemEntity> findSelectWorkItemList(WorkItemQuery workItemQuery) {
        String treePath = workItemQuery.getTreePath();
        ArrayList<String> objects = new ArrayList<>();
        objects.add(workItemQuery.getId());
        int sizeId = objects.size();
        String[] stringIds = new String[sizeId];
        String[] arrayId = objects.toArray(stringIds);


        if(treePath != null){
            List<String> treePaths = Arrays.asList(treePath.split(";"));
            List<String> treePathList = new ArrayList<>();
            treePathList.addAll(treePaths);

            for(String path:treePaths){
                objects.add(path);
            }
        }

        int size = objects.size();
        String[] strings = new String[size];
        String[] array = objects.toArray(strings);

        QueryBuilders queryBuilders = QueryBuilders.createQuery(WorkItemEntity.class)
                .notIn("id",array)
                .eq("workTypeId", workItemQuery.getWorkTypeId())
                .eq("projectId", workItemQuery.getProjectId())
                .orders(workItemQuery.getOrderParams())
                .pagination(workItemQuery.getPageParam());


        OrQueryCondition orQueryCondition = OrQueryBuilders.instance()
                .notIn("parentId", arrayId)
                .isNull("parentId")
                .get();
        queryBuilders = queryBuilders.or(orQueryCondition);
        if(workItemQuery.getLikeId() != null &&  workItemQuery.getTitle() != null){
            OrQueryCondition orQueryBuildCondition1 = OrQueryBuilders.instance()
                    .like("id", workItemQuery.getLikeId())
                    .like("title", workItemQuery.getTitle())
                    .get();
            queryBuilders = queryBuilders.or(orQueryBuildCondition1);
        }


        if(workItemQuery.getWorkPriorityIds() != null){
            List<String> workPriorityIdsIds = workItemQuery.getWorkPriorityIds();
            String[] arr = workPriorityIdsIds.toArray(new String[workPriorityIdsIds.size()]);
            queryBuilders.in("workPriorityId", arr);
        }

        if(workItemQuery.getWorkStatusIds() != null){
            List<String> workStatusIds = workItemQuery.getWorkStatusIds();
            String[] arr = workStatusIds.toArray(new String[workStatusIds.size()]);
            queryBuilders.in("workStatusNodeId", arr);
        }

        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findPage(queryCondition, WorkItemEntity.class);
    }


    /**
     * 根据搜索条件拼接查询sql
     * @param workItemQuery
     * @return
     */
    public Map<String, Object> WorkItemSearchSql(WorkItemQuery workItemQuery) {
        String sql = new String();
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, String> sqlMap = new HashMap<String, String>();
        Object[] objects = {};

        if(workItemQuery.getWorkTypeId() != null && workItemQuery.getWorkTypeId().length()>0){
            sql = sql.concat(" p.work_type_sys_id = '" + workItemQuery.getWorkTypeId() + "'");
            paramMap.put("workTypeId", workItemQuery.getWorkTypeId());
        }

        if(workItemQuery.getId() != null && workItemQuery.getId().length()>0){
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.id = '" + workItemQuery.getId() + "'");
            }else {
                sql = sql.concat(" and p.id = '" + workItemQuery.getId() + "'");
            }
            paramMap.put("id", workItemQuery.getId());
        }

        if(workItemQuery.getWorkTypeIds() != null && workItemQuery.getWorkTypeIds().size() > 0){
            List<String> workTypeIds = workItemQuery.getWorkTypeIds();
            String s = new String("(");
            for(String workTypeId:workTypeIds){
                s = s.concat("'" + workTypeId + "',");
            }
            s= s.substring(0, s.length() - 1);
            s=s.concat(")");

            if(paramMap.isEmpty()){
                sql = sql.concat("p.work_type_sys_id in " + s);
            }else {
                sql = sql.concat("and p.work_type_sys_id in " + s);
            }
            paramMap.put("workTypeIds", workItemQuery.getWorkTypeIds());
        }

        if(workItemQuery.getProjectId() != null && workItemQuery.getProjectId().length()>0){
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.project_id = '" + workItemQuery.getProjectId() + "'");
            }else {
                sql = sql.concat(" and p.project_id = '" + workItemQuery.getProjectId() + "'");
            }
            paramMap.put("projectId", workItemQuery.getProjectId());
        }

        if(workItemQuery.getWorkTypeCode() != null && workItemQuery.getWorkTypeCode().length()>0){
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.work_type_code = '" + workItemQuery.getWorkTypeCode() + "'");
            }else {
                sql = sql.concat(" and p.work_type_code = '" + workItemQuery.getWorkTypeCode() + "'");
            }
            paramMap.put("workTypeCode", workItemQuery.getWorkTypeCode());
        }

        if(workItemQuery.getProjectIds() != null && workItemQuery.getProjectIds().size()>0){
            List<String> projectIds = workItemQuery.getProjectIds();
            String s = new String();
            s =  "(";
            for(String projectId:projectIds){
                s = s.concat("'" + projectId + "',");
            }
            s= s.substring(0, s.length() - 1);
            s = s.concat(")");

            if(paramMap.isEmpty()){
                sql = sql.concat(" p.project_id in " + s);
            }else {
                sql = sql.concat(" and p.project_id in " + s);
            }
            paramMap.put("projectIds", workItemQuery.getProjectIds());
        }

        if(workItemQuery.getSprintId() != null && workItemQuery.getSprintId().length()>0){
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.sprint_id = '" + workItemQuery.getSprintId() + "'");
            }else {
                sql = sql.concat(" and p.sprint_id = '" + workItemQuery.getSprintId() + "'");
            }
            paramMap.put("sprintId", workItemQuery.getSprintIds());
        }

        if(workItemQuery.getSprintIds() != null && workItemQuery.getSprintIds().size()>0){
            List<String> sprintIds = workItemQuery.getSprintIds();
            String s = new String();
            s =  "(";
            for(String sprintId:sprintIds){
                s = s.concat("'" + sprintId + "',");
            }
            s= s.substring(0, s.length() - 1);
            s = s.concat(")");

            if(paramMap.isEmpty()){
                sql = sql.concat(" p.sprint_id in " + s);
            }else {
                sql = sql.concat(" and p.sprint_id in " + s);
            }
            paramMap.put("sprintIds", workItemQuery.getSprintIds());
        }

        if(workItemQuery.getWorkStatusId() != null && workItemQuery.getWorkStatusId().length()>0){

            if(paramMap.isEmpty()){
                sql = sql.concat(" p.work_status_node_id = '" + workItemQuery.getWorkStatusId() + "'");
            }else {
                sql = sql.concat(" and p.work_status_node_id = '"+ workItemQuery.getWorkStatusId() + "'");
            }
            paramMap.put("workStatusId", workItemQuery.getWorkStatusId());
        }

        if(workItemQuery.getWorkStatusIds() != null && workItemQuery.getWorkStatusIds().size()>0){
            List<String> workStatusIds = workItemQuery.getWorkStatusIds();
            String s = new String("(");
            for(String workStatusId:workStatusIds){
                s = s.concat("'" + workStatusId + "',");
            }
            s= s.substring(0, s.length() - 1);
            s= s.concat(")");

            if(paramMap.isEmpty()){
                sql = sql.concat(" p.work_status_node_id in " + s);
            }else {
                sql = sql.concat(" and p.work_status_node_id in " + s);
            }
            paramMap.put("workStatusIds", workItemQuery.getWorkStatusIds());
        }

        if(workItemQuery.getTitle() != null && workItemQuery.getTitle().length()>0 ){
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.title like '%" + workItemQuery.getTitle() + "%'");
            }else {
                sql = sql.concat(" and p.title like '%" +  workItemQuery.getTitle() + "%'");
            }
            paramMap.put("title", workItemQuery.getTitle());
        }

        if(workItemQuery.getKeyWord() != null && workItemQuery.getKeyWord().length()>0 ){
            if(paramMap.isEmpty()){
                sql = sql.concat(" (p.title like '%" + workItemQuery.getKeyWord() + "%' or p.id like '%" + workItemQuery.getKeyWord() + "%')");
            }else {
                sql = sql.concat(" and (p.title like '%" +  workItemQuery.getKeyWord() + "%' or p.id like '%" + workItemQuery.getKeyWord() + "%')");
            }
            paramMap.put("keyWord", workItemQuery.getTitle());
        }
        if(workItemQuery.getRootId() != null && workItemQuery.getRootId().length()>0){
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.root_id = '" + workItemQuery.getRootId() + "'");
            }else {
                sql = sql.concat(" and p.root_id = '" + workItemQuery.getRootId() + "'");
            }
            paramMap.put("rootId", workItemQuery.getRootId());
        }

        if(workItemQuery.getBuilderId() != null && workItemQuery.getBuilderId().length()>0){
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.builder_id = '" + workItemQuery.getUserId() + "'");
            }else {
                sql = sql.concat(" and p.builder_id = '" + workItemQuery.getUserId() + "'");
            }
            paramMap.put("builderId", workItemQuery.getUserId());
        }


        if(workItemQuery.getBuilderIds() != null && workItemQuery.getBuilderIds().size()>0){
            List<String> builderIds = workItemQuery.getBuilderIds();
            String s = new String("(");
            for(String builderId:builderIds){
                s = s.concat("'" + builderId + "',");
            }
            s= s.substring(0, s.length() - 1);
            s= s.concat(")");

            if(paramMap.isEmpty()){
                sql = sql.concat(" p.builder_id in " + s);
            }else {
                sql = sql.concat(" and p.builder_id in " + s);
            }
            paramMap.put("builderId", workItemQuery.getBuilderIds());
        }

        if(workItemQuery.getAssignerIds() != null && workItemQuery.getAssignerIds().size()>0){
            List<String> assignerIds = workItemQuery.getAssignerIds();
            String s = new String("(");
            for(String assignerId:assignerIds){
                s = s.concat("'" + assignerId + "',");
            }
            s= s.substring(0, s.length() - 1);
            s= s.concat(")");

            if(paramMap.isEmpty()){
                sql = sql.concat(" p.assigner_id in " + s);
            }else {
                sql = sql.concat(" and p.assigner_id in " + s);
            }
            paramMap.put("assignerId", workItemQuery.getAssignerIds());
        }

        if(workItemQuery.getIdNotIn() != null && workItemQuery.getIdNotIn().length > 0){
            String[] idNotIn = workItemQuery.getIdNotIn();
            String s = new String("(");
            for(int i = 0; i< idNotIn.length; i++){
                s = s.concat("'" + idNotIn[i] + "',");
            }
            s= s.substring(0, s.length() - 1);
            s= s.concat(")");

            sql = sql.concat(" and p.id not in " + s);
            paramMap.put("idNotIn", idNotIn);
        }

        if(workItemQuery.getRootIds() != null && workItemQuery.getRootIds().size() > 0){
            List<String> rootIds = workItemQuery.getRootIds();
            String s = new String("(");
            for(String rootId:rootIds){
                s = s.concat("'" + rootId + "',");
            }
            s= s.substring(0, s.length() - 1);
            s= s.concat(")");

            if(paramMap.isEmpty()){
                sql = sql.concat(" p.root_id in " + s);
            }else {
                sql = sql.concat(" and p.root_id in " + s);
            }
            paramMap.put("rootId", workItemQuery.getRootIds());
        }

        if(!ObjectUtils.isEmpty(workItemQuery.getWorkPriorityIds())) {
            List<String> workPriorityIds = workItemQuery.getWorkPriorityIds();
            String s = new String("(");
            for(int i = 0; i< workPriorityIds.size(); i++){
                s = s.concat("'" + workPriorityIds.get(i) + "',");
            }
            s= s.substring(0, s.length() - 1);
            s= s.concat(")");

            sql = sql.concat(" and p.work_priority_id in " + s);
            paramMap.put("workPriorityId", workPriorityIds);
        }

        if(!ObjectUtils.isEmpty(workItemQuery.getModuleIds())){
            List<String> moduleIds = workItemQuery.getModuleIds();
            String s = new String("(");
            for(int i = 0; i< moduleIds.size(); i++){
                s = s.concat("'" + moduleIds.get(i) + "',");
            }
            s= s.substring(0, s.length() - 1);
            s= s.concat(")");

            sql = sql.concat(" and p.module_id in " + s);
            paramMap.put("moduleIds", moduleIds);
        }

        if(!StringUtils.isEmpty(workItemQuery.getUpdateTimeStart())){
            String updateTimeStart = workItemQuery.getUpdateTimeStart();
            updateTimeStart = updateTimeStart.concat(" 00:00:00");

            String updateTimeEnd = workItemQuery.getUpdateTimeEnd();
            updateTimeEnd = updateTimeEnd.concat(" 23:59:59");
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.update_time >= '"+ updateTimeStart + "' and p.update_time <= '" + updateTimeEnd + "'");
            }else {
                sql = sql.concat(" and p.update_time >= '"+ updateTimeStart + "' and p.update_time <= '" + updateTimeEnd + "'");
            }
            paramMap.put("updateTime", updateTimeStart);
        }

        if(!StringUtils.isEmpty(workItemQuery.getBuildTimeStart())){
            String buildTimeStart = workItemQuery.getBuildTimeStart();
            buildTimeStart = buildTimeStart.concat(" 00:00:00");
            String buildTimeEnd = workItemQuery.getBuildTimeEnd();
            buildTimeStart = buildTimeStart.concat(" 23:59:59");
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.build_time >= '"+ buildTimeStart + "' and p.build_time <= '" + buildTimeEnd + "'");
            }else {
                sql = sql.concat(" and p.build_time >= '"+ buildTimeStart + "' and p.build_time <= '" + buildTimeEnd + "'");
            }
            paramMap.put("buildTime", buildTimeStart);
        }

        if(!StringUtils.isEmpty(workItemQuery.getPlanStartDateStart())){
            String planStartDateStart = workItemQuery.getPlanStartDateStart();
            String planStartDateEnd = workItemQuery.getPlanStartDateEnd();
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.plan_begin_time >= '"+ planStartDateStart + "' and p.plan_begin_time <= '" + planStartDateEnd + "'");
            }else {
                sql = sql.concat(" and p.plan_begin_time >= '"+ planStartDateStart + "' and p.plan_begin_time <= '" + planStartDateEnd + "'");
            }
            paramMap.put("planBeginTime", planStartDateStart);
        }

        if(!StringUtils.isEmpty(workItemQuery.getPlanEndDateStart())){
            String planEndDateStart = workItemQuery.getPlanEndDateStart();
            String planEndDateEnd = workItemQuery.getPlanEndDateEnd();
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.plan_end_time >= '"+ planEndDateStart + "' and p.plan_end_time <= '" + planEndDateEnd + "'");
            }else {
                sql = sql.concat(" and p.plan_end_time >= '"+ planEndDateStart + "' and p.plan_end_time <= '" + planEndDateEnd + "'");
            }
            paramMap.put("planEndTime", planEndDateStart);
        }

        if(workItemQuery.getOverdue() != null && workItemQuery.getOverdue()){
            Boolean overdue = workItemQuery.getOverdue();
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentTime = simpleDateFormat.format(date);
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.plan_end_time <= '"+ currentTime + "' and p.work_status_code != 'DONE'");
            }else {
                sql = sql.concat(" and p.plan_end_time <= '"+ currentTime + "' and p.work_status_code != 'DONE'");
            }
            paramMap.put("overdue", overdue);
        }

        sqlMap.put("sql", sql);
        objectObjectHashMap.put("query", objects);
        objectObjectHashMap.put("sql", sql);

        return objectObjectHashMap;
    }


    /**
     * 查找符合条件的根节点事项
     * @param workItemQuery
     * @return
     */
    public Pagination<WorkItemEntity> findTopWorkItemIds(WorkItemQuery workItemQuery){
        String sql = new String();
        sql = "Select * from pmc_work_item p";
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        Object o1 = stringObjectMap.get("sql");
        if(!ObjectUtils.isEmpty(o1)){
            sql = sql.concat(" where " + String.valueOf(o1));
            sql = sql.concat(" and parent_id is null");
        }else {
            sql = sql.concat(" where parent_id is null");
        }
        if(!ObjectUtils.isEmpty(workItemQuery.getOrderParams())){
            sql= sql.concat(" order by ");
        }
        for (Order orderParam : workItemQuery.getOrderParams()) {
            OrderTypeEnum orderType = orderParam.getOrderType();
            String name = orderParam.getName();
            System.out.println(orderType);
            System.out.println(name);
            sql = sql.concat(name + " " + orderType );
        }

        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        Pagination page = jdbcTemplate.findPage(sql, new Object[]{}, workItemQuery.getPageParam(), new BeanPropertyRowMapper(WorkItemEntity.class));

        return page;
    }

    public HashMap<String, Integer> findWorkItemNumByWorkType(WorkItemQuery workItemQuery){
        HashMap<String, Integer> WorkItemCount = new HashMap<>();
        workItemQuery.setWorkTypeId(null);
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        String sql = new String();
        sql = "Select count(1) as total from pmc_work_item p";
        Object o1 = stringObjectMap.get("sql");
        if(!ObjectUtils.isEmpty(o1)){
            sql = sql.concat(" where " + String.valueOf(o1));
            sql = sql.concat(" and parent_id is null");
        }else {
            sql = sql.concat(" where parent_id is null");
        }

        Integer allNum = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
        WorkItemCount.put("all", allNum);

        String sql1 =  sql.concat(" and work_type_code = 'demand'");
        Integer demandNum = jpaTemplate.getJdbcTemplate().queryForObject(sql1, new Object[]{}, Integer.class);
        WorkItemCount.put("demand", demandNum);

        String sql2 =  sql.concat(" and work_type_code = 'task'");
        Integer taskNum = jpaTemplate.getJdbcTemplate().queryForObject(sql2, new Object[]{}, Integer.class);
        WorkItemCount.put("task", taskNum);

        String sql3 =  sql.concat(" and work_type_code = 'defect'");
        Integer defectNum = jpaTemplate.getJdbcTemplate().queryForObject(sql3, new Object[]{}, Integer.class);
        WorkItemCount.put("defect", defectNum);

        return WorkItemCount;
    }

    public HashMap<String, Integer> findWorkItemNumByWorkList(WorkItemQuery workItemQuery){
        HashMap<String, Integer> WorkItemCount = new HashMap<>();
        workItemQuery.setWorkTypeId(null);
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        String sql = new String();
        sql = "Select count(1) as total from pmc_work_item p";
        Object o1 = stringObjectMap.get("sql");

        String sql0 = "";
        if(!ObjectUtils.isEmpty(o1)) {
            sql0 = sql0.concat(sql + " where " + String.valueOf(o1));
        }
        Integer allNum = jpaTemplate.getJdbcTemplate().queryForObject(sql0, new Object[]{}, Integer.class);
        WorkItemCount.put("all", allNum);

        if(!ObjectUtils.isEmpty(o1)){
            sql = sql.concat(" where " + String.valueOf(o1) + "and");
        }else {
            sql = sql.concat(" where ");
        }

        String sql1 =  sql.concat(" work_type_code = 'demand'");
        Integer demandNum = jpaTemplate.getJdbcTemplate().queryForObject(sql1, new Object[]{}, Integer.class);
        WorkItemCount.put("demand", demandNum);

        String sql2 =  sql.concat(" work_type_code = 'task'");
        Integer taskNum = jpaTemplate.getJdbcTemplate().queryForObject(sql2, new Object[]{}, Integer.class);
        WorkItemCount.put("task", taskNum);

        String sql3 =  sql.concat(" work_type_code = 'defect'");
        Integer defectNum = jpaTemplate.getJdbcTemplate().queryForObject(sql3, new Object[]{}, Integer.class);
        WorkItemCount.put("defect", defectNum);

        return WorkItemCount;
    }
    /**
     * 查找非根节点事项的符合条件的事项
     * @param workItemQuery
     * @return
     */
    public List<WorkItemEntity> findTopChildWorkItem(WorkItemQuery workItemQuery){
        String sql = new String();
        sql = "Select * from pmc_work_item p ";
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        Object sql1 = stringObjectMap.get("sql");

        if(!ObjectUtils.isEmpty(sql1)){
            sql = sql.concat(" where " + String.valueOf(sql1));
            sql = sql.concat(" and parent_id is not null");
        }else {
            sql = sql.concat(" where parent_id is not null");
        }

        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<WorkItemEntity> query = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(WorkItemEntity.class));
        return query;
    }


    /**
     * 按照分页查找符合条件的事项
     * @param workItemQuery
     * @return
     */
    public Pagination<WorkItemEntity> findConditionWorkItemPage(WorkItemQuery workItemQuery) {
        String sql = new String();
        sql = "Select * from pmc_work_item p ";
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        Object o1 = stringObjectMap.get("sql");
        if(String.valueOf(o1).length()>0){
            sql = sql.concat("where " + String.valueOf(o1));
        }
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        Pagination WorkItemList = jdbcTemplate.findPage(sql, new Object[]{}, workItemQuery.getPageParam(), new BeanPropertyRowMapper(WorkItemEntity.class));
        return WorkItemList;
    }

    /**
     * 查找符合条件的事项列表
     * @param workItemQuery
     * @return
     */
    public List<WorkItemEntity> findConditionWorkItemList(WorkItemQuery workItemQuery) {
        String sql = new String();
        sql = "Select * from pmc_work_item p ";
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        Object o1 = stringObjectMap.get("sql");
        if(String.valueOf(o1).length()>0){
            sql = sql.concat("where " + String.valueOf(o1));
        }
       List<WorkItemEntity> WorkItemList = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{}, new BeanPropertyRowMapper(WorkItemEntity.class));

        return WorkItemList;
    }


    /**
     * 查找未被史诗管理的事项列表
     * @param workItemQuery
     * @return
     */
    public Pagination<WorkItemEntity> findUnEpicWorkItemPage(WorkItemQuery workItemQuery){
        EpicWorkItemEntity epicWorkItem = new EpicWorkItemEntity();
        QueryCondition queryEqicCondition = QueryBuilders.createQuery(EpicWorkItemEntity.class,"ew")
                .eq("ew.epicId",workItemQuery.getEpicId())
                .get();

        List<EpicWorkItemEntity> list = jpaTemplate.findList(queryEqicCondition, EpicWorkItemEntity.class);
        List<String> collect = list.stream().map(EpicWorkItemEntity::getWorkItemId).collect(Collectors.toList());
        String[] notIds = new String[collect.size()];
        notIds = collect.toArray(notIds);


        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemEntity.class,"wi")
                .eq("wi.projectId",workItemQuery.getProjectId())
                .notIn("wi.id", notIds)
                .get();
        Pagination WorkItemList = jpaTemplate.findPage(queryCondition, WorkItemEntity.class);

        return  WorkItemList;
    }

    /**
     * 查找未被某个版本管理的事项
     * @param workItemQuery
     * @return
     */
    public Pagination<WorkItemEntity> findUnPlanWorkItemPage(WorkItemQuery workItemQuery){
        PlanWorkItemEntity planWorkItem = new PlanWorkItemEntity();
        QueryCondition queryPlanCondition = QueryBuilders.createQuery(PlanWorkItemEntity.class,"pw")
                .eq("pw.planId",workItemQuery.getPlanId())
                .get();
        List<PlanWorkItemEntity> list = jpaTemplate.findList(queryPlanCondition, PlanWorkItemEntity.class);
        List<String> collect = list.stream().map(PlanWorkItemEntity::getWorkItemId).collect(Collectors.toList());
        String[] notIds = new String[collect.size()];
        notIds = collect.toArray(notIds);

        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemEntity.class,"wi")
                .eq("wi.projectId",workItemQuery.getProjectId())
                .notIn("wi.id", notIds)
                .pagination(workItemQuery.getPageParam())
                .get();

        Pagination WorkItemList = jpaTemplate.findPage(queryCondition, WorkItemEntity.class);
        return  WorkItemList;
    }

    /**
     * 查找某个版本下管理的事项
     * @param workItemQuery
     * @return
     */
    public List<WorkItemEntity> findPlanWorkItemPage(WorkItemQuery workItemQuery){
        QueryCondition queryPlanCondition = QueryBuilders.createQuery(PlanWorkItemEntity.class,"pw")
                .eq("pw.planId",workItemQuery.getPlanId())
                .get();
        List<PlanWorkItemEntity> planWorkItemEntityList = jpaTemplate.findList(queryPlanCondition, PlanWorkItemEntity.class);
        List<String> collect = planWorkItemEntityList.stream().map(PlanWorkItemEntity::getWorkItemId).collect(Collectors.toList());
        String[] ids = new String[collect.size()];
        ids = collect.toArray(ids);

        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemEntity.class,"wi")
                .in("wi.id",ids)
                .get();
        List<WorkItemEntity> workItemEntityList = jpaTemplate.findList(queryCondition, WorkItemEntity.class);
        return workItemEntityList;
    }

    public Pagination<WorkItemEntity> findWorkItemByKeyWorks(WorkItemQuery workItemQuery){
        String word = "%" + workItemQuery.getTitle() + "%";
        String workSql = "select * from pmc_work_item where title like ?" ;
        Pagination WorkItemPageList = this.jpaTemplate.getJdbcTemplate().findPage(workSql, new String[]{word}, workItemQuery.getPageParam(), new BeanPropertyRowMapper(WorkItemEntity.class));
        return WorkItemPageList;
    }


}