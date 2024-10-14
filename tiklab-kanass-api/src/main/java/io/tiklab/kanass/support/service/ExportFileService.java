package io.tiklab.kanass.support.service;

import io.tiklab.kanass.workitem.model.WorkItemQuery;


/**
* 最近访问的服务
*/
public interface ExportFileService {

        byte[] exportWorkItemXml(WorkItemQuery workItemQuery);

}