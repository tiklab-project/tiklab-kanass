package io.tiklab.teamwire.support.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teamwire.support.model.Icon;
import io.tiklab.teamwire.support.service.BackUpService;
import io.tiklab.teamwire.support.service.ExportFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/backup")
@Api(name = "BackUpController",desc = "BackUpController")
public class BackUpController {
    private static Logger logger = LoggerFactory.getLogger(BackUpController.class);

    @Autowired
    private BackUpService backUpService;

    @RequestMapping(path="/backupsExec",method = RequestMethod.POST)
    @ApiMethod(name = "backupsExec",desc = "备份")
    public Result<String> backupsExec(){
        String result = backUpService.backupsExec();

        return Result.ok(result);
    }
}
