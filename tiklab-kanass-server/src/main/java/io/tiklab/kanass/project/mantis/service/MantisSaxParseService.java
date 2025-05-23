package io.tiklab.kanass.project.mantis.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.kanass.project.mantis.model.MantisAttachment;
import io.tiklab.kanass.project.mantis.model.MantisIssue;
import io.tiklab.kanass.project.mantis.model.MantisNote;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

public class MantisSaxParseService extends DefaultHandler {
    private String MantisVersion;
    ArrayList<MantisIssue> MantisIssueList;
    private StringBuilder currentText;

    // 在附件标签下
    private boolean inAttachment = false;
    // 在附件列表标签下
    private boolean inAttachmentList = false;
    // 在评论标签下
    private boolean inNote  = false;
    // 在评论列表标签下
    private boolean inNoteList = false;
    // 在事项标签下
    private boolean inIssue = false;
    // 在附件内容标签下
    private boolean inContent = false;

    // 当前事项
    private MantisIssue currentIssue;
    // 当前事项的评论
    private MantisNote currentIssueNote;
    // 当前事项的评论list
    private List<MantisNote> currentIssueNoteList;
    // 当前事项的附件
    private MantisAttachment currentIssueAttachment;
    // 当前事项的附件list
    private List<MantisAttachment> currentIssueAttachmentList;
    // 当前事项附件内容流
    private StringBuilder base64Buffer;

    private File tempFile;
    private OutputStream fileOutput = null;

    // 附件路径
    private String attachmentPath;

//    @Value("${unzip.mantisAttachment}")
//    private void setAttachmentPath(String path){
//        this.attachmentPath = path;
//    }

    public MantisSaxParseService(String attachmentPath) throws Exception {
        this.attachmentPath = attachmentPath;
    }

    @Override
    public void startDocument() throws SAXException {
        MantisVersion = "";
        MantisIssueList = new ArrayList<>();
        currentText = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        if (qName.equals("mantis") && MantisVersion.isBlank()){
            MantisVersion = attributes.getValue("version");
        }

        currentText.setLength(0);

        switch (qName){
            case "issue":
                inIssue  = true;
                currentIssue = new MantisIssue();
                currentIssueNoteList = new ArrayList<>();
                currentIssueAttachmentList = new ArrayList<>();
                break;
            case "project":
                currentIssue.setProjectId(attributes.getValue("id"));
                break;
            case "reporter":
                if (inNote){
                    currentIssueNote.setReporterId(attributes.getValue("id"));
                }else if (inIssue){
                    currentIssue.setReporterId(attributes.getValue("id"));
                }
                break;
            case "handler":
                currentIssue.setHandlerId(attributes.getValue("id"));
                break;
            case "attachments":
                inAttachmentList = true;
                currentIssueAttachmentList = new ArrayList<>();
                break;
            case "attachment":
                inAttachment = true;
                currentIssueAttachment = new MantisAttachment();
                break;
            case "bugnotes":
                inNoteList = true;
                currentIssueNoteList = new ArrayList<>();
                break;
            case "bugnote":
                inNote = true;
                currentIssueNote = new MantisNote();
                break;
            case "content":
                inContent = true;
//                contentBuffer = new ByteArrayOutputStream();
                base64Buffer = new StringBuilder();

//                try {
//                    tempFile = File.createTempFile(attachmentPath + "/base64temp", ".tmp");
//                    fileOutput = new FileOutputStream(tempFile);
//                } catch (IOException e) {
//                    throw new SAXException("创建临时文件失败", e);
//                }
//                break;
            default:
                break;
        }
    }

