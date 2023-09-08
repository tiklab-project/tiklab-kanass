package io.tiklab.teamwire.support.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.teamwire.support.dao.BackupsInfoDao;
import io.tiklab.teamwire.support.entity.BackupsInfoEntity;
import io.tiklab.teamwire.support.model.BackupsInfo;
import io.tiklab.teamwire.support.model.BackupsInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* BackupsInfoServiceImpl-定时任务管理
*/
@Service
@Exporter
public class BackupsInfoServiceImpl implements BackupsInfoService {

    @Autowired
    BackupsInfoDao BackupsInfoDao;


    @Autowired
    JoinTemplate joinTemplate;


    @Override
    public String createBackupsInfo(@NotNull @Valid BackupsInfo openRecord) {

        BackupsInfoEntity openRecordEntity = BeanMapper.map(openRecord, BackupsInfoEntity.class);
        String openRecordId= BackupsInfoDao.createBackupsInfo(openRecordEntity);
        return openRecordId;
    }

    @Override
    public void updateBackupsInfo(@NotNull @Valid BackupsInfo openRecord) {
        BackupsInfoEntity openRecordEntity = BeanMapper.map(openRecord, BackupsInfoEntity.class);

        BackupsInfoDao.updateBackupsInfo(openRecordEntity);
    }




    @Override
    public BackupsInfo findOneBackupsInfo(String id) {
        BackupsInfoEntity openRecordEntity = BackupsInfoDao.findBackupsInfo(id);

        BackupsInfo openRecord = BeanMapper.map(openRecordEntity, BackupsInfo.class);
        return openRecord;
    }

    @Override
    public List<BackupsInfo> findAllBackupsInfo() {
        List<BackupsInfoEntity> openRecordEntityList =  BackupsInfoDao.findAllBackupsInfo();

        List<BackupsInfo> openRecordList =  BeanMapper.mapList(openRecordEntityList, BackupsInfo.class);
        return openRecordList;
    }


    @Override
    public List<BackupsInfo> findBackupsInfoList(BackupsInfoQuery BackupsInfoQuery) {
        List<BackupsInfoEntity> openRecordEntityList = BackupsInfoDao.findBackupsInfoList(BackupsInfoQuery);

        List<BackupsInfo> openRecordList = BeanMapper.mapList(openRecordEntityList, BackupsInfo.class);

        return openRecordList;
    }

}