package io.tiklab.kanass.project.appraised.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.appraised.model.AppraisedType;
import io.tiklab.kanass.project.appraised.model.AppraisedTypeQuery;
import io.tiklab.kanass.project.appraised.service.AppraisedTypeService;
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
@RequestMapping("/appraisedType")
public class AppraisedTypeController {
    private static Logger logger = LoggerFactory.getLogger(AppraisedTypeController.class);

    @Autowired
    private AppraisedTypeService appraisedTypeService;


    @RequestMapping(path="/createAppraisedType",method = RequestMethod.POST)
    public Result<String> createAppraisedType(@RequestBody @NotNull @Valid AppraisedType appraisedType){
        String id = appraisedTypeService.createAppraisedType(appraisedType);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateAppraisedType",method = RequestMethod.POST)
    public Result<Void> updateAppraisedType(@RequestBody @NotNull @Valid AppraisedType appraisedType){
        appraisedTypeService.updateAppraisedType(appraisedType);

        return Result.ok();
    }


    @RequestMapping(path="/deleteAppraisedType",method = RequestMethod.POST)
    public Result<Void> deleteAppraisedType(@NotNull String id){
        appraisedTypeService.deleteAppraisedType(id);

        return Result.ok();
    }


    @RequestMapping(path="/findAppraisedType",method = RequestMethod.POST)
    public Result<AppraisedType> findAppraisedType(@NotNull String id){
        AppraisedType appraisedType = appraisedTypeService.findAppraisedType(id);

        return Result.ok(appraisedType);
    }

    @RequestMapping(path="/findAllAppraisedType",method = RequestMethod.POST)
    public Result<List<AppraisedType>> findAllAppraisedType(){
        List<AppraisedType> appraisedTypeList = appraisedTypeService.findAllAppraisedType();

        return Result.ok(appraisedTypeList);
    }


    @RequestMapping(path = "/findAppraisedTypeList",method = RequestMethod.POST)
    public Result<List<AppraisedType>> findAppraisedTypeList(@RequestBody @Valid @NotNull AppraisedTypeQuery appraisedTypeQuery){
        List<AppraisedType> appraisedTypeList = appraisedTypeService.findAppraisedTypeList(appraisedTypeQuery);

        return Result.ok(appraisedTypeList);
    }


    @RequestMapping(path = "/findAppraisedTypePage",method = RequestMethod.POST)
    public Result<Pagination<AppraisedType>> findAppraisedTypePage(@RequestBody @Valid @NotNull AppraisedTypeQuery appraisedTypeQuery){
        Pagination<AppraisedType> pagination = appraisedTypeService.findAppraisedTypePage(appraisedTypeQuery);

        return Result.ok(pagination);
    }
}
