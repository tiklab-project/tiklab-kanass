package io.tiklab.teamwire.support.controller;

import io.tiklab.core.Result;
import io.tiklab.core.exception.SystemException;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teamwire.support.model.Backups;
import io.tiklab.teamwire.support.service.BackupsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/backup")
@Api(name = "BackUpController",desc = "BackUpController")
public class BackupsController {
    private static Logger logger = LoggerFactory.getLogger(BackupsController.class);

    @Autowired
    private BackupsService backupsService;

    @RequestMapping(path="/backupsExec",method = RequestMethod.POST)
    @ApiMethod(name = "backupsExec",desc = "备份")
    public Result<String> backupsExec(){
        String result = backupsService.backupsExec();

        return Result.ok(result);
    }

    @RequestMapping(path="/recoveryData",method = RequestMethod.POST)
    @ApiMethod(name = "recoveryData",desc = "备份")
    public Result<String> recoveryData(String fileName){
        String result = backupsService.recoveryData(fileName);

        return Result.ok(result);
    }

    @RequestMapping(path="/findBackups",method = RequestMethod.POST)
    @ApiMethod(name = "findBackups",desc = "查找备份信息")
    public Result<Backups> findBackups(){
        Backups backups = backupsService.findBackups();

        return Result.ok(backups);
    }

    @RequestMapping(path="/updateBackups",method = RequestMethod.POST)
    @ApiMethod(name = "updateBackups",desc = "修改备份相关数据")
    @ApiParam(name = "auth",desc = "auth",required = true)
    public Result<String> updateBackups(@RequestBody @NotNull @Valid Backups backups){

        backupsService.updateBackups(backups);

        return Result.ok();
    }

    @RequestMapping(path="/gainBackupsRes",method = RequestMethod.POST)
    @ApiMethod(name = "gainBackupsRes",desc = "获取备份或者数据恢复结果")
    @ApiParam(name = "type",desc = "backups、recovery",required = true)
    public Result<String> gainBackupsRes(@NotNull String type){

        String gainBackupsRes = backupsService.gainBackupsRes(type);

        return Result.ok(gainBackupsRes);
    }

    @RequestMapping(path="/uploadBackups",method = RequestMethod.POST)
    @ApiMethod(name = "uploadBackups",desc = "上传备份数据")
    @ApiParam(name = "uploadFile",desc = "uploadFile",required = true)
    public Result<String> uploadBackups(@RequestParam("uploadFile") MultipartFile uploadFile, String userId){
        try {
            String fileName = uploadFile.getOriginalFilename();   //获取文件名字
            InputStream inputStream = uploadFile.getInputStream();
            backupsService.uploadBackups(inputStream,fileName,userId);
        } catch (IOException e) {
            throw new SystemException(e);
        }
        return Result.ok();
    }

}
