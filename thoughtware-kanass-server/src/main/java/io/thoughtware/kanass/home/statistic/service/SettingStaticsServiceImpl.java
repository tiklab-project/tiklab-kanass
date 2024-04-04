package io.thoughtware.kanass.home.statistic.service;


import io.thoughtware.flow.flow.service.FlowService;
import io.thoughtware.flow.statenode.service.StateNodeService;
import io.thoughtware.form.field.service.FieldService;
import io.thoughtware.form.field.service.FieldTypeService;
import io.thoughtware.form.form.service.FormService;
import io.thoughtware.kanass.support.service.SystemUrlService;
import io.thoughtware.kanass.workitem.service.WorkPriorityService;
import io.thoughtware.kanass.workitem.service.WorkTypeService;
import io.thoughtware.licence.appauth.service.ApplyAuthService;
import io.thoughtware.licence.licence.model.Version;
import io.thoughtware.licence.licence.service.VersionService;
import io.thoughtware.message.message.service.MessageNoticeService;
import io.thoughtware.message.setting.service.MessageSendTypeService;
import io.thoughtware.plugin.manager.service.PluginManagerService;
import io.thoughtware.privilege.role.service.RoleService;
import io.thoughtware.security.backups.service.BackupsDbService;
import io.thoughtware.user.directory.service.UserDirService;
import io.thoughtware.user.orga.service.OrgaService;
import io.thoughtware.user.user.service.UserService;
import io.thoughtware.user.usergroup.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SettingStaticsServiceImpl implements SettingStaticsService{
    @Autowired
    UserService userService;

    @Autowired
    OrgaService orgaService;

    @Autowired
    UserGroupService userGroupService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserDirService userDirService;

    @Autowired
    MessageNoticeService messageNoticeService;

    @Autowired
    MessageSendTypeService messageSendTypeService;

    @Autowired
    PluginManagerService pluginManagerService;

    @Autowired
    VersionService versionservice;

    @Autowired
    ApplyAuthService applyAuthService;

    @Autowired
    BackupsDbService backupsDbService;

    @Autowired
    WorkTypeService workTypeService;

    @Autowired
    WorkPriorityService workPriorityService;

    @Autowired
    FieldTypeService fieldTypeService;

    @Autowired
    FieldService fieldService;

    @Autowired
    FormService formService;

    @Autowired
    FlowService flowService;

    @Autowired
    StateNodeService stateNodeService;

    @Autowired
    SystemUrlService systemUrlService;

    @Override
    public HashMap<String, Object> findOrgaNum() {
        HashMap<String, Object> numMap = new HashMap<>();

        // 用户
        Integer userNumber = userService.findUserNumber();
        Integer orgaNumber = orgaService.findOrgaNumber();
        Integer userGroupNumber = userGroupService.findUserGroupNumber();
        Integer roleNumber = roleService.findRoleNumber();
        Integer userDirNumber = userDirService.findUserDirNumber();

        numMap.put("user", userNumber);
        numMap.put("orga", orgaNumber);
        numMap.put("userGroup", userGroupNumber);
        numMap.put("role", roleNumber);
        numMap.put("userDir", userDirNumber);
        // 消息
        Integer messageNoticeNumber = messageNoticeService.findNoticeNumber("kanass");
        Integer sendTypeNumber = messageSendTypeService.findSendTypeNumber();
        numMap.put("messageNotice", messageNoticeNumber);
        numMap.put("sendType", sendTypeNumber);
        //插件
        Integer installPluginNumber = pluginManagerService.findInstallPluginNumber();
        Integer shopPluginNumber = pluginManagerService.findShopPluginNumber();
        numMap.put("installPlugin", installPluginNumber);
        numMap.put("shopPlugin", shopPluginNumber);

        // 系统集成
        // 事项
        Integer workTypeNumber = workTypeService.findAllWorkTypeNum();
        Integer workPriorityNumber = workPriorityService.findAllWorkPriorityNumber();

        numMap.put("workType", workTypeNumber);
        numMap.put("workPriority", workPriorityNumber);
        // 表单
        Integer fieldTypeNumber = fieldTypeService.findAllFieldTypeNumber();
        Integer fieldNumber = fieldService.findAllFieldNumber();
        Integer systemFormNumber = formService.findAllSystemFormNumber();

        numMap.put("fieldType", fieldTypeNumber);
        numMap.put("field", fieldNumber);
        numMap.put("form", systemFormNumber);
        // 流程
        Integer systemFlowNumber = flowService.findSystemFlowNumber();
        Integer stateNodeNumber = stateNodeService.findAllStateNodeNumber();

        numMap.put("flow", systemFlowNumber);
        numMap.put("stateNode", stateNodeNumber);

        // 版本
        Version version = versionservice.getVersion();
        Integer applyAuthNumber = applyAuthService.findApplyAuthNumber();
        numMap.put("version", version);
        numMap.put("applyAuth", applyAuthNumber);

        // 备份
        String lastBackupsTime = backupsDbService.findLastBackupsTime();
        numMap.put("lastBackups", lastBackupsTime);

        // 地址配置
        Integer systemUrlNumber = systemUrlService.findSystemUrlNumber();
        numMap.put("systemUrl", systemUrlNumber);
        return numMap;
    }
}
