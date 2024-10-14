package io.tiklab.kanass.support.controller;

import io.tiklab.core.Result;
import io.tiklab.kanass.support.service.CopyFlowToProjectImpl;
import io.tiklab.kanass.support.service.InitStateNodeFiledService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图标控制器
 */
@RestController
@RequestMapping("/initStateNodeField")
@Api(name = "InitStateNodeFieldController",desc = "InitStateNodeFieldController")
public class InitStateNodeFieldController {

    private static Logger logger = LoggerFactory.getLogger(InitStateNodeFieldController.class);

    @Autowired
    private InitStateNodeFiledService initStateNodeFiledService;

    @Autowired
    CopyFlowToProjectImpl copyFlowToProject;


    @RequestMapping(path="/addFormField",method = RequestMethod.POST)
    @ApiMethod(name = "addFormField",desc = "创建图标")
    public Result<Void> addFormField(){
        initStateNodeFiledService.addFormField();

        return Result.ok();
    }

    @RequestMapping(path="/setRoleParent",method = RequestMethod.POST)
    public Result<Void> setRoleParent(){
        initStateNodeFiledService.setRoleParent();

        return Result.ok();
    }

    @RequestMapping(path="/flowRelationForm",method = RequestMethod.POST)
    public Result<Void> flowRelationForm(){
        initStateNodeFiledService.flowRelationForm();

        return Result.ok();
    }

    @RequestMapping(path="/newformFieldList",method = RequestMethod.POST)
    public Result<Void> newformFieldList(){
        initStateNodeFiledService.newformFieldList();

        return Result.ok();
    }

    @RequestMapping(path="/stateNodeField",method = RequestMethod.POST)
    public Result<Void> stateNodeField(){
        initStateNodeFiledService.stateNodeField();

        return Result.ok();
    }

    @RequestMapping(path="/updateFormField",method = RequestMethod.POST)
    public Result<Void> updateFormField(){
        initStateNodeFiledService.updateFormField();

        return Result.ok();
    }

    @RequestMapping(path="/copyFlow",method = RequestMethod.POST)
    public Result<Void> copyFlow(){
        copyFlowToProject.copyFlow();

        return Result.ok();
    }
}
