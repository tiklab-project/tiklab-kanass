package io.tiklab.kanass.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.instance.entity.TestReportEntity;
import io.tiklab.kanass.instance.model.TestReportQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试报告 数据访问
 */
@Repository
public class TestReportDao {

    private static Logger logger = LoggerFactory.getLogger(TestReportDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建测试报告
     * @param testReportEntity
     * @return
     */
    public String createTestReport(TestReportEntity testReportEntity) {
        return jpaTemplate.save(testReportEntity,String.class);
    }

    /**
     * 更新测试报告
     * @param testReportEntity
     */
    public void updateTestReport(TestReportEntity testReportEntity){
        jpaTemplate.update(testReportEntity);
    }

    /**
     * 删除测试报告
     * @param id
     */
    public void deleteTestReport(String id){
        jpaTemplate.delete(TestReportEntity.class,id);
    }

    /**
     * 批量删除测试报告
     * @param deleteCondition
     */
    public void deleteAllCategory(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }


    /**
     * 根据id查找测试报告
     * @param id
     * @return
     */
    public TestReportEntity findTestReport(String id){
        return jpaTemplate.findOne(TestReportEntity.class,id);
    }
    

    /**
    * 查找所有测试报告
    * @return
    */
    public List<TestReportEntity> findAllTestReport() {
        return jpaTemplate.findAll(TestReportEntity.class);
    }

    public List<TestReportEntity> findTestReportList(List<String> idList) {
        return jpaTemplate.findList(TestReportEntity.class,idList);
    }

    /**
     * 查询测试报告列表
     * @param testReportQuery
     * @return
     */
    public List<TestReportEntity> findTestReportList(TestReportQuery testReportQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestReportEntity.class)
                .eq("projectId", testReportQuery.getProjectId())
                .eq("testPlanId",  testReportQuery.getTestPlanId())
                .like("name", testReportQuery.getName())
                .orders(testReportQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, TestReportEntity.class);
    }

    /**
     * 按分页查询测试报告
     * @param testReportQuery
     * @return
     */
    public Pagination<TestReportEntity> findTestReportPage(TestReportQuery testReportQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestReportEntity.class)
                .eq("projectId", testReportQuery.getProjectId())
                .eq("testPlanId",  testReportQuery.getTestPlanId())
                .like("name", testReportQuery.getName())
                .pagination(testReportQuery.getPageParam())
                .orders(testReportQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, TestReportEntity.class);
    }


}