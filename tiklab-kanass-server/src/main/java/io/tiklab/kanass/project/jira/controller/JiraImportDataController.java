package io.tiklab.kanass.project.jira.controller;

import io.tiklab.kanass.project.jira.service.JiraImportDataService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.core.Result;
import io.tiklab.core.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * jira 数据导入
 */

@RestController
@RequestMapping("/importDate")
@Api(name = "JiraImportDataController",desc = "JiraImportDataController")
public class JiraImportDataController {

    @Autowired
    JiraImportDataService jiraImportDataService;

    @RequestMapping(path="/importJireDate",method = RequestMethod.POST)
    @ApiMethod(name = "importJireDate",desc = "导入jire数据")
    @ApiParam(name = "uploadFile",desc = "压缩包文件",required = true)
    public Result<Void> importJireDate(@RequestParam("uploadFile")MultipartFile uploadFile){
        if (uploadFile == null) {
            throw new ApplicationException("文件不能为空");
        }else {
            try {
                InputStream inputStream = uploadFile.getInputStream();
                jiraImportDataService.importJiraData(inputStream);
                return Result.ok();
            } catch (IOException e) {
                throw new ApplicationException(e);
            }
        }
    }




    @RequestMapping(path="/findJiraInputSchedule",method = RequestMethod.POST)
    @ApiMethod(name = "findJiraInputSchedule",desc = "导入jire数据")
    public Result<Map<String, Object>> findJiraInputSchedule(){
        Map<String, Object> jiraInputSchedule = jiraImportDataService.findJiraInputSchedule();
        return Result.ok(jiraInputSchedule);
    }

    @RequestMapping(path="/testBack",method = RequestMethod.POST)
    @ApiMethod(name = "testBack",desc = "导入jire数据")
    public void testBack(){
        jiraImportDataService.testBack();
    }
}
