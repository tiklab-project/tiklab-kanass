package io.tiklab.kanass.project.jira.service;

import io.tiklab.kanass.project.project.model.Project;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Map;

/**
* 导入第三方数据服务接口
*/
public interface JiraImportDataCloudService {


    String writeData(List<Element> elements, Map<String, Project> CurrentProject, Map<String, Integer> Percent);

}