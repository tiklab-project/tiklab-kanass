package io.thoughtware.kanass.support.service;

import io.thoughtware.kanass.workitem.model.WorkItemQuery;


/**
* 最近访问的服务
*/
public interface ExportFileService {

        byte[] exportWorkItemXml(WorkItemQuery workItemQuery);

}