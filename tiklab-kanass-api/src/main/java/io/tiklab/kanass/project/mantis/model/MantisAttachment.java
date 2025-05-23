package io.tiklab.kanass.project.mantis.model;

// 事项附件
public class MantisAttachment {
    private String id;
    // 附件名称
    private String fileName;

    // 文件类型
    private String fileType;

    // 附件内容
    private byte[] content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
