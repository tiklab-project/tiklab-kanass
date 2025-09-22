package io.tiklab.kanass.project.appraised.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.project.appraised.dao.AppraisedTypeDao;
import io.tiklab.kanass.project.appraised.entity.AppraisedTypeEntity;
import io.tiklab.kanass.project.appraised.model.AppraisedType;
import io.tiklab.kanass.project.appraised.model.AppraisedTypeQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

//@Service
public class AppraisedTypeServiceImpl implements AppraisedTypeService{
    
    @Autowired
    JoinTemplate joinTemplate;
    
    @Autowired
    AppraisedTypeDao appraisedTypeDao;

    @Override
    public String createAppraisedType(@NotNull @Valid AppraisedType appraisedType) {
        AppraisedTypeEntity appraisedTypeEntity = BeanMapper.map(appraisedType, AppraisedTypeEntity.class);

        return appraisedTypeDao.createAppraisedType(appraisedTypeEntity);
    }

    @Override
    public void updateAppraisedType(@NotNull @Valid AppraisedType appraisedType) {
        AppraisedTypeEntity appraisedTypeEntity = BeanMapper.map(appraisedType, AppraisedTypeEntity.class);

        appraisedTypeDao.updateAppraisedType(appraisedTypeEntity);
    }

    @Override
    public void deleteAppraisedType(@NotNull String id) {
        appraisedTypeDao.deleteAppraisedType(id);
    }

    @Override
    public AppraisedType findOne(String id) {
        AppraisedTypeEntity appraisedTypeEntity = appraisedTypeDao.findAppraisedType(id);

        AppraisedType appraisedType = BeanMapper.map(appraisedTypeEntity, AppraisedType.class);
        return appraisedType;
    }

    @Override
    public List<AppraisedType> findList(List<String> idList) {
        List<AppraisedTypeEntity> appraisedTypeEntityList =  appraisedTypeDao.findAppraisedTypeList(idList);

        List<AppraisedType> appraisedTypeList =  BeanMapper.mapList(appraisedTypeEntityList,AppraisedType.class);
        return appraisedTypeList;
    }

    @Override
    public AppraisedType findAppraisedType(@NotNull String id) {
        AppraisedType appraisedType = findOne(id);

        joinTemplate.joinQuery(appraisedType);
        return appraisedType;
    }

    @Override
    public List<AppraisedType> findAllAppraisedType() {
        List<AppraisedTypeEntity> appraisedTypeEntityList =  appraisedTypeDao.findAllAppraisedType();

        List<AppraisedType> appraisedTypeList =  BeanMapper.mapList(appraisedTypeEntityList,AppraisedType.class);

        joinTemplate.joinQuery(appraisedTypeList);
        return appraisedTypeList;
    }

    @Override
    public List<AppraisedType> findAppraisedTypeList(AppraisedTypeQuery appraisedTypeQuery) {
        List<AppraisedTypeEntity> appraisedTypeEntityList = appraisedTypeDao.findAppraisedTypeList(appraisedTypeQuery);

        List<AppraisedType> appraisedTypeList = BeanMapper.mapList(appraisedTypeEntityList,AppraisedType.class);

        joinTemplate.joinQuery(appraisedTypeList);

        return appraisedTypeList;
    }

    @Override
    public Pagination<AppraisedType> findAppraisedTypePage(AppraisedTypeQuery appraisedTypeQuery) {

        Pagination<AppraisedTypeEntity>  pagination = appraisedTypeDao.findAppraisedTypePage(appraisedTypeQuery);

        List<AppraisedType> appraisedTypeList = BeanMapper.mapList(pagination.getDataList(),AppraisedType.class);

        joinTemplate.joinQuery(appraisedTypeList);

        return PaginationBuilder.build(pagination,appraisedTypeList);
    }
}
