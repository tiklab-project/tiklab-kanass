package io.thoughtware.kanass.project.version.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.kanass.project.version.entity.ProjectVersionEntity;
import io.thoughtware.kanass.project.version.entity.VersionFocusEntity;
import io.thoughtware.kanass.project.version.model.VersionFocusQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 迭代收藏数据访问
 */
@Repository
public class VersionFocusDao {

    private static Logger logger = LoggerFactory.getLogger(VersionFocusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建迭代收藏
     * @param versionFocusEntity
     * @return
     */
    public String createVersionFocus(VersionFocusEntity versionFocusEntity) {
        return jpaTemplate.save(versionFocusEntity,String.class);
    }

    /**
     * 更新收藏的迭代
     * @param versionFocusEntity
     */
    public void updateVersionFocus(VersionFocusEntity versionFocusEntity){
        jpaTemplate.update(versionFocusEntity);
    }

    /**
     * 删除收藏的迭代
     * @param id
     */
    public void deleteVersionFocus(String id){
        jpaTemplate.delete(VersionFocusEntity.class,id);
    }

    /**
     * 根据添加删除收藏的迭代
     * @param deleteCondition
     */
    public void deleteVersionFocus(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找收藏的迭代
     * @param id
     * @return
     */
    public VersionFocusEntity findVersionFocus(String id){
        return jpaTemplate.findOne(VersionFocusEntity.class,id);
    }

    /**
    * 查找所有收藏迭代
    * @return
    */
    public List<VersionFocusEntity> findAllVersionFocus() {
        return jpaTemplate.findAll(VersionFocusEntity.class);
    }

    /**
     * 根据多个迭代id,查找收藏的迭代
     * @param idList
     * @return
     */
    public List<VersionFocusEntity> findVersionFocusList(List<String> idList) {
        return jpaTemplate.findList(VersionFocusEntity.class,idList);
    }

    /**
     * 根据条件查询收藏的迭代列表
     * @param versionFocusQuery
     * @return
     */
    public List<VersionFocusEntity> findVersionFocusList(VersionFocusQuery versionFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(VersionFocusEntity.class, "vf")
                .leftJoin(ProjectVersionEntity.class,"pv","pv.id=vf.versionId")
                .eq("vf.masterId", versionFocusQuery.getMasterId ())
                .eq("vf.versionId", versionFocusQuery.getVersionId ())
                .eq("vf.projectId", versionFocusQuery.getProjectId())
                .orders(versionFocusQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, VersionFocusEntity.class);
    }

    /**
     * 根据条件按分页查询收藏的迭代列表
     * @param versionFocusQuery
     * @return
     */
    public Pagination<VersionFocusEntity> findVersionFocusPage(VersionFocusQuery versionFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(VersionFocusEntity.class, "vf")
                .leftJoin(ProjectVersionEntity.class,"pv","pv.id=vf.versionId")
                .eq("vf.masterId", versionFocusQuery.getMasterId ())
                .eq("vf.projectId", versionFocusQuery.getProjectId())
                .orders(versionFocusQuery.getOrderParams())
                .pagination(versionFocusQuery.getPageParam())
                .get();

        return jpaTemplate.findPage(queryCondition,VersionFocusEntity.class);
    }

    public List<String> findFocusVersionIds(String masterId) {
        String sql = "select version_id from pmc_version_focus v where v.master_id = '"+ masterId + "'";
        List<String> versionIds = this.jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        return versionIds;
    }

}