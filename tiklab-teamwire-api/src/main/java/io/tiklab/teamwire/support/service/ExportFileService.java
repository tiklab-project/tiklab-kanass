package io.tiklab.teamwire.support.service;

import io.tiklab.teamwire.workitem.model.WorkItemQuery;


/**
* 最近访问的服务
*/
public interface ExportFileService {

        byte[] exportWorkItemXml(WorkItemQuery workItemQuery);

}