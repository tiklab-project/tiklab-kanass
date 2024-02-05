package io.thoughtware.kanass.support.support;


import io.thoughtware.core.utils.UuidGenerator;
import io.thoughtware.kanass.project.version.model.ProjectVersion;
import io.thoughtware.kanass.sprint.model.Sprint;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.kanass.workitem.service.WorkSprintService;
import io.thoughtware.kanass.workitem.service.WorkVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class WorkSprintVersionInit implements ApplicationRunner {
    @Autowired
    WorkItemService workItemService;

    @Autowired
    WorkSprintService workSprintService;

    @Autowired
    WorkVersionService workVersionService;

    private static Logger logger = LoggerFactory.getLogger(WorkSprintVersionInit.class);
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        initWorkSprintVersionInit();
    }

    public void initWorkSprintVersionInit(){
        List<WorkItem> allWorkItem = workItemService.findAllWorkItem();
        String sprintValueString = "";
        String versionValueString = "";
        for (WorkItem workItem : allWorkItem) {
            String id = workItem.getId();
            Sprint sprint = workItem.getSprint();
            ProjectVersion projectVersion = workItem.getProjectVersion();
            if(sprint != null){
                String workSprintId = UuidGenerator.getRandomIdByUUID(12);
                String sql = "('" + workSprintId + "', '" + id + "', '" + sprint.getId() + "'),";
                sprintValueString = sprintValueString.concat(sql);
            }
            if(projectVersion != null){
                String workVersionId = UuidGenerator.getRandomIdByUUID(12);
                String sql = "('" + workVersionId + "', '" + id + "', '" + projectVersion.getId() + "'),";
                versionValueString = versionValueString.concat(sql);
            }

        }

        int length = sprintValueString.length() - 1;
        String substring = sprintValueString.substring(0, length);
        workSprintService.createBatchWorkSprint(substring);

        int length1 = versionValueString.length() - 1;
        String substring1 = versionValueString.substring(0, length1);
        workVersionService.createBatchWorkVersion(substring1);


    }
}
