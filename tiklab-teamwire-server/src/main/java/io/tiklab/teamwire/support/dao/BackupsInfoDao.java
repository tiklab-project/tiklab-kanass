package io.tiklab.teamwire.support.dao;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teamwire.support.entity.BackupsInfoEntity;
import io.tiklab.teamwire.support.model.BackupsInfoQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BackupsInfoDao-定时任务管理
 */
@Repository
public class BackupsInfoDao {

    private static Logger logger = LoggerFactory.getLogger(BackupsInfoDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param BackupsInfoEntity
     * @return
     */
    public String createBackupsInfo(BackupsInfoEntity BackupsInfoEntity) {
        return jpaTemplate.save(BackupsInfoEntity,String.class);
    }

    /**
     * 更新
     * @param BackupsInfoEntity
     */
    public void updateBackupsInfo(BackupsInfoEntity BackupsInfoEntity){
        jpaTemplate.update(BackupsInfoEntity);
    }



    /**
     * 查找
     * @param id
     * @return
     */
    public BackupsInfoEntity findBackupsInfo(String id){
        return jpaTemplate.findOne(BackupsInfoEntity.class,id);
    }

    /**
    * findAllBackupsInfo
    * @return
    */
    public List<BackupsInfoEntity> findAllBackupsInfo() {
        return jpaTemplate.findAll(BackupsInfoEntity.class);
    }



    /**
     * 条件查询插件
     * @param BackupsInfoQuery
     * @return
     */
    public List<BackupsInfoEntity> findBackupsInfoList(BackupsInfoQuery BackupsInfoQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(BackupsInfoEntity.class)
                .eq("type", BackupsInfoQuery.getType())
                .get();
        return jpaTemplate.findList(queryCondition, BackupsInfoEntity.class);
    }

}