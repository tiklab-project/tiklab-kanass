package io.tiklab.teamwire.project.milestone.service;

import io.tiklab.teamwire.project.milestone.model.Milestone;
import io.tiklab.teamwire.project.milestone.model.MilestoneQuery;
import io.tiklab.teamwire.project.milestone.dao.MilestoneDao;
import io.tiklab.teamwire.project.milestone.entity.MilestoneEntity;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
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