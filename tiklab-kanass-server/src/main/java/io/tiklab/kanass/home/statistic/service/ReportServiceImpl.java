package io.tiklab.kanass.home.statistic.service;

import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.home.statistic.model.Report;
import io.tiklab.kanass.home.statistic.model.ReportQuery;
import io.tiklab.kanass.home.statistic.dao.ReportDao;
import io.tiklab.kanass.home.statistic.entity.ReportEntity;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 报表服务，弃用
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

        joinTemplate.joinQuery(report, new String[]{"creater"});

        return report;
    }

    @Override
    public List<Report> findAllReport() {
        List<ReportEntity> reportEntityList =  reportDao.findAllReport();

        List<Report> reportList =  BeanMapper.mapList(reportEntityList,Report.class);

        joinTemplate.joinQuery(reportList, new String[]{"creater"});

        return reportList;
    }

    @Override
    public List<Report> findReportList(ReportQuery reportQuery) {
        List<ReportEntity> reportEntityList = reportDao.findReportList(reportQuery);

        List<Report> reportList = BeanMapper.mapList(reportEntityList,Report.class);

        joinTemplate.joinQuery(reportList, new String[]{"creater"});

        return reportList;
    }

    @Override
    public Pagination<Report> findReportPage(ReportQuery reportQuery) {
        Pagination<ReportEntity>  pagination = reportDao.findReportPage(reportQuery);

        List<Report> reportList = BeanMapper.mapList(pagination.getDataList(),Report.class);

        joinTemplate.joinQuery(reportList, new String[]{"creater"});

        return PaginationBuilder.build(pagination,reportList);
    }
}