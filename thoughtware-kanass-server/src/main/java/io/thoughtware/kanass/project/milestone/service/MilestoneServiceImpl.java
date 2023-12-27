package io.thoughtware.kanass.project.milestone.service;

import io.thoughtware.kanass.project.milestone.model.Milestone;
import io.thoughtware.kanass.project.milestone.model.MilestoneQuery;
import io.thoughtware.kanass.project.milestone.dao.MilestoneDao;
import io.thoughtware.kanass.project.milestone.entity.MilestoneEntity;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 里程碑服务
*/
@Service
public class MilestoneServiceImpl implements MilestoneService {

    @Autowired
    MilestoneDao milestoneDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createMilestone(@NotNull @Valid Milestone milestone) {
        MilestoneEntity milestoneEntity = BeanMapper.map(milestone, MilestoneEntity.class);

        return milestoneDao.createMilestone(milestoneEntity);
    }

    @Override
    public void updateMilestone(@NotNull @Valid Milestone milestone) {
        MilestoneEntity milestoneEntity = BeanMapper.map(milestone, MilestoneEntity.class);

        milestoneDao.updateMilestone(milestoneEntity);
    }

    @Override
    public void deleteMilestone(@NotNull String id) {
        milestoneDao.deleteMilestone(id);
    }

    @Override
    public Milestone findOne(String id) {
        MilestoneEntity milestoneEntity = milestoneDao.findMilestone(id);

        Milestone milestone = BeanMapper.map(milestoneEntity, Milestone.class);
        return milestone;
    }

    @Override
    public List<Milestone> findList(List<String> idList) {
        List<MilestoneEntity> milestoneEntityList =  milestoneDao.findMilestoneList(idList);

        List<Milestone> milestoneList =  BeanMapper.mapList(milestoneEntityList,Milestone.class);
        return milestoneList;
    }

    @Override
    public Milestone findMilestone(@NotNull String id) {
        Milestone milestone = findOne(id);

        joinTemplate.joinQuery(milestone);

        return milestone;
    }

    @Override
    public List<Milestone> findAllMilestone() {
        List<MilestoneEntity> milestoneEntityList = milestoneDao.findAllMilestone();

        List<Milestone> milestoneList =  BeanMapper.mapList(milestoneEntityList,Milestone.class);

        joinTemplate.joinQuery(milestoneList);

        return milestoneList;
    }

    @Override
    public List<Milestone> findMilestoneList(MilestoneQuery milestoneQuery) {
        List<MilestoneEntity> milestoneEntityList = milestoneDao.findMilestoneList(milestoneQuery);

        List<Milestone> milestoneList = BeanMapper.mapList(milestoneEntityList,Milestone.class);

        joinTemplate.joinQuery(milestoneList);

        return milestoneList;
    }

    @Override
    public Pagination<Milestone> findMilestonePage(MilestoneQuery milestoneQuery) {
        Pagination<MilestoneEntity>  pagination = milestoneDao.findMilestonePage(milestoneQuery);

        List<Milestone> milestoneList = BeanMapper.mapList(pagination.getDataList(),Milestone.class);

        joinTemplate.joinQuery(milestoneList);

        return PaginationBuilder.build(pagination,milestoneList);
    }
}