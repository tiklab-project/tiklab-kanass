package io.thoughtware.kanass.support.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.kanass.support.service.ExportFileService;
import io.thoughtware.kanass.workitem.model.WorkItemQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 最近访问的控制器
 */
@RestController
@RequestMapping("/exportfile")
@Api(name = "ExportFileController",desc = "ExportFileController")
public class ExportFileController {

    private static Logger logger = LoggerFactory.getLogger(ExportFileController.class);

    @Autowired
    private ExportFileService exportFileService;

    @RequestMapping(path="/exportWorkItemXml",method = RequestMethod.POST)
    @ApiMethod(name = "exportWorkItemXml",desc = "返回soular地址给前端")
    public void exportWorkItemXml(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx;");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        WorkItemQuery workItemQuery = objectMapper.readValue(request.getReader(), WorkItemQuery.class);
        byte[] bytes = exportFileService.exportWorkItemXml(workItemQuery);
        response.setContentLength(bytes.length);
        response.getOutputStream().write(bytes);

    }

}
