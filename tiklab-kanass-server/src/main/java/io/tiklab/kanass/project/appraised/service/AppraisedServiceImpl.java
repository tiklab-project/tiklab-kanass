package io.tiklab.kanass.project.appraised.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.project.appraised.dao.AppraisedDao;
import io.tiklab.kanass.project.appraised.entity.AppraisedEntity;
import io.tiklab.kanass.project.appraised.model.Appraised;
import io.tiklab.kanass.project.appraised.model.AppraisedQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AppraisedServiceImpl implements AppraisedService{

    @Autowired
    private AppraisedDao appraisedDao;

    @Autowired
    private JoinTemplate joinTemplate;

    @Override
    public String createAppraised(Appraised appraised) {
        String createId = LoginContext.getLoginId();
        User user = new User();
        user.setId(createId);
        appraised.setBuilder(user);

        // 设置版本头像颜色
        int color = new Random().nextInt(3) + 1;
        appraised.setColor(color);
        AppraisedEntity appraisedEntity = BeanMapper.map(appraised, AppraisedEntity.class);
        return appraisedDao.createAppraised(appraisedEntity);
    }

    @Override
    public void updateAppraised(Appraised appraised) {
        AppraisedEntity map = BeanMapper.map(appraised, AppraisedEntity.class);
        appraisedDao.updateAppraised(map);
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

        joinTemplate.joinQuery(appraised);
        return appraised;
    }

    @Override
    public List<Appraised> findAllAppraised() {
        List<AppraisedEntity> allAppraised = appraisedDao.findAllAppraised();
        List<Appraised> appraiseds = BeanMapper.mapList(allAppraised, Appraised.class);
        joinTemplate.joinQuery(appraiseds);
        return appraiseds;
    }

    @Override
    public List<Appraised> findAppraisedList(AppraisedQuery appraisedQuery) {
        List<AppraisedEntity> appraisedList = appraisedDao.findAppraisedList(appraisedQuery);

        List<Appraised> mapList = BeanMapper.mapList(appraisedList, Appraised.class);

        joinTemplate.joinQuery(mapList);
        return mapList;
    }

    @Override
    public Pagination<Appraised> findAppraisedPage(AppraisedQuery appraisedQuery) {
        Pagination<AppraisedEntity> appraisedPage = appraisedDao.findAppraisedPage(appraisedQuery);
        List<Appraised> mapList = BeanMapper.mapList(appraisedPage.getDataList(), Appraised.class);

        joinTemplate.joinQuery(mapList);
        return PaginationBuilder.build(appraisedPage, mapList);
    }
}
