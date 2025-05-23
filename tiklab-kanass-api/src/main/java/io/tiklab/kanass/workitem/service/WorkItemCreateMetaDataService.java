package io.tiklab.kanass.workitem.service;

import io.tiklab.kanass.workitem.model.WorkItemCreateMetaData;
import io.tiklab.kanass.workitem.model.WorkItemCreateMetaDataQuery;
import io.tiklab.kanass.workitem.model.WorkItemDetailMetaData;
import io.tiklab.kanass.workitem.model.WorkItemDetailMetaDataQuery;

public interface WorkItemCreateMetaDataService {
    /**
     * 查询创建事项元数据
     * @param query
     * @return
     */
    WorkItemCreateMetaData  findCreateMetaData(WorkItemCreateMetaDataQuery query);

    /**
     * 查询详情元数据
     * @param query
     * @return
     */
    WorkItemDetailMetaData  findDetailMetaData(WorkItemDetailMetaDataQuery query);
}
