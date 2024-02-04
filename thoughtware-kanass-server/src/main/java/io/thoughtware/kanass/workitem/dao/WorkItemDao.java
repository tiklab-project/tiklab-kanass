package io.thoughtware.kanass.workitem.dao;

import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderTypeEnum;
import io.thoughtware.kanass.project.epic.entity.EpicWorkItemEntity;
import io.thoughtware.kanass.project.plan.entity.PlanWorkItemEntity;
import io.thoughtware.kanass.workitem.entity.WorkItemEntity;
import io.thoughtware.kanass.workitem.model.WorkItemQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jdbc.JdbcTemplate;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.OrQueryCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.OrQueryBuilders;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;

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

    public String findWorkItemAndChildren(String workItemId) {
        String sql = "SELECT id FROM pmc_work_item where tree_path like '%" + workItemId + "%'";
        List<String> workItemIdList = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        workItemIdList.add(workItemId);
        String workItemIds = workItemIdList.stream().map(id -> "'" + id + "'").collect(Collectors.joining(", "));

        return workItemIds;
    }

    /**
     * 删除事项
     * @param workItemId
     */
    public void deleteWorkItem(String workItemId){
        String workItemIds = findWorkItemAndChildren(workItemId);

        // 删除关联事项
        String sql = "DELETE FROM pmc_work_relate where work_item_id in (" + workItemIds + ")";
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

        // 删除下级
        sql = "DELETE FROM pmc_work_item where id in (" + workItemIds + ")";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除事项的下级事项");
        }else {
            logger.info("删除事项的下级事项");
        }

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
                .eq("wi.parentId", workItemQuery.getParentId())
                .eq("wi.versionId", workItemQuery.getVersionId())
                .eq("wi.workTypeId", workItemQuery.getWorkTypeId())
                .eq("wi.workTypeSysId", workItemQuery.getWorkTypeSysId())
                .eq("wi.workStatusId", workItemQuery.getWorkStatusId())
                .eq("wi.workStatusCode", workItemQuery.getWorkStatusCode())
                .eq("wi.workTypeCode", workItemQuery.getWorkTypeCode())
                .eq("wi.sprintId", workItemQuery.getSprintId())
                .in("wi.id", workItemQuery.getIds())
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
            queryBuilders.in("wi.workStatusNodeId", arr);
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
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        String sql = new String();
        Object o1 = stringObjectMap.get("sql");
        Object query = stringObjectMap.get("query");
        Integer integer = new Integer(0);
        if(!ObjectUtils.isEmpty(query)){
            sql = sql.concat( String.valueOf(o1));
            integer = this.jpaTemplate.getJdbcTemplate().queryForObject(sql, new String[]{}, Integer.class);
        }else {
            integer = 0;
        }

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
                .eq("wi.sprintId", workItemQuery.getSprintId())
                .eq("wi.workTypeCode", workItemQuery.getWorkTypeCode())
                .notIn("wi.id", workItemQuery.getIdNotIn());


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
            queryBuilders.in("wi.workStatusNodeId", arr);
        }

        if(workItemQuery.getProjectIds() != null){
            List<String> projectIds = workItemQuery.getProjectIds();
            String[] arr = projectIds.toArray(new String[projectIds.size()]);
            queryBuilders.in("wi.projectId", arr);
        }

        if(workItemQuery.getWorkTypeIds() != null){
            List<String> workTypeIdsIds = workItemQuery.getWorkTypeIds();
            String[] arr = workTypeIdsIds.toArray(new String[workTypeIdsIds.size()]);
            queryBuilders.in("wi.workTypeSysId", arr);
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

        if(workItemQuery.getLikeId() != null){
            OrQueryCondition orQueryBuildCondition1 = OrQueryBuilders.instance()
                    .like("id", workItemQuery.getLikeId())
                    .like("title", workItemQuery.getLikeId())
                    .get();
            queryBuilders = queryBuilders.or(orQueryBuildCondition1);
        }
        QueryCondition queryCondition = queryBuilders
                .orders(workItemQuery.getOrderParams())
                .pagination(workItemQuery.getPageParam()).get();
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
        String sql = "Select * from pmc_work_item p where";
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, String> sqlMap = new HashMap<String, String>();
        Object[] objects = {};

        // 如果存在安装优先级查询，就链表查
        List<Order> orderParams = workItemQuery.getOrderParams();
        boolean priorityOrder = orderParams.stream().anyMatch(order -> order.getName().equals("work_priority_id"));
        if(priorityOrder){
            sql = "Select p.*, priority.sort from pmc_work_item p LEFT JOIN pmc_work_priority priority on priority.id = p.work_priority_id where";
        }
        Boolean statisticsNum = workItemQuery.getStatisticsNum();
        if(statisticsNum != null && statisticsNum == true){
            sql = "Select count(1) as count from pmc_work_item p where";
        }

        if(workItemQuery.getVersionId() != null && workItemQuery.getVersionId().length()>0){
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.version_id = '" + workItemQuery.getVersionId() + "'");
            }
            paramMap.put("versionId", workItemQuery.getVersionId());
        }

        if(workItemQuery.getWorkTypeId() != null && workItemQuery.getWorkTypeId().length()>0){

            if(paramMap.isEmpty()){
                sql = sql.concat(" p.work_type_sys_id = '" + workItemQuery.getWorkTypeId() + "'");
            }else {
                sql = sql.concat(" and p.work_type_sys_id = '" + workItemQuery.getWorkTypeId() + "'");
            }
            paramMap.put("workTypeSysId", workItemQuery.getWorkTypeId());

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
                sql = sql.concat(" p.work_type_sys_id in " + s);
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
        if(workItemQuery.getWorkTypeCodes() != null && workItemQuery.getWorkTypeCodes().size()>0){
            List<String> workTypeCodes = workItemQuery.getWorkTypeCodes();
            String s = new String();
            s =  "(";
            for(String workTypeCode :workTypeCodes){
                s = s.concat("'" + workTypeCode + "',");
            }
            s= s.substring(0, s.length() - 1);
            s = s.concat(")");

            if(paramMap.isEmpty()){
                sql = sql.concat(" p.work_type_code in " + s);
            }else {
                sql = sql.concat(" and p.work_type_code in " + s);
            }
            paramMap.put("workTypeCodes", workItemQuery.getWorkTypeCodes());
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
                sql = sql.concat(" p.builder_id = '" + workItemQuery.getBuilderId() + "'");
            }else {
                sql = sql.concat(" and p.builder_id = '" + workItemQuery.getBuilderId() + "'");
            }
            paramMap.put("builderId", workItemQuery.getBuilderId());
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
            buildTimeEnd = buildTimeEnd.concat(" 23:59:59");
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

        if(workItemQuery.getVersionIdIsNull() != null && workItemQuery.getVersionIdIsNull() == true) {
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.version_id is null");
            }else {
                sql = sql.concat(" and p.version_id is null");
            }
        }

        if(workItemQuery.getVersionId() != null) {
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.version_id = '" + workItemQuery.getVersionId() + "'");
            }else {
                sql = sql.concat(" and p.version_id = '" + workItemQuery.getVersionId() + "'");
            }
        }

        if(workItemQuery.getVersionIds() != null && workItemQuery.getVersionIds().size()>0){
            List<String> versionIds = workItemQuery.getVersionIds();
            String s = new String();
            s =  "(";
            for(String versionId:versionIds){
                s = s.concat("'" + versionId + "',");
            }
            s= s.substring(0, s.length() - 1);
            s = s.concat(")");

            if(paramMap.isEmpty()){
                sql = sql.concat(" p.version_id in " + s);
            }else {
                sql = sql.concat(" and p.version_id in " + s);
            }
            paramMap.put("versionIds", workItemQuery.getVersionIds());
        }

        if(workItemQuery.getSprintIdIsNull() != null && workItemQuery.getSprintIdIsNull() == true) {
            if(paramMap.isEmpty()){
                sql = sql.concat(" p.sprint_id is null");
            }else {
                sql = sql.concat(" and p.sprint_id is null");
            }
        }

        objectObjectHashMap.put("query", paramMap);
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
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        Object o1 = stringObjectMap.get("sql");
        Object query = stringObjectMap.get("query");
        if(!ObjectUtils.isEmpty(query)){
            sql = sql.concat( String.valueOf(o1));
            sql = sql.concat(" and p.parent_id is null");
        }else {
            sql = sql.concat( String.valueOf(o1));
            sql = sql.concat(" p.parent_id is null");
        }
        if(!ObjectUtils.isEmpty(workItemQuery.getOrderParams())){
            sql= sql.concat(" order by");
        }
        for (Order orderParam : workItemQuery.getOrderParams()) {
            OrderTypeEnum orderType = orderParam.getOrderType();
            String name = orderParam.getName();
            System.out.println(orderType);
            System.out.println(name);
            if(name.equals("id")){
                sql = sql.concat(" split_part(p.id, '-', 1) " + orderType + ", cast(split_part (p.id, '-', 2) as integer) " + orderType + ",");
            } else if(name.equals("work_priority_id")){
                sql = sql.concat(" priority.sort " + orderType + "," );
            } else {
                sql = sql.concat(" " + name + " " + orderType + "," );
            }
        }

        if(!ObjectUtils.isEmpty(workItemQuery.getOrderParams())){
            sql= sql.substring(0, sql.length() - 1);
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
        Object o1 = stringObjectMap.get("sql");
        Object query = stringObjectMap.get("query");
        if(!ObjectUtils.isEmpty(query)){
            sql = sql.concat(String.valueOf(o1));
            sql = sql.concat(" and p.parent_id is null");
        }else {
            sql = sql.concat( String.valueOf(o1));
            sql = sql.concat(" p.parent_id is null");
        }
        int index = sql.indexOf("where");
        sql = "Select count(1) as total from pmc_work_item p " + sql.substring(index);

        Integer allNum = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
        WorkItemCount.put("all", allNum);

        String sql1 =  sql.concat(" and p.work_type_code = 'demand'");
        Integer demandNum = jpaTemplate.getJdbcTemplate().queryForObject(sql1, new Object[]{}, Integer.class);
        WorkItemCount.put("demand", demandNum);

        String sql2 =  sql.concat(" and p.work_type_code = 'task'");
        Integer taskNum = jpaTemplate.getJdbcTemplate().queryForObject(sql2, new Object[]{}, Integer.class);
        WorkItemCount.put("task", taskNum);

        String sql3 =  sql.concat(" and p.work_type_code = 'defect'");
        Integer defectNum = jpaTemplate.getJdbcTemplate().queryForObject(sql3, new Object[]{}, Integer.class);
        WorkItemCount.put("defect", defectNum);

        String sql4 =  sql.concat(" and p.work_type_code = 'epic'");
        Integer epicNum = jpaTemplate.getJdbcTemplate().queryForObject(sql4, new Object[]{}, Integer.class);
        WorkItemCount.put("epic", epicNum);

        return WorkItemCount;
    }

    public HashMap<String, Integer> findWorkItemListNumByWorkType(WorkItemQuery workItemQuery){
        HashMap<String, Integer> WorkItemCount = new HashMap<>();
        workItemQuery.setWorkTypeId(null);
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        String sql = new String();
        Object o1 = stringObjectMap.get("sql");
        Object query = stringObjectMap.get("query");
        sql = sql.concat(String.valueOf(o1));
//        if(!ObjectUtils.isEmpty(query)){
//            sql = sql.concat(String.valueOf(o1));
//            sql = sql.concat(" and p.parent_id is null");
//        }else {
//            sql = sql.concat( String.valueOf(o1));
//            sql = sql.concat(" p.parent_id is null");
//        }
        int index = sql.indexOf("where");
        sql = "Select count(1) as total from pmc_work_item p " + sql.substring(index);

        Integer allNum = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
        WorkItemCount.put("all", allNum);

        String sql1 =  sql.concat(" and p.work_type_code = 'demand'");
        Integer demandNum = jpaTemplate.getJdbcTemplate().queryForObject(sql1, new Object[]{}, Integer.class);
        WorkItemCount.put("demand", demandNum);

        String sql2 =  sql.concat(" and p.work_type_code = 'task'");
        Integer taskNum = jpaTemplate.getJdbcTemplate().queryForObject(sql2, new Object[]{}, Integer.class);
        WorkItemCount.put("task", taskNum);

        String sql3 =  sql.concat(" and p.work_type_code = 'defect'");
        Integer defectNum = jpaTemplate.getJdbcTemplate().queryForObject(sql3, new Object[]{}, Integer.class);
        WorkItemCount.put("defect", defectNum);

        String sql4 =  sql.concat(" and p.work_type_code = 'epic'");
        Integer epicNum = jpaTemplate.getJdbcTemplate().queryForObject(sql4, new Object[]{}, Integer.class);
        WorkItemCount.put("epic", epicNum);

        return WorkItemCount;
    }
    public Integer findWorkItemNumByQuickSearch(WorkItemQuery workItemQuery){
        HashMap<String, Integer> WorkItemCount = new HashMap<>();
        workItemQuery.setWorkTypeId(null);
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        String sql = new String();
        Object o1 = stringObjectMap.get("sql");

        String sql0 = "";
        if(!ObjectUtils.isEmpty(o1)) {

            String s = String.valueOf(o1);
            String where = StringUtils.substringAfter(s, "where");
            if(!StringUtils.isEmpty(where)){
                sql0 = "Select count(1) as total from pmc_work_item p where ";
                sql0 = sql0.concat(sql + where);
            }else {
                sql0 = "Select count(1) as total from pmc_work_item p";
            }
        }
        Integer num = jpaTemplate.getJdbcTemplate().queryForObject(sql0, new Object[]{}, Integer.class);

        return num;
    }

    public HashMap<String, Integer> findWorkItemNumByWorkStatus(WorkItemQuery workItemQuery){
        HashMap<String, Integer> WorkItemCount = new HashMap<>();
        workItemQuery.setWorkTypeId(null);
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        String sql = new String();
        Object o1 = stringObjectMap.get("sql");

        String sql0 = "";
        if(!ObjectUtils.isEmpty(o1)) {
            sql0 = sql0.concat(sql + String.valueOf(o1));
        }else {
            sql0 = sql;
        }
        Integer allNum = jpaTemplate.getJdbcTemplate().queryForObject(sql0, new Object[]{}, Integer.class);
        WorkItemCount.put("all", allNum);

        if(!ObjectUtils.isEmpty(o1)){
            sql = sql.concat(String.valueOf(o1) + "and");
        }else {
            sql = sql.concat(" where ");
        }

        String sql1 =  sql.concat(" p.work_type_code = 'demand'");
        Integer demandNum = jpaTemplate.getJdbcTemplate().queryForObject(sql1, new Object[]{}, Integer.class);
        WorkItemCount.put("demand", demandNum);

        String sql2 =  sql.concat(" p.work_type_code = 'task'");
        Integer taskNum = jpaTemplate.getJdbcTemplate().queryForObject(sql2, new Object[]{}, Integer.class);
        WorkItemCount.put("task", taskNum);

        String sql3 =  sql.concat(" p.work_type_code = 'defect'");
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
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        Object sql1 = stringObjectMap.get("sql");

        if(!ObjectUtils.isEmpty(sql1)){
            sql = sql.concat(String.valueOf(sql1));
            sql = sql.concat(" and p.parent_id is not null");
        }else {
            sql = sql.concat(" p.parent_id is not null");
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
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        Object o1 = stringObjectMap.get("sql");
        Object query = stringObjectMap.get("query");
        if(!ObjectUtils.isEmpty(query)){
            sql = sql.concat(String.valueOf(o1));
        }else {
            return null;
        }
        if(!ObjectUtils.isEmpty(workItemQuery.getOrderParams())){
            sql= sql.concat(" order by");
        }
        for (Order orderParam : workItemQuery.getOrderParams()) {
            OrderTypeEnum orderType = orderParam.getOrderType();
            String name = orderParam.getName();
            System.out.println(orderType);
            System.out.println(name);
            if(name.equals("id")){
                sql = sql.concat(" split_part(p.id, '-', 1) " + orderType + ", cast(split_part (p.id, '-', 2) as integer) " + orderType + ",");
            } else if(name.equals("work_priority_id")){
                sql = sql.concat(" priority.sort " + orderType + "," );
            } else {
                sql = sql.concat(" " + name + " " + orderType + "," );
            }
        }
        if(!ObjectUtils.isEmpty(workItemQuery.getOrderParams())){
            sql= sql.substring(0, sql.length() - 1);
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
    public Pagination<WorkItemEntity> findConditionWorkItemList(WorkItemQuery workItemQuery) {
        String sql = new String();
//        sql = "Select * from pmc_work_item p ";
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workItemQuery);
        Object o1 = stringObjectMap.get("sql");
        if(String.valueOf(o1).length()>0){
            sql = sql.concat(String.valueOf(o1));
        }
//       List<WorkItemEntity> WorkItemList = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{}, new BeanPropertyRowMapper(WorkItemEntity.class));
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        Pagination WorkItemList = jdbcTemplate.findPage(sql, new Object[]{}, workItemQuery.getPageParam(), new BeanPropertyRowMapper(WorkItemEntity.class));

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
    public Pagination<WorkItemEntity> findCanBeRelationParentWorkItemList(WorkItemQuery workItemQuery){
        String id = workItemQuery.getId();
        String projectId = workItemQuery.getProjectId();
        String workTypeId = workItemQuery.getWorkTypeId();
        String sql = "select * from pmc_work_item where id != '" + id
                + "' and (tree_path not like '%" + id + ";%' or tree_path is null) and project_id = ? and work_type_id = ?";
        if(workItemQuery.getLikeId() != null){
            String likeId = workItemQuery.getLikeId();
            String title = workItemQuery.getTitle();
            sql = sql.concat(" and (id like '%" + likeId + "%' or title like '%" + title + "%')");
        }

        // 前置的前置不能作为父级
        List<String> preWorkItemIds = new ArrayList<>();
        findPreIds(id, preWorkItemIds);


        if(preWorkItemIds.size() > 0){
            String preSql = new String("(");
            for (String postWorkItemId : preWorkItemIds) {
                preSql = preSql.concat("'" + postWorkItemId + "',");
            }
            preSql = preSql.substring(0, preSql.length() - 1);
            preSql = preSql.concat(")");
            sql = sql.concat(" and id not in " + preSql);
        }


        Pagination WorkItemPageList = this.jpaTemplate.getJdbcTemplate().findPage(sql, new String[]{ projectId, workTypeId}, workItemQuery.getPageParam(), new BeanPropertyRowMapper(WorkItemEntity.class));
        return WorkItemPageList;
    }

    public Pagination<WorkItemEntity> findCanBeRelationPreWorkItemList(WorkItemQuery workItemQuery){
        String projectId = workItemQuery.getProjectId();
        String workTypeId = workItemQuery.getWorkTypeId();
        String id = workItemQuery.getId();
        String sql = "select * from pmc_work_item where id != '" + id
                + "' and (tree_path not like '%" + id + ";%' or tree_path is null) and project_id = ? and work_type_id = ?";

        //查找当前事项的上级，不能设置为前置事项, 暂时去掉
        String sql1 = "select tree_path from pmc_work_item where id = '" + id + "'";
        String treePathString = this.jpaTemplate.getJdbcTemplate().queryForObject(sql1, String.class);

        if(treePathString != null &&  treePathString.length() > 0){
            String[] treePathIds = treePathString.split(";");
            String tree = new String("(");
            for (String treePathId : treePathIds) {
                tree = tree.concat("'" + treePathId + "',");
            }
            tree = tree.substring(0, tree.length() - 1);
            tree = tree.concat(")");
            sql = sql.concat(" and id not in " + tree);
        }


        // 查找当前事项的后置事项，以及后置的后置， 不能包括包括后置的事项, 暂时去掉
        List<String> postWorkItemIds = new ArrayList<>();
        findPostIds(id, postWorkItemIds);
        String postSql = new String("(");
        if(postWorkItemIds.size() > 0){
            for (String postWorkItemId : postWorkItemIds) {
                postSql = postSql.concat("'" + postWorkItemId + "',");
            }
            postSql = postSql.substring(0, postSql.length() - 1);
            postSql = postSql.concat(")");
            sql = sql.concat(" and id not in " + postSql);
        }

        // 查找已经被用作前置事项的事项
        String sql2 = "select pre_depend_id from pmc_work_item where project_id = '" + projectId +  "' and id != '" + id + "' and pre_depend_id is not null";
        List<String> preWorkItemIds = this.jpaTemplate.getJdbcTemplate().queryForList(sql2, String.class);
        String preSql = new String("(");
        if(preWorkItemIds.size() > 0){
            for (String preWorkItemId : preWorkItemIds) {
                if(preWorkItemId != null){
                    preSql = preSql.concat("'" + preWorkItemId + "',");
                }

            }
            preSql = preSql.substring(0, preSql.length() - 1);
            preSql = preSql.concat(")");
            sql = sql.concat(" and id not in " + preSql);
        }

        if(workItemQuery.getLikeId() != null){
            String likeId = workItemQuery.getLikeId();
            String title = workItemQuery.getTitle();
            sql = sql .concat(" and (id like '%" + likeId + "%' or title like '%" + title + "%')");
        }

        Pagination WorkItemPageList = this.jpaTemplate.getJdbcTemplate().findPage(sql, new String[]{projectId, workTypeId}, workItemQuery.getPageParam(), new BeanPropertyRowMapper(WorkItemEntity.class));
        return WorkItemPageList;
    }


    public void findPostIds(String id, List<String> postWorkItemIds){
        String sql = "select id from pmc_work_item where pre_depend_id = '" + id +  "'";
        List<String> postIds = this.jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        if(postIds.size() > 0){
            postWorkItemIds.addAll(postIds);
            for (String postId : postIds) {
                findPostIds(postId, postWorkItemIds);
            }
        }
    }

    public void findPreIds(String id, List<String> preWorkItemIds){
        String sql = "select pre_depend_id from pmc_work_item where id = '" + id +  "'";
        String preId = this.jpaTemplate.getJdbcTemplate().queryForObject(sql, String.class);
        if(preId != null && preId.length() > 0){
            preWorkItemIds.add(preId);
            findPostIds(preId, preWorkItemIds);
        }
    }

    public HashMap<String, Integer> findWorkItemRelationModelCount(String workItemId, String workTypeCode) {
        HashMap<String, Integer> relationModel = new HashMap<String, Integer>();
        String sql = new String();
        // 查找关联事项个数
        sql = "Select count(1) as total from pmc_work_relate where work_item_id = '" + workItemId +"' ";
        Integer realtionNum = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
        relationModel.put("relationWork", realtionNum);

        //查找子事项
        if(workTypeCode.equals("demand")){
            sql = "Select count(1) as total from pmc_work_item where parent_id = '" + workItemId +"' and work_type_code = 'demand'";
            Integer childrenWork = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
            relationModel.put("childrenWork", childrenWork);

            sql = "Select count(1) as total from pmc_work_item where parent_id = '" + workItemId +"' and work_type_code = 'task'";
            Integer childrenTaskWork = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
            relationModel.put("childrenTaskWork", childrenTaskWork);
        }else {
            sql = "Select count(1) as total from pmc_work_item where parent_id = '" + workItemId + "'";
            Integer childrenWork = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
            relationModel.put("childrenWork", childrenWork);
        }

        // 工时
        sql = "Select count(1) as total from pmc_work_log where work_item_id = '" + workItemId + "'";
        Integer workLog = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
        relationModel.put("workLog", workLog);

        // 动态
//        sql = "Select count(1) as total from pmc_work_log where work_item_id = '" + workItemId;
//        Integer workLog = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
//        relationModel.put("workLog", workLog);

        // 评论
        sql = "Select count(1) as total from pmc_work_comment where work_item_id = '" + workItemId + "'";
        Integer workComment = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
        relationModel.put("workComment", workComment);

        // 文档
        sql = "Select count(1) as total from pmc_work_item_document where work_item_id = '" + workItemId + "'";
        Integer workDoucment = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
        relationModel.put("workDoucment", workDoucment);

        // 测试用例
        sql = "Select count(1) as total from pmc_work_test_case where work_item_id = '" + workItemId + "'";
        Integer workTestCase = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
        relationModel.put("workTestCase", workTestCase);

        return relationModel;
    }

    /**
     * 查找不同字段关联的事项数量
     * @param colunm
     * @param ids
     * @return
     */
    public List<Map<String, Object>> findWorkItemNum(String colunm, String ids) {

        String sql = "select id, " + colunm  + " from pmc_work_item where " +  colunm  + " in "+ ids;

//        String sql =  "select " + colunm + ", count(1) as total from pmc_work_item where " + colunm +" in "+ ids + "  group by " + colunm;
        List<Map<String, Object>> workItemList = this.jpaTemplate.getJdbcTemplate().queryForList(sql);
        return workItemList;
    }

    public void updateBatchWorkItemSprint(String oldSprintId, String newSprintId){
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        if(newSprintId != null){
            String sql = "update pmc_work_item SET sprint_id = '" + newSprintId + "' WHERE sprint_id = '" + oldSprintId + "'";
            try {
                jdbcTemplate.execute(sql);
            } catch (Exception e){
                throw new ApplicationException(2000,"批量更新事项迭代失败" + e.getMessage());
            }
        }else {
            String sql = "update pmc_work_item SET sprint_id = null WHERE sprint_id = '" + oldSprintId + "'";
            try {
                jdbcTemplate.execute(sql);
            } catch (Exception e){
                throw new ApplicationException(2000,"批量更新事项迭代失败" + e.getMessage());
            }
        }
    }

}