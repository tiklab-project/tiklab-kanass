package io.tiklab.teamwire.support.support;

import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.service.ProjectService;
import io.tiklab.teamwire.workitem.controller.WikiDocumentController;
import io.tiklab.teamwire.workitem.model.WorkTypeDm;
import io.tiklab.teamwire.workitem.model.WorkTypeDmQuery;
import io.tiklab.teamwire.workitem.service.WorkTypeDmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class WorkTypeEpicInit implements ApplicationRunner {

    @Autowired
    ProjectService projectService;
    @Autowired
    WorkTypeDmService workTypeDmService;

    private static Logger logger = LoggerFactory.getLogger(WorkTypeEpicInit.class);
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        initWorkTypeEpicForProject();
    }

    public void initWorkTypeEpicForProject(){
        for (Project project : projectService.findAllProject()) {
            WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
            workTypeDmQuery.setCode("epic");
            workTypeDmQuery.setProjectId(project.getId());
            List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
            if(workTypeDmList.size() <= 0){
                String id = project.getId();
                projectService.initWorkTypeEpic(id);
            }

        }
    }
}
