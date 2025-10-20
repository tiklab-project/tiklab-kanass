package io.tiklab.kanass.support.sql.service;

import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.kanass.common.IDGeneratorUtil;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.service.WorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Pmc168WorkCodeTask implements DsmProcessTask {

    @Autowired
    private WorkItemService workItemService;

    @Override
    public void execute() {
        List<WorkItem> allList = workItemService.findAllWorkItem();

        Map<String, List<WorkItem>> allWorkItem = allList.stream().collect(Collectors.groupingBy(
                item -> item.getProject().getId(),
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> list.stream()
                                .sorted(Comparator.comparing(WorkItem::getOrderNum))
                                .collect(Collectors.toList())
                )
        ));

        for (String projectId : allWorkItem.keySet()) {
            // 生成新的id
            String newId = IDGeneratorUtil.generateID();
            List<WorkItem> workItemList = allWorkItem.get(projectId);
            for (WorkItem workItem : workItemList) {
                workItem.setCode(newId);

                newId = IDGeneratorUtil.incrementID(newId);
                workItemService.updateWork(workItem);

            }
        }


    }
}
