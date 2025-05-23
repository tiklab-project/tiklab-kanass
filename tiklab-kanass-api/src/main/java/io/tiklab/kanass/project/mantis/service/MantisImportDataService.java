package io.tiklab.kanass.project.mantis.service;

import java.io.InputStream;
import java.util.Map;

public interface MantisImportDataService {

    /**
     * 导入mantis的数据
     * @param inputStream
     * @return
     */
    void importMantisData(InputStream inputStream);

    /**
     * 查询导入状态
     * @return
     */
    Map<String, Object> findMantisInputSchedule();
}
