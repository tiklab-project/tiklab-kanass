package io.tiklab.kanass.support.service;

import io.tiklab.flow.flow.model.Flow;
import io.tiklab.flow.flow.model.FlowModelRelation;
import io.tiklab.flow.flow.model.FlowQuery;
import io.tiklab.flow.flow.service.FlowModelRelationService;
import io.tiklab.flow.flow.service.FlowService;
import io.tiklab.form.form.service.FormService;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.workitem.model.WorkTypeDm;
import io.tiklab.kanass.workitem.model.WorkTypeDmQuery;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.kanass.workitem.service.WorkTypeDmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyFlowToProjectImpl implements CopyFlowToProjectService {
    @Autowired
    FlowService flowService;

    @Autowired
    FormService formService;

    @Autowired
    ProjectService projectService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    WorkTypeDmService workTypeDmService;

    @Autowired
    FlowModelRelationService flowModelRelationService;


    @Override
    public void copyFlow(){
        FlowQuery flowQuery = new FlowQuery();
        flowQuery.setScope(1);
        List<Flow> flowList = flowService.findFlowList(flowQuery);
        List<Project> allProject = projectService.findAllProject();
        for (Project project : allProject) {
            String projectId = project.getId();
            for (Flow flow : flowList) {
                String oldFlow = flow.getId();
                Flow flow2 = flowService.copyFlow(oldFlow, projectId);
                String flowId = flow2.getId();
                if(oldFlow.equals("a96cf9c9")){
                    WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
                    workTypeDmQuery.setWorkTypeId("7055ebc6");
                    workTypeDmQuery.setProjectId(projectId);
                    List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
                   if(workTypeDmList.size() > 0){
                       WorkTypeDm workTypeDm = workTypeDmList.get(0);
                       String id = workTypeDm.getId();
                       Flow flow1 = new Flow();
                       flow1.setId(flowId);
                       workTypeDm.setFlow(flow1);
                       workTypeDmService.updateWorkTypeDm(workTypeDm);

                       FlowModelRelation flowModelRelation = new FlowModelRelation();
                       flowModelRelation.setFlowId(flowId);
                       flowModelRelation.setModelId(id);
                       flowModelRelation.setModelType("workTypeDm");
                       flowModelRelation.setModelName(workTypeDm.getWorkType().getName());
                       flowModelRelation.setBgroup("kanass");
                       flowModelRelationService.createFlowModelRelation(flowModelRelation);
                   }
                }

                if(oldFlow.equals("4d040c6d")){
                    WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
                    workTypeDmQuery.setWorkTypeId("778222e0");
                    workTypeDmQuery.setProjectId(projectId);
                    List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
                    if(workTypeDmList.size() > 0){
                        WorkTypeDm workTypeDm = workTypeDmList.get(0);
                        String id = workTypeDm.getId();
                        Flow flow1 = new Flow();
                        flow1.setId(flowId);
                        workTypeDm.setFlow(flow1);
                        workTypeDmService.updateWorkTypeDm(workTypeDm);

                        FlowModelRelation flowModelRelation = new FlowModelRelation();
                        flowModelRelation.setFlowId(flowId);
                        flowModelRelation.setModelId(id);
                        flowModelRelation.setModelType("workTypeDm");
                        flowModelRelation.setModelName(workTypeDm.getWorkType().getName());
                        flowModelRelation.setBgroup("kanass");
                        flowModelRelationService.createFlowModelRelation(flowModelRelation);
                    }
                }

                if(oldFlow.equals("3d879830")){
                    WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
                    workTypeDmQuery.setWorkTypeId("98121701");
                    workTypeDmQuery.setProjectId(projectId);
                    List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
                    if(workTypeDmList.size() > 0){
                        WorkTypeDm workTypeDm = workTypeDmList.get(0);
                        String id = workTypeDm.getId();
                        Flow flow1 = new Flow();
                        flow1.setId(flowId);
                        workTypeDm.setFlow(flow1);
                        workTypeDmService.updateWorkTypeDm(workTypeDm);

                        FlowModelRelation flowModelRelation = new FlowModelRelation();
                        flowModelRelation.setFlowId(flowId);
                        flowModelRelation.setModelId(id);
                        flowModelRelation.setModelType("workTypeDm");
                        flowModelRelation.setModelName(workTypeDm.getWorkType().getName());
                        flowModelRelation.setBgroup("kanass");
                        flowModelRelationService.createFlowModelRelation(flowModelRelation);
                    }
                }
            }
        }
    }
}
