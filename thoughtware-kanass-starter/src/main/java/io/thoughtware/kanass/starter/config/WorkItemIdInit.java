package io.thoughtware.kanass.starter.config;

import io.thoughtware.core.utils.UuidGenerator;
import io.thoughtware.dal.jdbc.JdbcTemplate;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.form.field.model.FieldEx;
import io.thoughtware.form.field.model.SelectItem;
import io.thoughtware.form.field.model.SelectItemRelation;
import io.thoughtware.form.field.service.SelectItemRelationService;
import io.thoughtware.form.form.model.Form;
import io.thoughtware.form.form.model.FormField;
import io.thoughtware.form.form.service.FormFieldService;
import io.thoughtware.kanass.workitem.entity.WorkItemEntity;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.model.WorkItemQuery;
import io.thoughtware.kanass.workitem.model.WorkTypeDm;
import io.thoughtware.kanass.workitem.model.WorkTypeDmQuery;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.kanass.workitem.service.WorkTypeDmService;
import io.thoughtware.message.message.dao.MessageDao;
import io.thoughtware.message.message.dao.MessageItemDao;
import io.thoughtware.message.message.entity.MessageItemEntity;
import io.thoughtware.message.message.model.MessageItem;
import io.thoughtware.message.message.model.MessageItemQuery;
import io.thoughtware.message.message.model.MessageQuery;
import io.thoughtware.message.message.service.MessageItemService;
import io.thoughtware.security.logging.logging.model.Logging;
import io.thoughtware.security.logging.logging.model.LoggingQuery;
import io.thoughtware.security.logging.logging.service.LoggingService;
import io.thoughtware.todotask.todo.model.Task;
import io.thoughtware.todotask.todo.model.TaskQuery;
import io.thoughtware.todotask.todo.service.TaskService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//@Component
public class WorkItemIdInit implements ApplicationRunner {

    @Autowired
    WorkItemService workItemService;

    @Autowired
    MessageItemService messageItemService;

    @Autowired
    LoggingService loggingService;

    @Autowired
    TaskService taskService;

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    MessageItemDao messageItemDao;

    @Autowired
    JoinTemplate joinTemplate;

    private static Logger logger = LoggerFactory.getLogger(WorkItemIdInit.class);

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        System.out.println("0");
        updateWorkItemId();
    }

    public void updateWorkItemId(){
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        for (WorkItem workItem : workItemService.findAllWorkItem()) {
            String newId = UuidGenerator.getRandomIdByUUID(12);
            String id = workItem.getId();
            String sql = "UPDATE pmc_work_item SET id = '" + newId + "' WHERE id = '" + id + "'";
            jdbcTemplate.execute(sql);

            sql = "UPDATE pmc_work_item SET root_id = '" + newId + "' WHERE root_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            sql = "UPDATE pmc_work_item SET parent_id = '" + newId + "' WHERE parent_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            WorkItemQuery workItemQuery = new WorkItemQuery();
            workItemQuery.setTreePath(id);

            sql = "SELECT * FROM pmc_work_item where tree_path like '%;" +
                    id + ";%' or tree_path like '" + id + ";%' ;";
            List<WorkItemEntity> workItemEntityList = jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(WorkItemEntity.class));
            List<WorkItem> workItemList = BeanMapper.mapList(workItemEntityList,WorkItem.class);

            joinTemplate.joinQuery(workItemList);
            for (WorkItem workItem1 : workItemList) {
                String tree = new String();
                String treePath = workItem1.getTreePath();
                if(!StringUtils.isEmpty(treePath)){
                    String[] treePathIds = treePath.split(";");
                    for (String treePathId : treePathIds) {
                        if(!treePathId.equals(id)){
                            tree = tree.concat( treePathId + ";");
                        }else {
                            tree = tree.concat(newId + ";");
                        }
                    }
                    System.out.println(tree);
                    workItem1.setTreePath(tree);
                    workItemService.updateWork(workItem1);
                }

            }

            // 修改流程与事项关联中事项的id
            sql = "UPDATE pcs_flc_state_node_relation SET work_id = '" + newId + "' WHERE work_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            // 修改表单选项与事项关联中事项的id
            sql = "UPDATE pcs_foc_select_item_rela SET relation_id = '" + newId + "' WHERE relation_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            // 更新消息
            MessageItemQuery messageItemQuery = new MessageItemQuery();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("workItemId", id);
            messageItemQuery.setData(linkedHashMap);

            sql = "SELECT * FROM pcs_mec_message_dispatch_item WHERE 1=1 AND bgroup IN ('kanass') AND data ::json #>>'{workItemId}'= '" + id + "'";
            List<MessageItemEntity> messageDispatchItemList = jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(MessageItemEntity.class));
