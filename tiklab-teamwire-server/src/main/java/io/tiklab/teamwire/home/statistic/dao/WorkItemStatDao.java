package io.tiklab.teamwire.home.statistic.dao;

import io.tiklab.teamwire.home.statistic.model.ProjectWorkItemStat;
import io.tiklab.teamwire.home.statistic.model.WorkItemBusStatusStat;
import io.tiklab.teamwire.home.statistic.model.WorkItemStatistic;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.sprint.model.Sprint;
import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.teamwire.workitem.model.WorkItemQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teamwire.project.project.entity.ProjectEntity;
import io.tiklab.teamwire.sprint.entity.SprintEntity;
import io.tiklab.teamwire.workitem.dao.WorkStatusDao;
import io.tiklab.teamwire.workitem.entity.WorkItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 事项统计数据操作
 */
@Repository
public class WorkItemStatDao {

    private static Logger logger = LoggerFactory.getLogger(WorkItemStatDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    JoinTemplate joinTemplate;
    @Autowired
    WorkStatusDao workStatusDao;

    /**
     * 按状态统计事项分布
     * @return
     */
    public List<WorkItemStatistic> statWorkItemByStatus() {
        List<WorkItemStatistic> list = new ArrayList<>();
        //统计总数
        String sql = "select count(1) as totalCount from pmc_work_item t";
        Integer totalCount = getJdbcTemplate().queryForObject(sql,Integer.class);
        if(totalCount == 0){
            return list;
        }

        //按状态统计
        sql = "select t.work_status_id as workStatusId,count(1) as groupCount from pmc_work_item t group by t.work_status_id";
        list = getJdbcTemplate().query(sql,new BeanPropertyRowMapper(WorkItemStatistic.class));
        if(list != null && list.size() > 0){
            for(WorkItemStatistic item:list){
                item.setTotalCount(totalCount);
                item.process();
            }
        }
        return list;
    }

    /**
     * 按迭代下状态统计事项分布
     * @return
     */
    public List<WorkItemStatistic> statSprintWorkItemByStatus(String sprintId) {
        List<WorkItemStatistic> list = new ArrayList<>();
        //统计总数
        String sql = "select count(1) as totalCount from pmc_work_item t Where t.sprint_id = ?";
        Integer totalCount = getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        if(totalCount == 0){
            return list;
        }

        //按状态统计
        sql = "select t.work_status_id as workStatusId,count(1) as groupCount from pmc_work_item t where t.sprint_id = ? group by t.work_status_id";

        list = getJdbcTemplate().query(sql,new String[]{sprintId},new BeanPropertyRowMapper(WorkItemStatistic.class));
        if(list != null && list.size() > 0){
            for(WorkItemStatistic item:list){
                item.setTotalCount(totalCount);
                item.process();
            }
        }
        return list;
    }
    /**
     * 按业务状态统计事项分布
     * @return
     */
    public List<WorkItemBusStatusStat> statWorkItemByBusStatus() {
        List<WorkItemBusStatusStat> list = new ArrayList<>();

        //统计总数
        String sql = "select count(1) as totalCount from pmc_work_item t";
        Integer totalCount = getJdbcTemplate().queryForObject(sql,Integer.class);
        if(totalCount == 0){
            return list ;
        }

        WorkItemBusStatusStat itemBusStatusStatAll = new WorkItemBusStatusStat();
        itemBusStatusStatAll.setStatusName("全部事项");
        itemBusStatusStatAll.setGroupCount(totalCount);
        list.add(itemBusStatusStatAll);

        // 统计已完成的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.work_status_code='DONE'");
        Integer endTotalCount = getJdbcTemplate().queryForObject(sql,Integer.class);

        WorkItemBusStatusStat itemBusStatusStatEnd = new WorkItemBusStatusStat();
        itemBusStatusStatEnd.setStatusName("已完成");
        itemBusStatusStatEnd.setGroupCount(endTotalCount);
        list.add(itemBusStatusStatEnd);

        // 统计进行中的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t, pcs_flc_state_node f";
        sql = sql.concat(" where t.work_status_code='DONE'");
        Integer processTotalCount = getJdbcTemplate().queryForObject(sql,Integer.class);

        WorkItemBusStatusStat itemBusStatusStatProcess= new WorkItemBusStatusStat();
        itemBusStatusStatProcess.setStatusName("进行中");
        itemBusStatusStatProcess.setGroupCount(processTotalCount);
        list.add(itemBusStatusStatProcess);

        // 统计已逾期的事项
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        String newDate = ft.format(dNow);
        sql = "select count(1) as endTotalCount from pmc_work_item t, pcs_flc_state_node f";
        sql = sql.concat(" where t.work_status_code='DONE' and ? > t.plan_end_time ");
        Integer delayTotalCount = getJdbcTemplate().queryForObject(sql,new String[]{newDate}, Integer.class);

        WorkItemBusStatusStat itemBusStatusStatDelay= new WorkItemBusStatusStat();
        itemBusStatusStatDelay.setStatusName("已逾期");
        itemBusStatusStatDelay.setGroupCount(delayTotalCount);
        list.add(itemBusStatusStatDelay);

        return list;
    }

    /**
     * 按业务状态统计项目下的事项分布
     * @return
     */
    public List<WorkItemBusStatusStat> statProjectWorkItemByBusStatus(String projectId, String masterId) {
        List<WorkItemBusStatusStat> list = new ArrayList<>();

        //统计总数

        String sql = "select count(1) as totalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id=? ");

        if(masterId != null){
            sql.concat("ant t.assigner_id = '"+ masterId +"' or t.builder_id= '"+ masterId +"' or t.reporter_id= '"+ masterId + "'");
        }
        Integer totalCount = getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        if(totalCount == 0){
            WorkItemBusStatusStat itemBusStatusStatAll = new WorkItemBusStatusStat();
            itemBusStatusStatAll.setStatusName("全部事项事项");
            itemBusStatusStatAll.setGroupCount(0);
            list.add(itemBusStatusStatAll);

            WorkItemBusStatusStat itemBusStatusStatNoStart = new WorkItemBusStatusStat();
            itemBusStatusStatNoStart.setStatusName("未完成事项");
            itemBusStatusStatNoStart.setGroupCount(0);
            list.add(itemBusStatusStatNoStart);

            WorkItemBusStatusStat itemBusStatusStatEnd = new WorkItemBusStatusStat();
            itemBusStatusStatEnd.setStatusName("已完成事项");
            itemBusStatusStatEnd.setGroupCount(0);
            list.add(itemBusStatusStatEnd);

            WorkItemBusStatusStat itemBusStatusStatProcess= new WorkItemBusStatusStat();
            itemBusStatusStatProcess.setStatusName("进行中事项");
            itemBusStatusStatProcess.setGroupCount(0);
            list.add(itemBusStatusStatProcess);

            WorkItemBusStatusStat itemBusStatusStatDelay= new WorkItemBusStatusStat();
            itemBusStatusStatDelay.setStatusName("已逾期事项");
            itemBusStatusStatDelay.setGroupCount(0);
            list.add(itemBusStatusStatDelay);

            return list ;
        }

        WorkItemBusStatusStat itemBusStatusStatAll = new WorkItemBusStatusStat();
        itemBusStatusStatAll.setStatusName("全部事项");
        itemBusStatusStatAll.setGroupCount(totalCount);
        list.add(itemBusStatusStatAll);

        // 统计未开始的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code='START' or t.work_status_code='TODO'");
        if(masterId != null){
            sql.concat(" ant t.assigner_id = '"+ masterId +"' or t.builder_id= '"+ masterId +"' or t.reporter_id= '"+ masterId + "'");
        }

        Integer nostartTotalCount = getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);

