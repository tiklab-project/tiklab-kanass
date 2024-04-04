package io.thoughtware.kanass.home.statistic.controller;

import io.thoughtware.core.Result;
import io.thoughtware.kanass.home.statistic.model.SprintBurnDowmChart;
import io.thoughtware.kanass.home.statistic.service.SettingStaticsService;
import io.thoughtware.kanass.home.statistic.service.SprintBurnDowmChartService;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

@RestController
@RequestMapping("/setting")
@Api(name = "SettingStaticsController",desc = "SettingStaticsController")
public class SettingStaticsController {
    private static Logger logger = LoggerFactory.getLogger(SettingStaticsController.class);

    @Autowired
    private SettingStaticsService settingStaticsService;

    @RequestMapping(path="/findOrgaNum",method = RequestMethod.POST)
    @ApiMethod(name = "findOrgaNum",desc = "创建迭代下事项数据动态记录")
    public Result<HashMap<String, Object>> findOrgaNum(){
        HashMap<String, Object> orgaNum = settingStaticsService.findOrgaNum();

        return Result.ok(orgaNum);
    }
}
