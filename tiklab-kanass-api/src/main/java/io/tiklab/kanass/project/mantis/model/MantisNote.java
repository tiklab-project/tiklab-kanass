package io.tiklab.kanass.project.mantis.model;

// 事项的注释 等于我们系统中的评论
public class MantisNote {
    // 注释id
    private String id;

    // 创建者id
    private String reporterId;

    // 创建者名称
    private String reporterName;

    // 注释内容
    private String note;

    // 提交日期
    private String dateSubmitted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }
}
