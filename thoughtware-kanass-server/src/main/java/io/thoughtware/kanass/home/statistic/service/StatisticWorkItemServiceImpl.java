package io.thoughtware.kanass.home.statistic.service;

import io.thoughtware.kanass.home.statistic.model.StatisticWorkItemQuery;
import io.thoughtware.kanass.home.statistic.model.WorkItemBuildAndEndStatistic;
import io.thoughtware.kanass.home.statistic.model.WorkItemStatistic;
import io.thoughtware.kanass.workitem.model.WorkType;
import io.thoughtware.kanass.workitem.service.WorkTypeService;
import io.thoughtware.flow.statenode.model.StateNode;
import io.thoughtware.flow.statenode.service.StateNodeService;
import io.thoughtware.kanass.home.statistic.dao.StatisticWorkItemDao;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 事项统计服务
 */
@Service
public class StatisticWorkItemServiceImpl implements StatisticWorkItemService {

    @Autowired
    StatisticWorkItemDao statisticWorkItemDao;

    @Autowired
    StateNodeService stateNodeService;

    @Autowired
    UserService userService;

    @Autowired
    WorkTypeService workTypeService;


    @Override
    public List<WorkItemStatistic> statisticWorkItem(StatisticWorkItemQuery statisticWorkItemQuery) {
        String collectionField = statisticWorkItemQuery.getCollectionField();
        List<WorkItemStatistic> list = statisticWorkItemDao.statisticWorkItem(statisticWorkItemQuery);

        //查找事项状态信息
        if(list != null && list.size() > 0){
            for(WorkItemStatistic item:list){
                if (collectionField.equals("state")) {
                    StateNode startNode = stateNodeService.findStateNode(item.getStatisticalId());
                    item.setStatisticalTitle(startNode.getName());
                }
                if(collectionField.equals("assigner") || collectionField.equals("builder") || collectionField.equals("reporter")){
                    User user = userService.findUser(item.getStatisticalId());
                    if(user != null){
                        item.setStatisticalTitle(user.getNickname());
                    }

                }
                if(collectionField.equals("workType")){
                    WorkType workType = workTypeService.findWorkType(item.getStatisticalId());
                    item.setStatisticalTitle(workType.getName());
                }
            }
        }
        return list;
    }


    @Override
    public List<WorkItemBuildAndEndStatistic> statisticBuildAndEndWorkItem(StatisticWorkItemQuery statisticWorkItemQuery) {
        List<WorkItemBuildAndEndStatistic> list = statisticWorkItemDao.statisticBuildAndEndWorkItem(statisticWorkItemQuery);

        return list;
    }
}
