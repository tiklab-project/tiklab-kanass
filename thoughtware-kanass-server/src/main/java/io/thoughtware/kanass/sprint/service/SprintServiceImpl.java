package io.thoughtware.kanass.sprint.service;

import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.kanass.sprint.model.Sprint;
import io.thoughtware.kanass.sprint.model.SprintQuery;
import io.thoughtware.kanass.sprint.model.SprintState;
import io.thoughtware.kanass.sprint.model.SprintStateQuery;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.kanass.sprint.entity.SprintStateEntity;
import io.thoughtware.kanass.sprint.dao.SprintDao;
import io.thoughtware.kanass.sprint.dao.SprintStateDao;
import io.thoughtware.kanass.sprint.entity.SprintEntity;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.model.WorkItemQuery;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.model.DmUserQuery;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* 迭代服务
*/
@Service
public class SprintServiceImpl implements SprintService {

    @Autowired
    SprintDao sprintDao;

    @Autowired
    SprintStateDao sprintStateDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @Autowired
    WorkItemService workItemService;

    /**
     * 添加动态
     */
    void creatDynamic(String id,String type){
        Sprint sprint = findOne(id);
        String createUserId = LoginContext.getLoginId();
        Project project = projectService.findOne(sprint.getProject().getId());
        User user = userService.findOne(createUserId);
        Date newDate = new Date();

        //创建动态
//        Dynamic dynamic = new Dynamic();
//        dynamic.setDynamicType(type);
//        dynamic.setName(sprint.getSprintName());
//        dynamic.setModelId(sprint.getId());
//        dynamic.setUser(user);
//        dynamic.setDynamicTime(newDate);
//        dynamic.setProject(project);
//        dynamic.setModel("sprint");
//        dynamicService.createDynamic(dynamic);
    }

    @Override
    public String createSprint(@NotNull @Valid Sprint sprint) {
        //初始化迭代状态
        SprintState sprintState = new SprintState();
        sprintState.setId("000000");
        sprint.setSprintState(sprintState);

        SprintEntity sprintEntity = BeanMapper.map(sprint, SprintEntity.class);
        String id = sprintDao.createSprint(sprintEntity);
        return id;
    }


    @Override
    public void updateSprint(@NotNull @Valid Sprint sprint) {
        SprintEntity sprintEntity = BeanMapper.map(sprint, SprintEntity.class);

        sprintDao.updateSprint(sprintEntity);

    }

    @Override
    public void deleteSprint(@NotNull String id) {
        sprintDao.deleteSprint(id);

        // 删除关注的迭代数据
        sprintDao.deleteSprintFocus(id);

    }

    @Override
    public Sprint findOne(String id) {
        SprintEntity sprintEntity = sprintDao.findSprint(id);

        Sprint sprint = BeanMapper.map(sprintEntity, Sprint.class);
        return sprint;
    }

    @Override
    public List<Sprint> findList(List<String> idList) {
        List<SprintEntity> sprintEntityList = sprintDao.findSprintList(idList);

        List<Sprint> sprintList = BeanMapper.mapList(sprintEntityList, Sprint.class);

        return sprintList;
    }

    @Override
    public Sprint findSprint(@NotNull String id) {
        Sprint sprint = findOne(id);

        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setSprintId(id);
        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);


        sprint.setWorkNumber(workItemList.size());

        joinTemplate.joinQuery(sprint);
        return sprint;
    }

    @Override
    public List<Sprint> findAllSprint() {
        List<SprintEntity> sprintEntityList = sprintDao.findAllSprint();

        List<Sprint> sprintList = BeanMapper.mapList(sprintEntityList, Sprint.class);

        joinTemplate.joinQuery(sprintList);
        return sprintList;
    }

    @Override
    public List<Sprint> findSprintList(SprintQuery sprintQuery) {
        List<SprintEntity> sprintEntityList = sprintDao.findSprintList(sprintQuery);
        List<Sprint> sprintList = BeanMapper.mapList(sprintEntityList, Sprint.class);
        if(sprintList.size() > 0){
            String sprintIds = "(" + sprintEntityList.stream().map(item -> "'" + item.getId() + "'").
                    collect(Collectors.joining(", ")) + ")";
            List<Map<String, Object>> sprintCount = workItemService.findWorkItemNum("sprint_id", sprintIds);
            for (Sprint sprint : sprintList) {
                String id = sprint.getId();
                List<Map<String, Object>> countList = sprintCount.stream().filter(item -> item.get("sprint_id").equals(id)).collect(Collectors.toList());
                sprint.setWorkNumber(countList.size());
            }
        }
        // 查找迭代的事项数量



        joinTemplate.joinQuery(sprintList);
        return sprintList;
    }

    @Override
    public Pagination<Sprint> findSprintPage(SprintQuery sprintQuery) {

        Pagination<SprintEntity> pagination = sprintDao.findSprintPage(sprintQuery);

        List<Sprint> sprintList = BeanMapper.mapList(pagination.getDataList(), Sprint.class);

        joinTemplate.joinQuery(sprintList);

        return PaginationBuilder.build(pagination,sprintList);
    }

    @Override
    public List<Sprint> findFocusSprintList(SprintQuery sprintQuery) {

        List<SprintEntity> sprintEntityList = sprintDao.findFocusSprintList(sprintQuery);

        List<Sprint> sprintList = BeanMapper.mapList(sprintEntityList, Sprint.class);

        joinTemplate.joinQuery(sprintList);
        return sprintList;
    }

    /**
     * 查询请迭代状态
     *
     * @param sprintState
     */
    public List<SprintStateEntity> findSprintState(SprintState sprintState) {
        SprintStateQuery sprintStateQuery = new SprintStateQuery();
        sprintStateQuery.setSort(sprintState.getSort());
        return sprintStateDao.findSprintStateList(sprintStateQuery);
    }

    @Override
    public List<Sprint> findProgressSprint() {
        List<SprintEntity> sprintEntityList = sprintDao.findProgressSprint();

        List<Sprint> sprintList = BeanMapper.mapList(sprintEntityList, Sprint.class);

        joinTemplate.joinQuery(sprintList);
        return sprintList;
    }
}