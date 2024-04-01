package io.thoughtware.kanass.home.statistic.service;


import io.thoughtware.flow.flow.service.FlowService;
import io.thoughtware.flow.statenode.service.StateNodeService;
import io.thoughtware.form.field.service.FieldService;
import io.thoughtware.form.field.service.FieldTypeService;
import io.thoughtware.form.form.service.FormService;
import io.thoughtware.kanass.workitem.service.WorkPriorityService;
import io.thoughtware.kanass.workitem.service.WorkTypeService;
import io.thoughtware.privilege.role.service.RoleService;
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

    @Override
    public HashMap<String, Integer> findOrgaNum() {
        HashMap<String, Integer> numMap = new HashMap<>();
        Integer userNumber = userService.findUserNumber();
        Integer orgaNumber = orgaService.findOrgaNumber();
        Integer userGroupNumber = userGroupService.findUserGroupNumber();
        Integer roleNumber = roleService.findRoleNumber();
        Integer userDirNumber = userDirService.findUserDirNumber();
        Integer workTypeNumber = workTypeService.findAllWorkTypeNum();
        Integer workPriorityNumber = workPriorityService.findAllWorkPriorityNumber();
        Integer fieldTypeNumber = fieldTypeService.findAllFieldTypeNumber();
        Integer fieldNumber = fieldService.findAllFieldNumber();
        Integer systemFormNumber = formService.findAllSystemFormNumber();
        Integer systemFlowNumber = flowService.findSystemFlowNumber();
        Integer stateNodeNumber = stateNodeService.findAllStateNodeNumber();

        numMap.put("user", userNumber);
        numMap.put("orga", orgaNumber);
        numMap.put("userGroup", userGroupNumber);
        numMap.put("role", roleNumber);
        numMap.put("userDir", userDirNumber);
        numMap.put("workType", workTypeNumber);
        numMap.put("workPriority", workPriorityNumber);
        numMap.put("fieldType", fieldTypeNumber);
        numMap.put("field", fieldNumber);
        numMap.put("form", systemFormNumber);
        numMap.put("flow", systemFlowNumber);
        numMap.put("stateNode", stateNodeNumber);
        return numMap;
    }
}
