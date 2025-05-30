package io.tiklab.kanass.project.mantis.controller;

import io.tiklab.core.Result;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.kanass.project.mantis.service.MantisImportDataService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/importDate")
//@Api(name = "MantisImportDataController",desc = "MantisImportDataController")
public class MantisImportDataController {

    @Autowired
    private MantisImportDataService mantisImportDataService;

    @RequestMapping(path="/importMantisDate",method = RequestMethod.POST)
    //@ApiMethod(name = "importMantisDate",desc = "导入Mantis数据")
    //@ApiParam(name = "uploadFile",desc = "压缩包文件",required = true)
    public Result<Void> importMantisDate(@RequestParam("uploadFile") MultipartFile uploadFile){
        if (uploadFile == null) {
            throw new ApplicationException("文件不能为空");
        }else {
            try {
                InputStream inputStream = uploadFile.getInputStream();
                mantisImportDataService.importMantisData(inputStream);
                return Result.ok();
            } catch (IOException e) {
                throw new ApplicationException(e);
            }
        }
    }

    @RequestMapping(path="/findMantisInputSchedule",method = RequestMethod.POST)
    //@ApiMethod(name = "findMantisInputSchedule",desc = "查询导入进度")
    public Result<Map<String, Object>> findMantisInputSchedule(){
        Map<String, Object> mantisInputSchedule = mantisImportDataService.findMantisInputSchedule();
        return Result.ok(mantisInputSchedule);
    }
}
