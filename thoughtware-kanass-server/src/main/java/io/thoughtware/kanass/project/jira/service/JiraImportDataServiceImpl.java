package io.thoughtware.kanass.project.jira.service;

import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.flow.flow.service.DmFlowService;
import io.thoughtware.flow.statenode.model.StateNodeFlow;
import io.thoughtware.flow.statenode.service.StateNodeFlowService;
import io.thoughtware.form.field.model.SelectItem;
import io.thoughtware.privilege.dmRole.model.DmRole;
import io.thoughtware.privilege.dmRole.model.DmRoleUser;
import io.thoughtware.privilege.dmRole.service.DmRoleService;
import io.thoughtware.privilege.dmRole.service.DmRoleUserService;
import io.thoughtware.privilege.role.model.Role;
import io.thoughtware.privilege.role.model.RoleQuery;
import io.thoughtware.privilege.role.model.RoleUserQuery;
import io.thoughtware.kanass.project.jira.util.UncompressUtil;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.model.ProjectType;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.kanass.workitem.model.*;
import io.thoughtware.kanass.workitem.service.WorkTypeDmService;
import io.thoughtware.kanass.workitem.service.WorkTypeService;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.privilege.role.service.RoleService;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.model.DmUserQuery;
import io.thoughtware.user.dmUser.service.DmUserService;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;

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



    @Value("${unzip.path}")
    String unzipAddress;


    public static Map<String, Integer> Percent = new HashMap();
    public static Map<String, Project> CurrentProject = new HashMap();
    @Override
    @Transactional
    public String importJiraData(InputStream inputStream) {
        BufferedReader unZIP=null;
        String createUserId = LoginContext.getLoginId();
        CurrentProject.put(createUserId + "project", null);
        Percent.put(createUserId + "total", 0);
        Percent.put(createUserId + "currentNum", 0);
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
            List<Element> elements = saxParseService.getElementList();
//            List<Element> sprintElement = analyActiveobjects();
//            elements.addAll(sprintElement);
            String jiraVersion = saxParseService.getJiraVersion();
            if(jiraVersion != null && !jiraVersion.isEmpty() && jiraVersion.equals("9.4.0")){
                System.out.println(jiraVersion);
                jiraImportData94Service.writeData(elements, CurrentProject, Percent);
            }else {
                jiraImportDataCloudService.writeData(elements, CurrentProject, Percent);
            }
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

    @Override
    @Transactional
    public Map<String, Object> findJiraInputSchedule(){
        String loginId = LoginContext.getLoginId();
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("project", CurrentProject.get(loginId + "project"));
        logMap.put("total", Percent.get(loginId + "total"));
        logMap.put("currentNum", Percent.get(loginId + "currentNum"));
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
