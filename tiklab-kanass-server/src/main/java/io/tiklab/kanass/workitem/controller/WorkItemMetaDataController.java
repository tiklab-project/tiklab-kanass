package io.tiklab.kanass.workitem.controller;

import io.tiklab.core.Result;
import io.tiklab.kanass.workitem.model.WorkItemCreateMetaData;
import io.tiklab.kanass.workitem.model.WorkItemCreateMetaDataQuery;
import io.tiklab.kanass.workitem.model.WorkItemDetailMetaData;
import io.tiklab.kanass.workitem.model.WorkItemDetailMetaDataQuery;
import io.tiklab.kanass.workitem.service.WorkItemCreateMetaDataService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/workItemMetaData")
//@Api(name = "WorkItemMetaDataController",desc = "事项元数据管理")
public class WorkItemMetaDataController {

    @Autowired
    private WorkItemCreateMetaDataService workItemCreateMetaDataService;

    @RequestMapping(path="/findCreateMetaData",method = RequestMethod.POST)
    //@ApiMethod(name = "findCreateMetaData",desc = "创建事项")
    //@ApiParam(name = "query",desc = "查询参数",required = true)
    public Result<WorkItemCreateMetaData> findCreateMetaData(@RequestBody @Valid @NotNull WorkItemCreateMetaDataQuery query){
        WorkItemCreateMetaData createMetaData = workItemCreateMetaDataService.findCreateMetaData(query);
        return Result.ok(createMetaData);
    }

    @RequestMapping(path="/findDetailMetaData",method = RequestMethod.POST)
    //@ApiMethod(name = "findDetailMetaData",desc = "事项详情")
    //@ApiParam(name = "query",desc = "查询参数",required = true)
    public Result<WorkItemDetailMetaData> findDetailMetaData(@RequestBody @Valid @NotNull WorkItemDetailMetaDataQuery query){
        WorkItemDetailMetaData detailMetaData = workItemCreateMetaDataService.findDetailMetaData(query);
        return Result.ok(detailMetaData);
    }

}
