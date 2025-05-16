package io.tiklab.kanass.project.jira.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.common.ErrorCode;
import io.tiklab.kanass.project.jira.util.UncompressUtil;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * jira 数据导入服务
 */
@Service
@EnableTransactionManagement
public class JiraImportDataServiceImpl implements JiraImportDataService {

    private static final Logger log = LoggerFactory.getLogger(JiraImportDataServiceImpl.class);
    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    JiraImportDataCloudService jiraImportDataCloudService;

    @Autowired
    JiraImportData94ServiceImpl jiraImportData94Service;

    @Autowired
    JiraImportData84ServiceImpl jiraImportData84Service;

    @Autowired
    ProjectService projectService;

    @Autowired
    private TransactionTemplate transactionTemplate;


    @Value("${unzip.path}")
    String unzipAddress;

    public final ExecutorService executorService = Executors.newCachedThreadPool();

    public static Map<String, Integer> Percent = new HashMap();
    public static Map<String, Project> CurrentProject = new HashMap();

    public static Map<String, String> Step = new HashMap<>();
//    @Override
//    public String importJiraData(InputStream inputStream) {
//        return transactionTemplate.execute((status) -> {
//            // 在这里执行需要在事务中的操作
//            return setData(inputStream);
//        });
//    }
    @Override
    public void importJiraData(InputStream inputStream) {
//        executorService.submit(() -> {
        transactionTemplate.execute((status) -> {
            // 在这里执行需要在事务中的操作
            return setData(inputStream);
        });

    }
    public String setData(InputStream inputStream) {
        BufferedReader unZIP=null;
        String createUserId = LoginContext.getLoginId();
        CurrentProject.put(createUserId + "project", null);
        Percent.put(createUserId + "total", 0);
        Percent.put(createUserId + "currentNum", 0);
        Percent.put(createUserId + "status", 0);
        Step.put("step0", "process");// 步骤1 上传文件
        Step.put("step1", "wait");// 步骤2 校验版本
        Step.put("step2", "wait");// 步骤3 解析数据
        Step.put("step3", "wait");// 步骤4 开始导入
        try {
            unZIP = new UncompressUtil().unZIP(inputStream, unzipAddress);
            String path=unzipAddress+"/entities.xml";
            SaxParseServiceImpl saxParseService = new SaxParseServiceImpl();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            try (FileInputStream fis = new FileInputStream(path);
                 Reader reader = new InvalidXMLCharFilter(new InputStreamReader(fis, "UTF-8"))) {
                // Wrap the Reader in InputSource
                InputSource is = new InputSource(reader);
                // Parse the XML using SAX parser
                parser.parse(is, saxParseService);
            }

            //InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("entities.xml");
            String jiraVersion = saxParseService.getJiraVersion();
            List<Element> elements = saxParseService.getElementList();

            if(jiraVersion != null && !jiraVersion.isEmpty() && jiraVersion.equals("9.4.0") ) {
                System.out.println(jiraVersion);
                List<Element> sprintElement = analyActiveobjects94();
                elements.addAll(sprintElement);
                jiraImportData94Service.writeData(elements, Step, CurrentProject, Percent);
            }
            else if(jiraVersion != null && !jiraVersion.isEmpty() && jiraVersion.equals("8.4.1")){
                List<Element> sprintElement = analyActiveobjects84();
                elements.addAll(sprintElement);
                jiraImportData84Service.writeData(elements, Step, CurrentProject, Percent);
            }else {
                List<Element> sprintElement = analyActiveobjectsCloud();
                elements.addAll(sprintElement);
                jiraImportDataCloudService.writeData(elements, CurrentProject, Percent);
            }
            Step.put("step3", "process");
            Percent.put(createUserId + "status", 1);
            return "success";
        } catch (Exception e) {
            Percent.put(createUserId + "status", 2);
            throw new ApplicationException(e);
        }

    }


    public List<Element> analyActiveobjects94() {
        SaxParseRow94ServiceImpl saxParseRowService = new SaxParseRow94ServiceImpl();
        try {
            String path=unzipAddress+"/activeobjects.xml";

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            try (FileInputStream fis = new FileInputStream(path);
                 Reader reader = new InvalidXMLCharFilter(new InputStreamReader(fis, "UTF-8"))) {
                // Wrap the Reader in InputSource
                InputSource is = new InputSource(reader);
                // Parse the XML using SAX parser
                parser.parse(is, saxParseRowService);
            }

        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        ArrayList<Element> elementList = saxParseRowService.getElementList();
        return elementList;
    }

    public List<Element> analyActiveobjects84() {
        SaxParseRow84ServiceImpl saxParseRowService = new SaxParseRow84ServiceImpl();
        try {
            String path=unzipAddress+"/activeobjects.xml";

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            try (FileInputStream fis = new FileInputStream(path);
                 Reader reader = new InvalidXMLCharFilter(new InputStreamReader(fis, "UTF-8"))) {
                // Wrap the Reader in InputSource
                InputSource is = new InputSource(reader);
                // Parse the XML using SAX parser
                parser.parse(is, saxParseRowService);
            }

        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        ArrayList<Element> elementList = saxParseRowService.getElementList();
        return elementList;
    }

    public List<Element> analyActiveobjectsCloud() {
        SaxParseRowCloudServiceImpl saxParseRowCloudService = new SaxParseRowCloudServiceImpl();
        try {
            String path=unzipAddress+"/activeobjects.xml";

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            try (FileInputStream fis = new FileInputStream(path);
                 Reader reader = new InvalidXMLCharFilter(new InputStreamReader(fis, "UTF-8"))) {
                // Wrap the Reader in InputSource
                InputSource is = new InputSource(reader);
                // Parse the XML using SAX parser
                parser.parse(is, saxParseRowCloudService);
            }

        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        ArrayList<Element> elementList = saxParseRowCloudService.getElementList();
        return elementList;
    }

    @Override
    @Transactional
    public Map<String, Object> findJiraInputSchedule(){
        String loginId = LoginContext.getLoginId();
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("project", CurrentProject.get(loginId + "project"));
        logMap.put("total", Percent.get(loginId + "total"));
        logMap.put("currentNum", Percent.get(loginId + "currentNum"));
        logMap.put("status", Percent.get(loginId + "status"));
        logMap.put("step", Step);
        return logMap;
    }

    @Override
    @Transactional
    public void testBack() {
        try{
            String sql = "insert into pmc_project(id,project_name,project_type_id,project_key,project_limits,master,creator,description, icon_url) values(?,?,?,?,?,?,?,?,?)";
            getJdbcTemplate().update(sql,"dd7f8c65406a","测试回滚","5a46432a","testyu","0","111111","111111","ces", "project1.png");
            Project projectId = projectService.findProject("dd7f8c65406a");

            String sql1 = "insert into pmc_project_focus(id,project_id,master_id,sort) values(?,?,?,?)";
            getJdbcTemplate().update(sql1,"901237241cwe",projectId,"111111","5");

        }catch (Exception e){
            throw new ApplicationException(ErrorCode.CREATE_ERROR,"添加失败" + e.getMessage());
        }
    }

    public JdbcTemplate getJdbcTemplate(){
      return  jpaTemplate.getJdbcTemplate();
    }

}
