package io.thoughtware.kanass.support.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.kanass.workitem.service.WorkTypeDmService;
import io.thoughtware.todotask.model.Task;
import io.thoughtware.todotask.model.TaskQuery;
import io.thoughtware.todotask.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class TodoTaskAndEndTimeInit implements ApplicationRunner {

    @Autowired
    TaskService taskService;

    @Autowired
    WorkItemService workItemService;

    private static Logger logger = LoggerFactory.getLogger(TodoTaskAndEndTimeInit.class);
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        updateTodoTask();
    }

    public void updateTodoTask(){
        TaskQuery taskQuery = new TaskQuery();
        taskQuery.setBgroup("kanass");
        try {
            Pagination<Task> taskPage = taskService.findTaskPage(taskQuery);
            for (Task task : taskPage.getDataList()) {
                String data = task.getData();
                ObjectMapper objectMapper = new ObjectMapper();
                HashMap hashMap = objectMapper.readValue(data, HashMap.class);
                String workItemId = hashMap.get("workItemId").toString();
                WorkItem workItem = workItemService.findWorkItem(workItemId);
                if(workItem != null){
                    String planEndTime = workItem.getPlanEndTime();
                    if(planEndTime != null){
                        String pattern = "yyyy-MM-dd HH:mm:ss";
                        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                        Date date = null;
                        try {
                            date = dateFormat.parse(planEndTime);
                        } catch (Exception e) {
                            throw new ApplicationException();
                        }
                        Timestamp timestamp = new Timestamp(date.getTime());
                        task.setEndTime(timestamp);
                    }
                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        for (Project project : projectService.findAllProject()) {
//            WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
//            workTypeDmQuery.setCode("epic");
//            workTypeDmQuery.setProjectId(project.getId());
//            List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
//            if(workTypeDmList.size() <= 0){
//                String id = project.getId();
//                projectService.initWorkTypeEpic(id);
//            }
//
//        }
    }
}
