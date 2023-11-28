package io.tiklab.teamwire.home.statistic.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.teamwire.home.statistic.model.Report;
import io.tiklab.teamwire.home.statistic.model.ReportQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 报表服务接口
*/
public interface ReportService {

    /**
    * 创建报表
    * @param report
    * @return
    */
    String createReport(@NotNull @Valid Report report);

    /**
    * 更新报表
    * @param report
    */
    void updateReport(@NotNull @Valid Report report);

    /**
    * 删除报表
    * @param id
    */
    void deleteReport(@NotNull String id);

    Report findOne(@NotNull String id);

    List<Report> findList(List<String> idList);

    /**
    * 按照id查找报表
    * @param id
    * @return
    */
    Report findReport(@NotNull String id);

    /**
    * 查找所有报表
    * @return
    */
    List<Report> findAllReport();

    /**
    * 按条件查询报表列表
    * @param reportQuery
    * @return
    */
    List<Report> findReportList(ReportQuery reportQuery);

    /**
    * 按条件分页查询报表列表
    * @param reportQuery
    * @return
    */
    Pagination<Report> findReportPage(ReportQuery reportQuery);

}