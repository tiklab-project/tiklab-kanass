package io.tiklab.kanass.home.statistic.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.sql.Timestamp;

/**
 * 报表实例
 */
@Entity
@Table(name="pmc_report")
public class ReportEntity {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12,notNull = true)
    private String id;

    // 项目集id
    @Column(name = "program_id",length = 64)
    private String programId;

    //项目id
    @Column(name = "project_id",length = 64)
    private String projectId;

    //迭代id
    @Column(name = "sprint_id",length = 64)
    private String sprintId;

    //报表标题
    @Column(name = "title",length = 64,notNull = true)
    private String title;

    //创建者id
    @Column(name = "creater_id",length = 64,notNull = true)
    private String createrId;

    //创建时间
    @Column(name = "creater_time")
    private Timestamp creatTime;

    //报表类型
    @Column(name = "report_type",length = 64)
    private String reportType;

    //报表所属大类（log, work）
    @Column(name = "type",length = 64)
    private String type;

    //报表数据
    @Column(name = "report_data",length = 2048)
    private String reportData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportData() {
        return reportData;
    }

    public void setReportData(String reportData) {
        this.reportData = reportData;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }
}
