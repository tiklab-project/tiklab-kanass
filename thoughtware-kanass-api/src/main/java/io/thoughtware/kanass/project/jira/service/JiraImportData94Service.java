package io.thoughtware.kanass.project.jira.service;

import io.thoughtware.kanass.project.project.model.Project;
import org.w3c.dom.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
* 导入第三方数据服务接口
*/
public interface JiraImportData94Service {

    String writeData(List<Element> elements, Map<String, Project> CurrentProject, Map<String, Integer> Percent);
}