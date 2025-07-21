package io.tiklab.kanass.sql;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.kanass.project.appraised.entity.AppraisedHistoryEntity;
import io.tiklab.kanass.project.appraised.model.*;
import io.tiklab.kanass.project.appraised.service.AppraisedHistoryService;
import io.tiklab.kanass.project.appraised.service.AppraisedService;
import io.tiklab.kanass.project.appraised.service.AppraisedWorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class Pmc155Task implements DsmProcessTask {

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    AppraisedWorkItemService appraisedWorkItemService;

    @Autowired
    AppraisedHistoryService appraisedHistoryService;

    @Override
    public void execute() {
        AppraisedWorkItemQuery appraisedQuery = new AppraisedWorkItemQuery();
        appraisedQuery.setWorkItemAppraisedState("1");
        List<AppraisedWorkItem> appraisedList1 = appraisedWorkItemService.findAppraisedWorkItemList(appraisedQuery);
        appraisedQuery = new AppraisedWorkItemQuery();
        appraisedQuery.setWorkItemAppraisedState("2");
        List<AppraisedWorkItem> appraisedList2 = appraisedWorkItemService.findAppraisedWorkItemList(appraisedQuery);
        List<AppraisedWorkItem> appraisedList = new ArrayList<>();
        appraisedList.addAll(appraisedList1);
        appraisedList.addAll(appraisedList2);
//        String selectSql = "select pwa.id as work_appraised_id, pwa.work_item_appraised_state,pwa.advice,pa.master as creater " +
//                "from pmc_work_appraised pwa LEFT JOIN pmc_appraised pa ON pwa.appraised_id = pa.id " +
//                "where pwa.work_item_appraised_state IN ('1', '2')";

//        List<AppraisedHistoryEntity> appraisedHistoryList = jpaTemplate.getJdbcTemplate().query(selectSql, new BeanPropertyRowMapper<>(AppraisedHistoryEntity.class));
//        for (AppraisedHistoryEntity appraisedHistory : appraisedHistoryList) {
//            appraisedHistory.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 12));
////            appraisedHistory.setCreateTime(new Date());
////            appraisedHistory.setUpdateTime(new Date());
//        }

        for (AppraisedWorkItem appraised : appraisedList) {
            AppraisedHistory appraisedHistory = new AppraisedHistory();
            appraisedHistory.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 12));
            appraisedHistory.setWorkAppraised(appraised);
            appraisedHistory.setWorkItemAppraisedState(appraised.getWorkItemAppraisedState());
            appraisedHistory.setCreater(appraised.getAppraised().getMaster());
            appraisedHistory.setAdvice(appraised.getAdvice());
            appraisedHistoryService.createAppraisedHistory(appraisedHistory);
//            appraisedHistoryEntity.setCreateTime(new Date());
//            appraisedHistoryEntity.setUpdateTime(new Date());
        }



//        String insertSql = "INSERT INTO pmc_work_appraised_history " +
//                "(id, work_appraised_id, work_item_appraised_state, creater, advice, create_time, update_time)" +
//                "values (?,?,?,?,?,?,?)";
//        jpaTemplate.getJdbcTemplate().batchUpdate(insertSql, new BatchPreparedStatementSetter(){
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                AppraisedHistoryEntity entity = appraisedHistoryList.get(i);
//                ps.setString(1, entity.getId());
//                ps.setString(2, entity.getWorkAppraisedId());
//                ps.setString(3, entity.getWorkItemAppraisedState());
//                ps.setString(4, entity.getCreater());
//                ps.setString(5, entity.getAdvice());
//                ps.setDate(6, new java.sql.Date(new Date().getTime()));
//                ps.setDate(7, new java.sql.Date(new Date().getTime()));
//            }
//
//            @Override
//            public int getBatchSize() {
//                return appraisedHistoryList.size();
//            }
//        });

    }
}