    @Override
    public void endElement (String uri, String localName, String qName) throws SAXException{
        String text = currentText.toString().trim();

        switch (qName){
            case "issue":
                MantisIssueList.add(currentIssue);
//                currentIssue = null;
                inIssue = false;
                break;
            case "id":
                if (inNote){
                    currentIssueNote.setId(text);
                }else if (inAttachment){
                    currentIssueAttachment.setId(text);
                }else if (inIssue){
                    currentIssue.setId(text);
                }
                break;
            case "project":
                currentIssue.setProjectName(text);
                break;
            case "reporter":
                if (inNote){
                    currentIssueNote.setReporterName(text);
                }else if (inIssue){
                    currentIssue.setReporterName(text);
                }
                break;
            case "handler":
                currentIssue.setHandlerName(text);
                break;
            case "priority":
                currentIssue.setPriority(text);
                break;
            case "status":
                currentIssue.setStatus(text);
                break;
            case "version":
                currentIssue.setVersion(text);
                break;
            case "fix_version":
                currentIssue.setFixVersion(text);
                break;
            case "target_version":
                currentIssue.setTargetVersion(text);
                break;
            case "summary":
                currentIssue.setSummary(text);
                break;
            case "description":
                currentIssue.setDescription(text);
                break;
            case "steps_to_reproduce":
                currentIssue.setStepsToReproduce(text);
                break;
            case "additional_information":
                currentIssue.setAdditionalInformation(text);
                break;
            case "date_submitted":
                if (inNote){
                    currentIssueNote.setDateSubmitted(text);
                }else if (inIssue){
                    currentIssue.setDateSubmitted(text);
                }
                break;
            case "last_updated":
                currentIssue.setLastUpdate(text);
                break;
            case "note":
                if (inNote && inNoteList){
                    currentIssueNote.setNote(text);
                }
                break;
            case "bugnote":
                currentIssueNoteList.add(currentIssueNote);
//                currentIssueNote = null;
                inNote = false;
                break;
            case "bugnotes":
                if (inNoteList){
                    currentIssue.setMantisNoteList(currentIssueNoteList);
//                    currentIssueNoteList = null;
                    inNoteList = false;
                }
                break;
            case "filename":
                if (inAttachment && inAttachmentList){
                    currentIssueAttachment.setFileName(text);
                }
                break;
            case "file_type":
                if (inAttachment && inAttachmentList){
                    currentIssueAttachment.setFileType(text);
                }
                break;
            case "content":
                if (inContent && inAttachment && inAttachmentList){
//                    currentIssueAttachment.setContent(contentBuffer.toByteArray());
//                    contentBuffer = null;
                    inContent = false;

//                    try {
//                        String fullBase64 = base64Buffer.toString();
//                        if (!fullBase64.isEmpty()) {
//                            byte[] decoded = Base64.getDecoder().decode(fullBase64);
//                            currentIssueAttachment.setContent(decoded);
//                        }
//                    }catch (Exception e){
//                        throw new ApplicationException(e);
//                    }


                    try {
                        if (fileOutput != null){
                            fileOutput.close();
                        }

                    } catch (IOException e) {
                        throw new RuntimeException("创建文件失败", e);
                    }
                }
                break;
            case "attachment":
                if (inAttachmentList && inAttachment){
                    currentIssueAttachmentList.add(currentIssueAttachment);
//                    currentIssueAttachment = null;
                    inAttachment = false;
                }
                break;
            case "attachments":
                if (inAttachmentList){
                    currentIssue.setMantisAttachmentList(currentIssueAttachmentList);
//                    currentIssueAttachmentList = null;
                    inAttachmentList = false;
                }
                break;
            default:
                break;

        }
    }

    @Override
    public void characters (char ch[], int start, int length) throws SAXException{

        if (inContent) {
            // 暂存附件内容，结束content标签时统一处理
//            base64Buffer.append(ch, start, length);

            // 如果是content标签内，直接流式处理Base64数据
            try {
                if (fileOutput == null) {
                    // 首次遇到content数据时创建文件
                    File outputFile = new File(attachmentPath + "/" + currentIssue.getId() + "/" + currentIssueAttachment.getFileName().trim());
                    // 确保父目录存在
                    File parentDir = outputFile.getParentFile();
                    if (parentDir != null && !parentDir.exists()) {
                        boolean dirsCreated = parentDir.mkdirs(); // 递归创建目录
                        if (!dirsCreated) {
                            System.err.println("Failed to create directories!");
                            return;
                        }
                    }
                    fileOutput = new FileOutputStream(outputFile);
                }

                if (fileOutput != null) {
                    byte[] partialData = Base64.getDecoder().decode(new String(ch, start, length));
                    fileOutput.write(partialData);
                }
            } catch (IOException e) {
                throw new RuntimeException("文件处理失败", e);
            }
        } else {
            currentText.append(ch, start, length);
        }
    }

    public ArrayList<MantisIssue> getMantisIssueList() {
        return MantisIssueList;
    }

    public String getMantisVersion() {
        return MantisVersion;
    }
}
