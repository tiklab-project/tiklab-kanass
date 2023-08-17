package io.tiklab.teamwire.workitem.service;

import io.tiklab.teamwire.workitem.model.WorkAttach;
import io.tiklab.teamwire.workitem.model.WorkAttachQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teamwire.workitem.dao.WorkAttachDao;
import io.tiklab.teamwire.workitem.entity.WorkAttachEntity;
import org.apache.commons.compress.harmony.pack200.NewAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项附件服务接口
*/
@Service
public class WorkAttachServiceImpl implements WorkAttachService {

    @Autowired
    WorkAttachDao workAttachDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Value("${dfs.client.group:null}")
    String dfsGroup;

    @Value("${dfs.client.bucket:null}")
    String dfsBucket;
    @Override
    public String createWorkAttach(@NotNull @Valid WorkAttach workAttach) {
        WorkAttachEntity workAttachEntity = BeanMapper.map(workAttach, WorkAttachEntity.class);

        return workAttachDao.createWorkAttach(workAttachEntity);
    }

    @Override
    public void updateWorkAttach(@NotNull @Valid WorkAttach workAttach) {
        WorkAttachEntity workAttachEntity = BeanMapper.map(workAttach, WorkAttachEntity.class);

        workAttachDao.updateWorkAttach(workAttachEntity);
    }

    @Override
    public void deleteWorkAttach(@NotNull String id) {
        workAttachDao.deleteWorkAttach(id);
    }

    @Override
    public WorkAttach findWorkAttach(@NotNull String id) {
        WorkAttachEntity workAttachEntity = workAttachDao.findWorkAttach(id);

        WorkAttach workAttach = BeanMapper.map(workAttachEntity, WorkAttach.class);

        joinTemplate.joinQuery(workAttach);

        return workAttach;
    }

    @Override
    public List<WorkAttach> findAllWorkAttach() {
        List<WorkAttachEntity> workAttachEntityList =  workAttachDao.findAllWorkAttach();

        List<WorkAttach> workAttachList = BeanMapper.mapList(workAttachEntityList,WorkAttach.class);

        joinTemplate.joinQuery(workAttachList);

        return workAttachList;
    }

    @Override
    public List<WorkAttach> findWorkAttachList(WorkAttachQuery workAttachQuery) {
        List<WorkAttachEntity> workAttachEntityList = workAttachDao.findWorkAttachList(workAttachQuery);

        List<WorkAttach> workAttachList = BeanMapper.mapList(workAttachEntityList,WorkAttach.class);

        joinTemplate.joinQuery(workAttachList);

        return workAttachList;
    }

    @Override
    public Pagination<WorkAttach> findWorkAttachPage(WorkAttachQuery workAttachQuery) {

        Pagination<WorkAttachEntity>  pagination = workAttachDao.findWorkAttachPage(workAttachQuery);

        List<WorkAttach> workAttachList = BeanMapper.mapList(pagination.getDataList(),WorkAttach.class);

        joinTemplate.joinQuery(workAttachList);

        return PaginationBuilder.build(pagination,workAttachList);
    }
}