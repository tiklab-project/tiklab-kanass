package io.tiklab.teamwire.home.statistic.service;

import io.tiklab.teamwire.home.statistic.model.StatisticWorkItemQuery;
import io.tiklab.teamwire.home.statistic.model.WorkItemBuildAndEndStatistic;
import io.tiklab.teamwire.home.statistic.model.WorkItemStatistic;
import io.tiklab.teamwire.workitem.model.WorkType;
import io.tiklab.teamwire.workitem.service.WorkTypeService;
import io.tiklab.flow.statenode.model.StateNode;
import io.tiklab.flow.statenode.service.StateNodeService;
import io.tiklab.teamwire.home.statistic.dao.StatisticWorkItemDao;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
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
