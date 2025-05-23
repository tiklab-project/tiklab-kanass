package io.tiklab.kanass.project.mantis.service;

import io.tiklab.kanass.project.mantis.model.MantisIssue;
import io.tiklab.kanass.project.project.model.Project;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Map;

/**
 * 导入2.27.1版本的mantis数据
 */
public interface Mantis2271ImportDataService {

    String writeData(List<MantisIssue> elements, Map<String, String> Steps, Map<String, Integer> Percent) throws InterruptedException;
}
