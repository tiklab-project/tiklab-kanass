package io.tiklab.kanass.project.jira.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.project.jira.util.UncompressUtil;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.tenant.ids.common.TenantHolder;
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

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    JiraImportDataCloudService jiraImportDataCloudService;

    @Autowired
    JiraImportData94ServiceImpl jiraImportData94Service;

    @Autowired
    ProjectService projectService;

    @Autowired
    private TransactionTemplate transactionTemplate;


    @Value("${unzip.path}")
    String unzipAddress;

    public final ExecutorService executorService = Executors.newCachedThreadPool();

    public static Map<String, Integer> Percent = new HashMap();
    public static Map<String, Project> CurrentProject = new HashMap();
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
        String s = TenantHolder.get();
        transactionTemplate.execute((status) -> {
            String t = TenantHolder.get();
            // 在这里执行需要在事务中的操作
            return setData(inputStream);
        });
//        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String s = TenantHolder.get();
//                transactionTemplate.execute((status) -> {
//                    String t = TenantHolder.get();
//                    // 在这里执行需要在事务中的操作
//                    return setData(inputStream);
//                });
//            }
//        }).start();

    }
    public String setData(InputStream inputStream) {
        BufferedReader unZIP=null;
        String createUserId = LoginContext.getLoginId();
        CurrentProject.put(createUserId + "project", null);
        Percent.put(createUserId + "total", 0);
        Percent.put(createUserId + "currentNum", 0);
        Percent.put(createUserId + "status", 0);
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

            if(jiraVersion != null && !jiraVersion.isEmpty() && jiraVersion.equals("9.4.0")){
                System.out.println(jiraVersion);
                List<Element> sprintElement = analyActiveobjects();
                elements.addAll(sprintElement);
                jiraImportData94Service.writeData(elements, CurrentProject, Percent);
            }else {
                List<Element> sprintElement = analyActiveobjectsCloud();
                elements.addAll(sprintElement);
                jiraImportDataCloudService.writeData(elements, CurrentProject, Percent);
            }
            Percent.put(createUserId + "status", 1);
            return "success";
        } catch (Exception e) {
            throw new ApplicationException(e);
        }

    }


    public List<Element> analyActiveobjects() {
        SaxParseRowServiceImpl saxParseRowService = new SaxParseRowServiceImpl();
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
        return logMap;
    }

    @Override
    @Transactional
    public void testBack() {
        try{
            String sql = "insert into pmc_project(id,project_name,project_type_id,project_key,project_limits,master,creator,description, icon_url) values(?,?,?,?,?,?,?,?,?)";
            getJdbcTemplet().update(sql,"dd7f8c65406a","测试回滚","5a46432a","testyu","0","111111","111111","ces", "project1.png");
            Project projectId = projectService.findProject("dd7f8c65406a");

            String sql1 = "insert into pmc_project_focus(id,project_id,master_id,sort) values(?,?,?,?)";
            getJdbcTemplet().update(sql1,"901237241cwe",projectId,"111111","5");

        }catch (Exception e){
            throw new ApplicationException(2000,"添加失败" + e.getMessage());
        }
    }

    public JdbcTemplate getJdbcTemplet(){
      return  jpaTemplate.getJdbcTemplate();
    }

}
