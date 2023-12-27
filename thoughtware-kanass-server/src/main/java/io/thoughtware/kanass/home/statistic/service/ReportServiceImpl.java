package io.thoughtware.kanass.home.statistic.service;

import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.kanass.home.statistic.model.Report;
import io.thoughtware.kanass.home.statistic.model.ReportQuery;
import io.thoughtware.kanass.home.statistic.dao.ReportDao;
import io.thoughtware.kanass.home.statistic.entity.ReportEntity;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 报表服务
*/
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportDao reportDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createReport(@NotNull @Valid Report report) {
        report.setCreatTime(new Timestamp(System.currentTimeMillis()));

        String createUserId = LoginContext.getLoginId();
        User user = new User();
        user.setId(createUserId);
        report.setCreater(user);

        ReportEntity reportEntity = BeanMapper.map(report, ReportEntity.class);

        return reportDao.createReport(reportEntity);
    }

    @Override
    public void updateReport(@NotNull @Valid Report report) {
        ReportEntity reportEntity = BeanMapper.map(report, ReportEntity.class);

        reportDao.updateReport(reportEntity);
    }

    @Override
    public void deleteReport(@NotNull String id) {
        reportDao.deleteReport(id);
    }

    @Override
    public Report findOne(String id) {
        ReportEntity reportEntity = reportDao.findReport(id);

        Report report = BeanMapper.map(reportEntity, Report.class);
        return report;
    }

    @Override
    public List<Report> findList(List<String> idList) {
        List<ReportEntity> reportEntityList =  reportDao.findReportList(idList);

        List<Report> reportList =  BeanMapper.mapList(reportEntityList,Report.class);
        return reportList;
    }

    @Override
    public Report findReport(@NotNull String id) {
        Report report = findOne(id);

        joinTemplate.joinQuery(report);

        return report;
    }

    @Override
    public List<Report> findAllReport() {
        List<ReportEntity> reportEntityList =  reportDao.findAllReport();

        List<Report> reportList =  BeanMapper.mapList(reportEntityList,Report.class);

        joinTemplate.joinQuery(reportList);

        return reportList;
    }

    @Override
    public List<Report> findReportList(ReportQuery reportQuery) {
        List<ReportEntity> reportEntityList = reportDao.findReportList(reportQuery);

        List<Report> reportList = BeanMapper.mapList(reportEntityList,Report.class);

        joinTemplate.joinQuery(reportList);

        return reportList;
    }

    @Override
    public Pagination<Report> findReportPage(ReportQuery reportQuery) {
        Pagination<ReportEntity>  pagination = reportDao.findReportPage(reportQuery);

        List<Report> reportList = BeanMapper.mapList(pagination.getDataList(),Report.class);

        joinTemplate.joinQuery(reportList);

        return PaginationBuilder.build(pagination,reportList);
    }
}