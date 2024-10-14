package io.tiklab.kanass.home.statistic.dao;

import io.tiklab.kanass.home.statistic.entity.ReportEntity;
import io.tiklab.kanass.home.statistic.model.ReportQuery;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 报表数据访问
 */
@Repository
public class ReportDao{

    private static Logger logger = LoggerFactory.getLogger(ReportDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建报表
     * @param reportEntity
     * @return
     */
    public String createReport(ReportEntity reportEntity) {
        return jpaTemplate.save(reportEntity,String.class);
    }

    /**
     * 更新报表
     * @param reportEntity
     */
    public void updateReport(ReportEntity reportEntity){
        jpaTemplate.update(reportEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteReport(String id){
        jpaTemplate.delete(ReportEntity.class,id);
    }

    public void deleteReport(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找报表
     * @param id
     * @return
     */
    public ReportEntity findReport(String id){
        return jpaTemplate.findOne(ReportEntity.class,id);
    }

    /**
    * 查找全部报表
    * @return
    */
    public List<ReportEntity> findAllReport() {
        return jpaTemplate.findAll(ReportEntity.class);
    }

    public List<ReportEntity> findReportList(List<String> idList) {
        return jpaTemplate.findList(ReportEntity.class,idList);
    }

    /**
     * 根据搜索条件查找报表列表
     * @param reportQuery
     * @return
     */
    public List<ReportEntity> findReportList(ReportQuery reportQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ReportEntity.class)
                .eq("projectId", reportQuery.getProjectId())
                .eq("programId", reportQuery.getProgramId())
                .eq("sprintId", reportQuery.getSprintId())
                .eq("reportType", reportQuery.getReportType())
                .eq("type", reportQuery.getType())
                .orders(reportQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ReportEntity.class);
    }

    /**
     * 根据搜索条件按分页查找报表列表
     * @param reportQuery
     * @return
     */
    public Pagination<ReportEntity> findReportPage(ReportQuery reportQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ReportEntity.class)
                .eq("projectId", reportQuery.getProjectId())
                .eq("sprintId", reportQuery.getSprintId())
                .orders(reportQuery.getOrderParams())
                .pagination(reportQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ReportEntity.class);
    }
}