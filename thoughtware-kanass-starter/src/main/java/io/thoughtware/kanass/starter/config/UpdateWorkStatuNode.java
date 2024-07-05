package io.thoughtware.kanass.starter.config;

import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.flow.flow.model.Flow;
import io.thoughtware.flow.statenode.model.StateNodeFlow;
import io.thoughtware.flow.statenode.model.StateNodeFlowQuery;
import io.thoughtware.flow.statenode.model.StateNodeRelation;
import io.thoughtware.flow.statenode.model.StateNodeRelationQuery;
import io.thoughtware.flow.statenode.service.StateNodeFlowService;
import io.thoughtware.flow.statenode.service.StateNodeRelationService;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.model.WorkItemQuery;
import io.thoughtware.kanass.workitem.model.WorkTypeDm;
import io.thoughtware.kanass.workitem.model.WorkTypeDmQuery;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.kanass.workitem.service.WorkTypeDmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
public class UpdateWorkStatuNode implements ApplicationRunner {
    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    ProjectService projectService;

    @Autowired
    WorkTypeDmService workTypeDmService;

    @Autowired
    StateNodeFlowService stateNodeFlowService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    StateNodeRelationService stateNodeRelationService;



    private static Logger logger = LoggerFactory.getLogger(UpdateWorkStatuNode.class);


    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        System.out.println("0");
        updateWorkStatuNode();
    }

    public void updateWorkStatuNode(){
        for (Project project : projectService.findAllProject()) {
            String projectId = project.getId();
            WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
            workTypeDmQuery.setProjectId(projectId);
            List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
            for (WorkTypeDm workTypeDm : workTypeDmList) {
                String workTypeId = workTypeDm.getWorkType().getId();
                Flow flow = workTypeDm.getFlow();
                StateNodeFlowQuery stateNodeFlowQuery = new StateNodeFlowQuery();
                stateNodeFlowQuery.setFlowId(flow.getId());
                List<StateNodeFlow> stateNodeFlowList = stateNodeFlowService.findStateNodeFlowList(stateNodeFlowQuery);
                for (StateNodeFlow stateNodeFlow : stateNodeFlowList) {
                    String stateNodeFlowId = stateNodeFlow.getId();
                    String nodeStatus = stateNodeFlow.getNodeStatus();
                    WorkItemQuery workItemQuery = new WorkItemQuery();
                    workItemQuery.setProjectId(projectId);
                    workItemQuery.setWorkTypeSysId(workTypeId);
                    workItemQuery.setWorkStatusCode(nodeStatus);
                    List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
                    for (WorkItem workItem : workItemList) {
                        String id = workItem.getId();
                        workItem.setWorkStatus(stateNodeFlow);
                        logger.info("joinQuery cost workItemId:{}", id);
                        logger.info("joinQuery cost stateNodeFlowId:{}", stateNodeFlowId);
                        workItemService.updateWork(workItem);

                        StateNodeRelationQuery stateNodeRelationQuery = new StateNodeRelationQuery();
                        stateNodeRelationQuery.setWorkId(id);
                        List<StateNodeRelation> stateNodeRelationList = stateNodeRelationService.findStateNodeRelationList(stateNodeRelationQuery);
                        for (StateNodeRelation stateNodeRelation : stateNodeRelationList) {
                            stateNodeRelation.setStateNodeId(stateNodeFlowId);
                            stateNodeRelation.setFlowId(flow.getId());
                            stateNodeRelationService.updateStateNodeRelation(stateNodeRelation);
                        }
                    }
                }

            }


        }

    }
}
