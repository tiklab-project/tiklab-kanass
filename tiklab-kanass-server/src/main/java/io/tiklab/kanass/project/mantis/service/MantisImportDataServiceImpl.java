package io.tiklab.kanass.project.mantis.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.project.jira.service.InvalidXMLCharFilter;
import io.tiklab.kanass.project.jira.util.UncompressUtil;
import io.tiklab.kanass.project.mantis.model.MantisIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class MantisImportDataServiceImpl implements MantisImportDataService{

    @Autowired
    private Mantis2271ImportDataService mantis2271ImportDataService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public static Map<String, Integer> Percent = new HashMap();
//    public static Map<String, Project> CurrentProject = new HashMap();

    public static Map<String, String> Step = new HashMap<>();

    @Value("${unzip.mantisPath}")
    String unzipAddress;

    @Value("${unzip.mantisAttachment}")
    String attachmentPath;
    @Override
    public void importMantisData(InputStream inputStream) {
        transactionTemplate.execute((status) -> {
            // 在这里执行需要在事务中的操作
            return setData(inputStream);
        });
    }

    public String setData(InputStream inputStream) {
        BufferedReader unZIP=null;
        String createUserId = LoginContext.getLoginId();
        Percent.put(createUserId + "total", 0);
        Percent.put(createUserId + "currentNum", 0);
        Percent.put(createUserId + "status", 0);
        Step.put("step0", "process");// 步骤1 上传文件
        Step.put("step1", "wait");// 步骤2 校验版本
        Step.put("step2", "wait");// 步骤3 解析数据
        Step.put("step3", "wait");// 步骤4 开始导入

        try {
            unZIP = new UncompressUtil().unZIP(inputStream, unzipAddress);
            String path = unzipAddress+"/exported_issues.xml";

            MantisSaxParseService mantisSaxParseService = new MantisSaxParseService(attachmentPath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            try (FileInputStream fis = new FileInputStream(path);
                 Reader reader = new InvalidXMLCharFilter(new InputStreamReader(fis, "UTF-8"))) {
                // Wrap the Reader in InputSource
                InputSource is = new InputSource(reader);
                // Parse the XML using SAX parser
                parser.parse(is, mantisSaxParseService);
            }
            ArrayList<MantisIssue> issueList = mantisSaxParseService.getMantisIssueList();
            String mantisVersion = mantisSaxParseService.getMantisVersion();

            if (mantisVersion.equals("2.27.1")){
                mantis2271ImportDataService.writeData(issueList, Step, Percent);
            }

            Step.put("step3", "process");
            Percent.put(createUserId + "status", 1);
            return "success";

        } catch (Exception e) {
            Percent.put(createUserId + "status", 2);
            throw new ApplicationException(e);
        }



    }

    @Override
    public Map<String, Object> findMantisInputSchedule() {
        String loginId = LoginContext.getLoginId();
        Map<String, Object> logMap = new HashMap<>();
//        logMap.put("project", CurrentProject.get(loginId + "project"));
        logMap.put("total", Percent.get(loginId + "total"));
        logMap.put("currentNum", Percent.get(loginId + "currentNum"));
        logMap.put("status", Percent.get(loginId + "status"));
        logMap.put("step", Step);
        return logMap;
    }
}
