package io.tiklab.kanass.workitem.controller;

import io.tiklab.kanass.workitem.model.WorkAttach;
import io.tiklab.kanass.workitem.model.WorkAttachQuery;
import io.tiklab.kanass.workitem.service.WorkAttachService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 事项附件控制器
 */
@RestController
@RequestMapping("/workAttach")
//@Api(name = "WorkAttachController",desc = "事项附件管理")
public class WorkAttachController {

    private static Logger logger = LoggerFactory.getLogger(WorkAttachController.class);

    @Autowired
    private WorkAttachService workAttachService;

    @RequestMapping(path="/createWorkAttach",method = RequestMethod.POST)
    //@ApiMethod(name = "createWorkAttach",desc = "创建事项附件")
    //@ApiParam(name = "workAttach",desc = "事项附件模型",required = true)
    public Result<String> createWorkAttach(@RequestBody @NotNull @Valid WorkAttach workAttach){
        String id = workAttachService.createWorkAttach(workAttach);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkAttach",method = RequestMethod.POST)
    //@ApiMethod(name = "updateWorkAttach",desc = "更新事项附件")
    //@ApiParam(name = "workAttach",desc = "事项附件模型",required = true)
    public Result<Void> updateWorkAttach(@RequestBody @NotNull @Valid WorkAttach workAttach){
        workAttachService.updateWorkAttach(workAttach);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkAttach",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteWorkAttach",desc = "根据id删除事项附件")
    //@ApiParam(name = "id",desc = "事项附件id",required = true)
    public Result<Void> deleteWorkAttach(@NotNull String id){
        workAttachService.deleteWorkAttach(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkAttach",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkAttach",desc = "根据id查找事项附件")
    //@ApiParam(name = "id",desc = "事项附件id",required = true)
    public Result<WorkAttach> findWorkAttach(@NotNull String id){
        WorkAttach workAttach = workAttachService.findWorkAttach(id);

        return Result.ok(workAttach);
    }

    @RequestMapping(path="/findAllWorkAttach",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllWorkAttach",desc = "查找所有事项附件")
    public Result<List<WorkAttach>> findAllWorkAttach(){
        List<WorkAttach> workAttachList = workAttachService.findAllWorkAttach();

        return Result.ok(workAttachList);
    }


    @RequestMapping(path = "/findWorkAttachList",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkAttachList",desc = "根据条件查询事项附件列表")
    //@ApiParam(name = "workAttachQuery",desc = "事项附件查询参数模型",required = true)
    public Result<List<WorkAttach>> findWorkAttachList(@RequestBody @Valid @NotNull WorkAttachQuery workAttachQuery){
        List<WorkAttach> workAttachList = workAttachService.findWorkAttachList(workAttachQuery);

        return Result.ok(workAttachList);
    }


    @RequestMapping(path = "/findWorkAttachPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkAttachPage",desc = "根据条件按分页查询事项附件")
    //@ApiParam(name = "workAttachQuery",desc = "事项附件查询参数模型",required = true)
    public Result<Pagination<WorkAttach>> findWorkAttachPage(@RequestBody @Valid @NotNull WorkAttachQuery workAttachQuery){
        Pagination<WorkAttach> pagination = workAttachService.findWorkAttachPage(workAttachQuery);

        return Result.ok(pagination);
    }

}
