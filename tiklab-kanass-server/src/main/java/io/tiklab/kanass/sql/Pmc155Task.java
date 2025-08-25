package io.tiklab.kanass.sql;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.kanass.project.appraised.model.*;
import io.tiklab.kanass.project.appraised.service.AppraisedHistoryService;
import io.tiklab.kanass.project.appraised.service.AppraisedItemService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Pmc155Task implements DsmProcessTask {

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    AppraisedItemService appraisedItemService;

    @Autowired
    AppraisedHistoryService appraisedHistoryService;

    @Override
    public void execute() {
        AppraisedItemQuery appraisedQuery = new AppraisedItemQuery();
        appraisedQuery.setWorkItemAppraisedState("1");
        List<AppraisedItem> appraisedList1 = appraisedItemService.findAppraisedItemList(appraisedQuery);
        appraisedQuery = new AppraisedItemQuery();
        appraisedQuery.setWorkItemAppraisedState("2");
        List<AppraisedItem> appraisedList2 = appraisedItemService.findAppraisedItemList(appraisedQuery);
        List<AppraisedItem> appraisedList = new ArrayList<>();
        appraisedList.addAll(appraisedList1);
        appraisedList.addAll(appraisedList2);

        List<AppraisedHistory> appraisedHistoryList = appraisedHistoryService.findAll();
        List<String> historyIdList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(appraisedHistoryList)){
            for (AppraisedHistory item : appraisedHistoryList) {
                historyIdList.add(item.getAppraisedItemId());
            }
        }

        for (AppraisedItem appraised : appraisedList) {
            if (historyIdList.contains(appraised.getId())){
                continue;
            }
            AppraisedHistory appraisedHistory = new AppraisedHistory();
            appraisedHistory.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 12));
            appraisedHistory.setAppraisedItemId(appraised.getId());
            appraisedHistory.setAppraisedItemState(appraised.getAppraisedItemState());
            appraisedHistory.setCreater(appraised.getAppraised().getMaster());
            appraisedHistory.setAdvice("");
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