//            List<MessageItem> messageDispatchItemListchItemList = messageItemService.findMessageDispatchItemList(messageItemQuery);
            for (MessageItemEntity messageItem : messageDispatchItemList) {
                String link = messageItem.getLink();
                String old = "/" + id;
                link = link.replace(old, '/' +  newId);
                messageItem.setLink(link);

                String data = messageItem.getData();
                String oldIdValue = '"' + id + '"';
                data = data.replace(oldIdValue, '"' +  newId + '"');
                messageItem.setData(data);
                messageItemDao.updateMessageSend(messageItem);
            }

            // 更新日志
            LoggingQuery loggingQuery = new LoggingQuery();
            loggingQuery.setData(linkedHashMap);
            List<Logging> logList = loggingService.findLogList(loggingQuery);
            for (Logging logging : logList) {
                String link = logging.getLink();
                String old = "/" + id;
                link = link.replace(old, '/' +  newId);
                logging.setLink(link);

                String data = logging.getData();
                String oldIdValue = '"' + id + '"';
                data = data.replace(oldIdValue, '"' +  newId + '"');
                logging.setData(data);
                loggingService.updateLog(logging);
            }

            // 更新待办
            TaskQuery taskQuery = new TaskQuery();
            taskQuery.setData(linkedHashMap);
            List<Task> taskList = taskService.findTaskList(taskQuery);
            for (Task task : taskList) {
                String link = task.getLink();
                String old = "/" + id;
                link = link.replace(old, '/' +  newId);
                task.setLink(link);

                String data = task.getData();
                String oldIdValue = '"' + id + '"';
                data = data.replace(oldIdValue, '"' +  newId + '"');
                task.setData(data);
                taskService.updateTask(task);
            }

            // 更新最近点击的关联事项id
            sql = "UPDATE pmc_recent SET model_id = '" + newId + "' WHERE model_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            // 更新阶段关联的事项id
            sql = "UPDATE pmc_stage_work_item SET work_item_id = '" + newId + "' WHERE work_item_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            // 更新事项附件关联的事项id
            sql = "UPDATE pmc_work_attach SET work_item_id = '" + newId + "' WHERE work_item_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            // 更新事项评论关联的事项id
            sql = "UPDATE pmc_work_comment SET work_item_id = '" + newId + "' WHERE work_item_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            // 更新事项文档关联的事项id
            sql = "UPDATE pmc_work_item_document SET work_item_id = '" + newId + "' WHERE work_item_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            // 更新事项工时关联的事项id
            sql = "UPDATE pmc_work_log SET work_item_id = '" + newId + "' WHERE work_item_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            // 更新关联事项关联的事项id
            sql = "UPDATE pmc_work_relate SET work_item_id = '" + newId + "' WHERE work_item_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            sql = "UPDATE pmc_work_relate SET relate_item_id = '" + newId + "' WHERE relate_item_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            // 更新迭代与事项关联的事项id
            sql = "UPDATE pmc_work_sprint SET work_item_id = '" + newId + "' WHERE work_item_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            // 更新事项测试用例的事项id
            sql = "UPDATE pmc_work_test_case SET work_item_id = '" + newId + "' WHERE work_item_id = '" + id + "'";
            jdbcTemplate.execute(sql);

            // 更新事项测试用例的事项id
            sql = "UPDATE pmc_work_version SET work_item_id = '" + newId + "' WHERE work_item_id = '" + id + "'";
            jdbcTemplate.execute(sql);
            logger.info(id);
        }
    }

}
