package io.tiklab.kanass.sql.service;

import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.kanass.project.appraised.model.AppraisedHistory;
import io.tiklab.kanass.project.appraised.model.AppraisedItem;
import io.tiklab.kanass.project.appraised.service.AppraisedHistoryService;
import io.tiklab.kanass.project.appraised.service.AppraisedItemService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Pmc163Task implements DsmProcessTask {

    @Autowired
    private AppraisedItemService appraisedItemService;

    @Autowired
    private AppraisedHistoryService appraisedHistoryService;

    @Override
    public void execute() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<AppraisedItem> appraisedItemList = appraisedItemService.findAll();
        List<AppraisedHistory> historyList = appraisedHistoryService.findAll();
        Map<String, List<AppraisedHistory>> historyMap = historyList.stream()
                .collect(Collectors.groupingBy(
                        AppraisedHistory::getAppraisedItemId,
                        // 对每个分组内的列表按 createTime 升序排序
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(AppraisedHistory::getCreateTime))
                                        .collect(Collectors.toList())
                        )
                ));
        for (AppraisedItem appraisedItem : appraisedItemList) {
            String appraisedItemId = appraisedItem.getId();
            List<AppraisedHistory> historyList1 = historyMap.get(appraisedItemId);
            if (CollectionUtils.isNotEmpty(historyList1)){
                Date createTime1 = historyList1.get(0).getCreateTime();
                Date date1 = new Date(createTime1.getTime());
                Date updateTime1 = historyList1.get(historyList1.size() - 1).getUpdateTime();
                Date date2 = new Date(updateTime1.getTime());
                appraisedItem.setCreateTime(new Timestamp(date1.getTime()));
                appraisedItem.setUpdateTime(new Timestamp(date2.getTime()));
            }else {
                Date date = new Date();
                appraisedItem.setCreateTime(new Timestamp(date.getTime()));
                appraisedItem.setUpdateTime(new Timestamp(date.getTime()));
            }
            appraisedItemService.updateAppraisedItem(appraisedItem);
        }
    }
}