        WorkItemBusStatusStat itemBusStatusStatNoStart = new WorkItemBusStatusStat();
        itemBusStatusStatNoStart.setStatusName("未开始事项");
        itemBusStatusStatNoStart.setGroupCount(nostartTotalCount);
        list.add(itemBusStatusStatNoStart);

        // 统计已完成的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code ='DONE'");
        if(masterId != null){
            sql.concat(" ant t.assigner_id = '"+ masterId +"' or t.builder_id= '"+ masterId +"' or t.reporter_id= '"+ masterId + "'");
        }

        Integer endTotalCount = getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);

        WorkItemBusStatusStat itemBusStatusStatEnd = new WorkItemBusStatusStat();
        itemBusStatusStatEnd.setStatusName("已完成事项");
        itemBusStatusStatEnd.setGroupCount(endTotalCount);
        list.add(itemBusStatusStatEnd);

        // 统计进行中的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code ='PROGRESS'");
        if(masterId != null){
            sql.concat(" ant t.assigner_id = '"+ masterId +"' or t.builder_id= '"+ masterId +"' or t.reporter_id= '"+ masterId + "'");
        }
        Integer processTotalCount = getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);

        WorkItemBusStatusStat itemBusStatusStatProcess= new WorkItemBusStatusStat();
        itemBusStatusStatProcess.setStatusName("进行中事项");
        itemBusStatusStatProcess.setGroupCount(processTotalCount);
        list.add(itemBusStatusStatProcess);

        // 统计已逾期的事项
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        String newDate = ft.format(dNow);
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code !='DONE' and ? > t.plan_end_time ");
        if(masterId != null){
            sql.concat("ant t.assigner_id = '"+ masterId +"' or t.builder_id= '"+ masterId +"' or t.reporter_id= '"+ masterId + "'");
        }
        Object[] params = new Object[]{projectId,newDate};
        Integer delayTotalCount = getJdbcTemplate().queryForObject(sql, params, Integer.class);

        WorkItemBusStatusStat itemBusStatusStatDelay= new WorkItemBusStatusStat();
        itemBusStatusStatDelay.setStatusName("已逾期事项");
        itemBusStatusStatDelay.setGroupCount(delayTotalCount);
        list.add(itemBusStatusStatDelay);

        return list;
    }

    /**
     * 管理项目的事项统计
     * @return
     */
    public List<ProjectWorkItemStat> statProjectWorkItemCount(String masterId) {
        List<ProjectWorkItemStat> list = new ArrayList<>();

        String sql = "select * from pmc_recent r, pmc_project p";
        sql = sql.concat(" where p.id = r.model_id && r.master_id = ? ");

        List<ProjectEntity> projectEntitylist = getJdbcTemplate().query(sql, new String[]{masterId}, new BeanPropertyRowMapper(ProjectEntity.class));

        // 数据成转换成业务层数据
        List<Project> projectList = BeanMapper.mapList(projectEntitylist,Project.class);
        // 统计已完成的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t, pcs_flc_state_node f";
        String sqlEnd = sql.concat(" where f.node_status='DONE' and f.id=t.work_status_id and t.project_id = ?");
        String sqlProcess =  sql.concat(" where f.node_status!='DONE' and f.id=t.work_status_id and t.project_id = ?");
        if(projectList != null && projectList.size() > 0){
            for(Project item:projectList){
                ProjectWorkItemStat projectWorkItemStat = new ProjectWorkItemStat();
                projectWorkItemStat.setProject(item);
                String projectId = item.getId();
                Integer endTotalCount = getJdbcTemplate().queryForObject(sqlEnd,new String[]{projectId},Integer.class);
                projectWorkItemStat.setEndWorkItemCount((endTotalCount));
                Integer processTotalCount = getJdbcTemplate().queryForObject(sqlProcess,new String[]{projectId},Integer.class);
                projectWorkItemStat.setProcessWorkItemCount(processTotalCount);
                list.add(projectWorkItemStat);
            }
        }
        return list;
    }

    //管理的所有迭代
    public List<Sprint> statManageSprint(String projectId) {
        List<SprintEntity> list = new ArrayList<>();
        String sql = "select * from pmc_sprint s";
        sql = sql.concat(" where s.project_id = ? ");
        list = getJdbcTemplate().query(sql, new String[]{projectId}, new BeanPropertyRowMapper(SprintEntity.class));
        //        数据成转换成业务层数据
        List<Sprint> sprintList = BeanMapper.mapList(list,Sprint.class);
        return sprintList;
    }


    /**
     * 统计我管理的项目下迭代
     * @param masterId
     * @param projectId
     * @return
     */
    public List<Sprint> statProjectManageSprint(String masterId,String projectId) {
        List<SprintEntity> list = new ArrayList<>();
        String sql = "select * from pmc_sprint s";
        sql = sql.concat(" where s.master = ? and s.project_id = ?");
        Object[] params = new Object[]{masterId,projectId};
        list = getJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(SprintEntity.class));
        //        数据成转换成业务层数据
        List<Sprint> sprintList = BeanMapper.mapList(list,Sprint.class);
        return sprintList;
    }

    // 某个迭代下我管理的进行中的事项
    public List<WorkItem> statSprintProcessWorkItem(String masterId, String sprintId) {
        List<WorkItemEntity> list = new ArrayList<>();
        String sql = "select * from pmc_work_item w, flc_state f";
        sql = sql.concat(" where f.state_type = 2 and f.id = w.work_status_id and w.assigner_id = ? and w.sprint_id = ?");
        Object[] params = new Object[]{masterId,sprintId};
        list = getJdbcTemplate().query(sql, params, new BeanPropertyRowMapper(WorkItemEntity.class));
        //        数据成转换成业务层数据
        List<WorkItem> WorkItemList = BeanMapper.mapList(list,WorkItem.class);
        return WorkItemList;
    }

    /**
     * 按业务状态统计迭代事项分布
     * @return
     */
    public List<WorkItemBusStatusStat> statSprintWorkItemByBusStatus(String sprintId) {
        List<WorkItemBusStatusStat> list = new ArrayList<>();

        //统计总数
        String sql = "select count(1) as totalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id=? ");
        Integer totalCount = getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        if(totalCount == 0){
            return list ;
        }

        WorkItemBusStatusStat itemBusStatusStatAll = new WorkItemBusStatusStat();
        itemBusStatusStatAll.setStatusName("全部事项");
        itemBusStatusStatAll.setGroupCount(totalCount);
        list.add(itemBusStatusStatAll);

        // 统计已完成的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t, flc_state f";
        sql = sql.concat(" where t.sprint_id= ? and f.state_type=3 and f.id=t.work_status_id");
        Integer endTotalCount = getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);

        WorkItemBusStatusStat itemBusStatusStatEnd = new WorkItemBusStatusStat();
        itemBusStatusStatEnd.setStatusName("已完成");
        itemBusStatusStatEnd.setGroupCount(endTotalCount);
        list.add(itemBusStatusStatEnd);

        // 统计进行中的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t, flc_state f";
        sql = sql.concat(" where t.sprint_id= ? and f.state_type = 2 and f.id = t.work_status_id");
        Integer processTotalCount = getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);

        WorkItemBusStatusStat itemBusStatusStatProcess= new WorkItemBusStatusStat();
        itemBusStatusStatProcess.setStatusName("进行中");
        itemBusStatusStatProcess.setGroupCount(processTotalCount);
        list.add(itemBusStatusStatProcess);

        // 统计已逾期的事项
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        String newDate = ft.format(dNow);
        sql = "select count(1) as endTotalCount from pmc_work_item t, flc_state f";
        sql = sql.concat(" where t.sprint_id= ? and f.state_type = 2 and f.id = t.work_status_id and ? > t.plan_end_time ");
        Object[] params = new Object[]{sprintId,newDate};
        Integer delayTotalCount = getJdbcTemplate().queryForObject(sql, params, Integer.class);

        WorkItemBusStatusStat itemBusStatusStatDelay= new WorkItemBusStatusStat();
        itemBusStatusStatDelay.setStatusName("已逾期");
        itemBusStatusStatDelay.setGroupCount(delayTotalCount);
        list.add(itemBusStatusStatDelay);

        return list;
    }

    /**
     *  统计待办事项
     * @return
     */
    public List<WorkItemEntity> statWorkItemProcess() {
        List<WorkItemEntity> workItemEntityList = new ArrayList<>();
        String sql = "select * from pcs_flc_state_node f, pmc_work_item t";

        sql = sql.concat(" where f.node_status!='DONE' and f.id = t.work_status_id ");

        workItemEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{}, new BeanPropertyRowMapper(WorkItemEntity.class));

        return workItemEntityList;
    }

    public List<WorkItemEntity> statProgramSetWorkItemProcess(String[] ids) {
        List<WorkItemEntity> workItemEntityList = new ArrayList<>();
        String sql = "select * from pcs_flc_state_node f, pmc_work_item t";

        String s = new String("(");
        ArrayList<String> objects = new ArrayList<>();
        for(String id:ids){
            s = s.concat("'" + id + "',");
        }
        s= s.substring(0, s.length() - 1);
        s=s.concat(")");

        //需要修改 2023/02/25
        sql = sql.concat(" where f.id!='DONE' and f.id = t.work_status_id and t.project_id in " + s);



        workItemEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new Object[]{}, new BeanPropertyRowMapper(WorkItemEntity.class));

        return workItemEntityList;
    }

    public List<WorkItemEntity> statProjectWorkItemProcess(String projectId) {
        List<WorkItemEntity> workItemEntityList = new ArrayList<>();
        String sql = "select * from pcs_flc_state_node f, pmc_work_item t";

        sql = sql.concat(" where f.node_status!='DONE' and f.id = t.work_status_id and t.project_id = ? ");

        workItemEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{projectId}, new BeanPropertyRowMapper(WorkItemEntity.class));

        return workItemEntityList;
    }


    public Pagination<WorkItemEntity> statWorkItemOverdue(WorkItemQuery workItemQuery) {
        List<WorkItemEntity> workItemEntityList = new ArrayList<>();
        String sql = "select t.* from pmc_work_item t, pcs_flc_state_node f";
        sql = sql.concat(" where f.node_status!='DONE' and f.id = t.work_status_id and t.plan_end_time < ? ");

        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        String newDate = ft.format(dNow);

        List<Object> objects = new ArrayList<>();
        objects.add(newDate);


        if(workItemQuery.getProjectId() != null){
            sql = sql.concat( "and t.project_id= ?");
            objects.add(workItemQuery.getProjectId());

        }

        if(workItemQuery.getSprintId() != null){
            sql = sql.concat( " and t.sprint_id= ?");
            objects.add(workItemQuery.getSprintIds());

        }
        int size = objects.size();
        Object[] objects1 = new Object[size];
        Object[] objects2 = objects.toArray(objects1);

        Pagination<WorkItemEntity> workItemEntityPagination =
                this.jpaTemplate.getJdbcTemplate().findPage(sql,objects2,workItemQuery.getPageParam(),new BeanPropertyRowMapper(WorkItemEntity.class));
        return workItemEntityPagination;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jpaTemplate.getJdbcTemplate();
    }

}