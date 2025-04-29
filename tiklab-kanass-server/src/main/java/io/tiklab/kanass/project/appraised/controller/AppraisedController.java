package io.tiklab.kanass.project.appraised.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.appraised.model.Appraised;
import io.tiklab.kanass.project.appraised.model.AppraisedQuery;
import io.tiklab.kanass.project.appraised.service.AppraisedService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 项目评审控制器
 */
@RestController
@RequestMapping("/appraised")
@Api(name = "AppraisedController",desc = "项目评审")
public class AppraisedController {
    private static Logger logger = LoggerFactory.getLogger(AppraisedController.class);

    @Autowired
    private AppraisedService appraisedService;

    @RequestMapping(path="/createAppraised",method = RequestMethod.POST)
    @ApiMethod(name = "createAppraised",desc = "创建项目评审")
    @ApiParam(name = "appraised",desc = "项目评审模型",required = true)
    public Result<String> createAppraised(@RequestBody @NotNull @Valid Appraised appraised){
        String id = appraisedService.createAppraised(appraised);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppraised",method = RequestMethod.POST)
    @ApiMethod(name = "updateAppraised",desc = "更新项目评审")
    @ApiParam(name = "appraised",desc = "项目评审模型",required = true)
    public Result<Void> updateAppraised(@RequestBody @NotNull @Valid Appraised appraised){
        appraisedService.updateAppraised(appraised);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppraised",method = RequestMethod.POST)
    @ApiMethod(name = "deleteAppraised",desc = "删除项目评审")
    @ApiParam(name = "id",desc = "评审id",required = true)
    public Result<Void> deleteAppraised(@NotNull String id){
        appraisedService.deleteAppraised(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppraised",method = RequestMethod.POST)
    @ApiMethod(name = "findAppraised",desc = "根据id查找项目评审")
    @ApiParam(name = "id",desc = "评审id",required = true)
    public Result<Appraised> findAppraised(@NotNull String id){
        Appraised appraised = appraisedService.findAppraised(id);

        return Result.ok(appraised);
    }

    @RequestMapping(path="/findAllAppraised",method = RequestMethod.POST)
    @ApiMethod(name = "findAllAppraised",desc = "查找所有项目评审")
    public Result<List<Appraised>> findAllAppraised(){
        List<Appraised> appraisedList = appraisedService.findAllAppraised();

        return Result.ok(appraisedList);
    }


    @RequestMapping(path = "/findAppraisedList",method = RequestMethod.POST)
    @ApiMethod(name = "findAppraisedList",desc = "根据条件查找项目类型列表")
    @ApiParam(name = "appraisedQuery",desc = "项目评审搜索模型",required = true)
    public Result<List<Appraised>> findAppraisedList(@RequestBody @Valid @NotNull AppraisedQuery appraisedQuery){
        List<Appraised> appraisedList = appraisedService.findAppraisedList(appraisedQuery);

        return Result.ok(appraisedList);
    }


    @RequestMapping(path = "/findAppraisedPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAppraisedPage",desc = "根据条件按分页查询项目类型列表")
    @ApiParam(name = "appraisedQuery",desc = "项目评审搜索模型",required = true)
    public Result<Pagination<Appraised>> findAppraisedPage(@RequestBody @Valid @NotNull AppraisedQuery appraisedQuery){
        Pagination<Appraised> pagination = appraisedService.findAppraisedPage(appraisedQuery);

        return Result.ok(pagination);
    }
}
