package io.tiklab.kanass.project.appraised.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.common.SendMessageUtil;
import io.tiklab.kanass.product.product.model.Product;
import io.tiklab.kanass.project.appraised.dao.AppraisedDao;
import io.tiklab.kanass.project.appraised.entity.AppraisedEntity;
import io.tiklab.kanass.project.appraised.model.Appraised;
import io.tiklab.kanass.project.appraised.model.AppraisedQuery;
import io.tiklab.kanass.project.appraised.model.AppraisedItem;
import io.tiklab.kanass.project.appraised.model.AppraisedItemQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@Service
public class AppraisedServiceImpl implements AppraisedService{
    public final ExecutorService executorService = Executors.newCachedThreadPool();
    @Autowired
    private AppraisedDao appraisedDao;

    @Autowired
    private AppraisedItemService appraisedItemService;

    @Autowired
    private JoinTemplate joinTemplate;

    @Autowired
    private SendMessageUtil sendMessageUtil;

    @Autowired
    private UserProcessor userProcessor;

    // 创建产品
    void sendCreateAppraisedMessage(Appraised appraised){
        HashMap<String, Object> content = new HashMap<>();
        content.put("appraisedName", appraised.getName());
        content.put("appraisedId", appraised.getId());
        content.put("projectId", appraised.getProject().getId());
        String createUserId = LoginContext.getLoginId();
        User user = userProcessor.findOne(createUserId);
        content.put("creater", user.getNickname());
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));

        content.put("link", "/#/project/${projectId}/appraised/${appraisedId}");
        content.put("action", "创建评审");
        content.put("noticeId", "KANASS_APPRAISED_CREATE");

        sendMessageUtil.sendDomainMessage(content, appraised.getProject().getId());
    }

    // 更新产品
    void sendUpdateAppraisedMessage(Appraised appraised){
        HashMap<String, Object> content = new HashMap<>();
        content.put("appraisedName", appraised.getName());
        content.put("appraisedId", appraised.getId());
        content.put("projectId", appraised.getProject().getId());
        String createUserId = LoginContext.getLoginId();
        User user = userProcessor.findOne(createUserId);
        content.put("creater", user.getNickname());
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));

        content.put("link", "/#/project/${projectId}/appraised/${appraisedId}");
        content.put("action", "创建评审");
        content.put("noticeId", "KANASS_APPRAISED_UPDATE");

        sendMessageUtil.sendDomainMessage(content, appraised.getProject().getId());
    }

    @Override
    public String createAppraised(Appraised appraised) {
        String createId = LoginContext.getLoginId();
        User user = new User();
        user.setId(createId);
        appraised.setBuilder(user);

        // 设置版本头像颜色
        int color = new Random().nextInt(3) + 1;
        appraised.setColor(color);

        Date nowDate = new Date();
        appraised.setCreateTime(new Timestamp(nowDate.getTime()));
        appraised.setUpdateTime(new Timestamp(nowDate.getTime()));

        AppraisedEntity appraisedEntity = BeanMapper.map(appraised, AppraisedEntity.class);
        String appraisedId = appraisedDao.createAppraised(appraisedEntity);
        appraised.setId(appraisedId);
        executorService.submit(() -> {
            sendCreateAppraisedMessage(appraised);
        });
        return appraisedId;
    }

    @Override
    public void updateAppraised(Appraised appraised) {
        AppraisedEntity map = BeanMapper.map(appraised, AppraisedEntity.class);
        appraisedDao.updateAppraised(map);

        Appraised newAppraised = findAppraised(appraised.getId());
        executorService.submit(() -> {
            sendUpdateAppraisedMessage(newAppraised);
        });
    }

    @Override
    public void deleteAppraised(String id) {
        appraisedDao.deleteAppraised(id);
    }

    @Override
    public Appraised findOne(String id) {
        AppraisedEntity appraisedEntity = appraisedDao.findAppraised(id);
        Appraised appraised = BeanMapper.map(appraisedEntity, Appraised.class);
        return appraised;
    }

    @Override
    public List<Appraised> findList(List<String> idList) {
        List<AppraisedEntity> appraisedEntityList = appraisedDao.findAppraisedList(idList);
        List<Appraised> appraisedList = BeanMapper.mapList(appraisedEntityList, Appraised.class);
        return appraisedList;
    }

    @Override
    public Appraised findAppraised(String id) {
        Appraised appraised = findOne(id);

        AppraisedItemQuery workItemQuery = new AppraisedItemQuery();
        workItemQuery.setAppraisedId(id);
        List<AppraisedItem> appraisedItemList = appraisedItemService.findAppraisedItemList(workItemQuery);
        appraised.setAllAppraisedWorkItemNumber(appraisedItemList.size());
        appraised.setUnPassAppraisedWorkItemNumber(0);
        appraised.setPassAppraisedWorkItemNumber(0);
        for (AppraisedItem appraisedItem : appraisedItemList) {
            if ("1".equals(appraisedItem.getAppraisedItemState())) {
                appraised.setPassAppraisedWorkItemNumber(appraised.getPassAppraisedWorkItemNumber() + 1);
            } else if ("2".equals(appraisedItem.getAppraisedItemState())){
                appraised.setUnPassAppraisedWorkItemNumber(appraised.getUnPassAppraisedWorkItemNumber() + 1);
            }
        }
        joinTemplate.joinQuery(appraised, new String[]{"master", "builder", "project", "stage", "appraisedType"});
        return appraised;
    }

    @Override
    public List<Appraised> findAllAppraised() {
        List<AppraisedEntity> allAppraised = appraisedDao.findAllAppraised();
        List<Appraised> appraiseds = BeanMapper.mapList(allAppraised, Appraised.class);
        joinTemplate.joinQuery(appraiseds, new String[]{"master", "builder", "project", "stage", "appraisedType"});
        return appraiseds;
    }

    @Override
    public List<Appraised> findAppraisedList(AppraisedQuery appraisedQuery) {
        List<AppraisedEntity> appraisedList = appraisedDao.findAppraisedList(appraisedQuery);

        List<Appraised> mapList = BeanMapper.mapList(appraisedList, Appraised.class);

        joinTemplate.joinQuery(mapList, new String[]{"master", "builder", "project", "stage", "appraisedType"});
        return mapList;
    }

    @Override
    public Pagination<Appraised> findAppraisedPage(AppraisedQuery appraisedQuery) {
        Pagination<AppraisedEntity> appraisedPage = appraisedDao.findAppraisedPage(appraisedQuery);
        List<Appraised> mapList = BeanMapper.mapList(appraisedPage.getDataList(), Appraised.class);

        joinTemplate.joinQuery(mapList, new String[]{"master", "builder", "project", "stage", "appraisedType"});
        return PaginationBuilder.build(appraisedPage, mapList);
    }
}
