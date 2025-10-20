package io.tiklab.kanass.test.instance.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.test.instance.model.Instance;
import io.tiklab.kanass.test.instance.model.InstanceQuery;
import io.tiklab.kanass.test.instance.service.InstanceService;
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

@RestController
@RequestMapping("/instance")
//@Api(name = "InstanceController",desc = "公共实例管理")
public class InstanceController {

    private static Logger logger = LoggerFactory.getLogger(InstanceController.class);

    @Autowired
    private InstanceService instanceService;

    @RequestMapping(path="/createInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "createInstance",desc = "创建公共实例")
//    @ApiParam(name = "instance",desc = "instance",required = true)
    public Result<String> createInstance(@RequestBody @NotNull @Valid Instance instance){
        String id = instanceService.createInstance(instance);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "updateInstance",desc = "更新公共实例")
//    @ApiParam(name = "instance",desc = "instance",required = true)
    public Result<Void> updateInstance(@RequestBody @NotNull @Valid Instance instance){
        instanceService.updateInstance(instance);

        return Result.ok();
    }


    @RequestMapping(path="/deleteInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteInstance",desc = "删除公共实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteInstance(@NotNull String id,String caseType){
        instanceService.deleteInstance(id,caseType);

        return Result.ok();
    }

    @RequestMapping(path="/findInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findInstance",desc = "根据id查找公共实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Instance> findInstance(@NotNull String id){
        Instance instance = instanceService.findInstance(id);

        return Result.ok(instance);
    }

    @RequestMapping(path="/findAllInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllInstance",desc = "查找所有公共实例")
    public Result<List<Instance>> findAllInstance(){
        List<Instance> instanceList = instanceService.findAllInstance();

        return Result.ok(instanceList);
    }

    @RequestMapping(path = "/findInstanceList",method = RequestMethod.POST)
//    @ApiMethod(name = "findInstanceList",desc = "查询公共实例列表")
//    @ApiParam(name = "instanceQuery",desc = "instanceQuery",required = true)
    public Result<List<Instance>> findInstanceList(@RequestBody @Valid @NotNull InstanceQuery instanceQuery){
        List<Instance> instanceList = instanceService.findInstanceList(instanceQuery);

        return Result.ok(instanceList);
    }

    @RequestMapping(path = "/findInstancePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findInstancePage",desc = "按分页查询公共实例")
//    @ApiParam(name = "instanceQuery",desc = "instanceQuery",required = true)
    public Result<Pagination<Instance>> findInstancePage(@RequestBody @Valid @NotNull InstanceQuery instanceQuery){
        Pagination<Instance> pagination = instanceService.findInstancePage(instanceQuery);

        return Result.ok(pagination);
    }
    

}
