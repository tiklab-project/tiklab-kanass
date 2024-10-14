package io.tiklab.kanass.support.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.kanass.support.service.UpdateMySqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 最近访问的控制器
 */
@RestController
@RequestMapping("/updateMySql")
@Api(name = "UpdateMySqlController",desc = "UpdateMySqlController")
public class UpdateMySqlController {

    private static Logger logger = LoggerFactory.getLogger(UpdateMySqlController.class);

    @Autowired
    private UpdateMySqlService updateMySqlService;

    @RequestMapping(path="/updateAllData",method = RequestMethod.POST)
    @ApiMethod(name = "updateAllData")
    public void updateAllData(){
        updateMySqlService.updateAllData();
    }

    @RequestMapping(path="/updateProject",method = RequestMethod.POST)
    @ApiMethod(name = "updateProject")
    public void updateProject(){
         updateMySqlService.updateProject();
    }

    @RequestMapping(path="/updateModule",method = RequestMethod.POST)
    @ApiMethod(name = "updateModule")
    public void updateModule(){
        updateMySqlService.updateModule();
    }

    @RequestMapping(path="/updateSprint",method = RequestMethod.POST)
    @ApiMethod(name = "updateSprint")
    public void updateSprint(){
        updateMySqlService.updateSprint();
    }

//    @RequestMapping(path="/updateSprintStatus",method = RequestMethod.POST)
//    @ApiMethod(name = "updateSprintStatus")
//    public void updateSprintStatus(){
//        updateMySqlService.updateSprintStatus();
//    }

    @RequestMapping(path="/updateVersion",method = RequestMethod.POST)
    @ApiMethod(name = "updateVersion")
    public void updateVersion(){
        updateMySqlService.updateVersion();
    }

    @RequestMapping(path="/updateWorkItemDocument",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkItemDocument")
    public void updateWorkItemDocument(){
        updateMySqlService.updateWorkItemDocument();
    }

    @RequestMapping(path="/updateWorkItemAttach",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkItemAttach")
    public void updateWorkItemAttach(){
        updateMySqlService.updateWorkItemAttach();
    }

    @RequestMapping(path="/updateWorkItemLog",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkItemLog")
    public void updateWorkItemLog(){
        updateMySqlService.updateWorkItemLog();
    }

    @RequestMapping(path="/updateWorkPriority",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkPriority")
    public void updateWorkPriority(){
        updateMySqlService.updateWorkPriority();
    }

    @RequestMapping(path="/updateWorkRelate",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkRelate")
    public void updateWorkRelate(){
        updateMySqlService.updateWorkRelate();
    }

    @RequestMapping(path="/updateWorkType",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkType")
    public void updateWorkType(){
        updateMySqlService.updateWorkType();
    }

    @RequestMapping(path="/updateProjectSet",method = RequestMethod.POST)
    @ApiMethod(name = "updateProjectSet")
    public void updateProjectSet(){
        updateMySqlService.updateProjectSet();
    }

    @RequestMapping(path="/updateEpic",method = RequestMethod.POST)
    @ApiMethod(name = "updateEpic")
    public void updateEpic(){
        updateMySqlService.updateEpic();
    }

    @RequestMapping(path="/updateEpicWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "updateEpicWorkItem")
    public void updateEpicWorkItem(){
        updateMySqlService.updateEpicWorkItem();
    }

    @RequestMapping(path="/updateIcon",method = RequestMethod.POST)
    @ApiMethod(name = "updateIcon")
    public void updateIcon(){
        updateMySqlService.updateIcon();
    }

    @RequestMapping(path="/updateWorkComment",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkComment")
    public void updateWorkComment(){
        updateMySqlService.updateWorkComment();
    }

    @RequestMapping(path="/updateReport",method = RequestMethod.POST)
    @ApiMethod(name = "updateReport")
    public void updateReport(){
        updateMySqlService.updateReport();
    }

    @RequestMapping(path="/updateRecent",method = RequestMethod.POST)
    @ApiMethod(name = "updateRecent")
    public void updateRecent(){
        updateMySqlService.updateRecent();
    }

    @RequestMapping(path="/updateSprintFocus",method = RequestMethod.POST)
    @ApiMethod(name = "updateSprintFocus")
    public void updateSprintFocus(){
        updateMySqlService.updateSprintFocus();
    }

    @RequestMapping(path="/updateSprintBurndowm",method = RequestMethod.POST)
    @ApiMethod(name = "updateSprintBurndowm")
    public void updateSprintBurndowm(){
        updateMySqlService.updateSprintBurndowm();
    }

    @RequestMapping(path="/updateMilestone",method = RequestMethod.POST)
    @ApiMethod(name = "updateMilestone")
    public void updateMilestone(){
        updateMySqlService.updateMilestone();
    }

    @RequestMapping(path="/updatePlan",method = RequestMethod.POST)
    @ApiMethod(name = "updatePlan")
    public void updatePlan(){
        updateMySqlService.updatePlan();
    }

    @RequestMapping(path="/updatePlanWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "updatePlanWorkItem")
    public void updatePlanWorkItem(){
        updateMySqlService.updatePlanWorkItem();
    }

    @RequestMapping(path="/updateInsight",method = RequestMethod.POST)
    @ApiMethod(name = "updateInsight")
    public void updateInsight(){
        updateMySqlService.updateInsight();
    }

    @RequestMapping(path="/updateProjectBurndowm",method = RequestMethod.POST)
    @ApiMethod(name = "updateProjectBurndowm")
    public void updateProjectBurndowm(){
        updateMySqlService.updateProjectBurndowm();
    }

    @RequestMapping(path="/updateProjectFocus",method = RequestMethod.POST)
    @ApiMethod(name = "updateProjectFocus")
    public void updateProjectFocus(){
        updateMySqlService.updateProjectFocus();
    }

    @RequestMapping(path="/updateProjectSetFocus",method = RequestMethod.POST)
    @ApiMethod(name = "updateProjectSetFocus")
    public void updateProjectSetFocus(){
        updateMySqlService.updateProjectSetFocus();
    }

    @RequestMapping(path="/updateWorkTypeDm",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkTypeDm")
    public void updateWorkTypeDm(){
        updateMySqlService.updateWorkTypeDm();
    }
}
