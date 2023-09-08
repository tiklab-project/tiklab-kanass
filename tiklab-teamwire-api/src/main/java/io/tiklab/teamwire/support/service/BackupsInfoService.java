package io.tiklab.teamwire.support.service;

import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teamwire.support.model.BackupsInfo;
import io.tiklab.teamwire.support.model.BackupsInfoQuery;

import java.util.List;

@JoinProvider(model = BackupsInfo.class)
public interface BackupsInfoService {

    /**
     * 创建定时任务
     * @param BackupsInfo
     */
    String createBackupsInfo(BackupsInfo BackupsInfo);

    
    /**
     * 更新定时任务
     * @param BackupsInfo 
     */
    void updateBackupsInfo(BackupsInfo BackupsInfo);

    /**
     * 查询单个定时任务
     * @param BackupsInfoId
     */
    BackupsInfo findOneBackupsInfo(String BackupsInfoId);

    /**
     * 查询所有定时任务
     */
    List<BackupsInfo> findAllBackupsInfo();

    /**
     * 条件查询定时任务
     * @param BackupsInfoQuery BackupsInfoQuery
     */
    List<BackupsInfo> findBackupsInfoList(BackupsInfoQuery BackupsInfoQuery);

}




















































