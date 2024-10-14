package io.tiklab.kanass.support.service;

import io.tiklab.flow.statenode.model.StateNode;
import io.tiklab.flow.statenode.service.StateNodeService;
import io.tiklab.form.field.model.SelectItem;
import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.user.user.model.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportFileServiceImpl implements ExportFileService {
    @Autowired
    WorkItemService workItemService;

    @Autowired
    StateNodeService stateNodeService;

    @Value("${user.home:null}")
    String userHome;


    @Override
    public byte[] exportWorkItemXml(WorkItemQuery workItemQuery) {

        List<WorkItem> workItemList = workItemService.findConditionWorkItemList(workItemQuery);
        Workbook sheets = new HSSFWorkbook();
        Sheet sheet = sheets.createSheet();
        Row row = sheet.createRow(0);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("id");
        strings.add("名称");
        strings.add("类型");
        strings.add("状态");
        strings.add("优先级");
        strings.add("负责人");
        strings.add("创建人");
        strings.add("创建时间");
        strings.add("所属模块");
        strings.add("所属迭代");
        strings.add("计划开始时间");
        strings.add("计划结束时间");
        strings.add("上级事项");
        strings.add("前置事项");
        for (short cellnum = (short) 0; cellnum < 14; cellnum += 1) {
            Cell cell = row.createCell(cellnum);
            cell.setCellValue(strings.get(cellnum));
        }

        ArrayList<String> stringKeys = new ArrayList<>();
        stringKeys.add("id");
        stringKeys.add("title");
        stringKeys.add("workTypeSys");
        stringKeys.add("workStatus");
        stringKeys.add("workPriority");
        stringKeys.add("assigner");
        stringKeys.add("builder");
        stringKeys.add("buildTime");
        stringKeys.add("module");
        stringKeys.add("sprint");
        stringKeys.add("planBeginTime");
        stringKeys.add("planEndTime");
        stringKeys.add("parentWorkItem");
        stringKeys.add("preDependWorkItem");

        Integer integer = new Integer(1);
        for (WorkItem workItem : workItemList) {
            Row workItemRow = sheet.createRow(integer);
            integer++;
            for (short cellnum = (short) 0; cellnum < 14; cellnum += 1) {
                Cell cell = workItemRow.createCell(cellnum);
                String cellValue = getCellValue(cellnum, workItem);
                cell.setCellValue(cellValue);
            }

        }
        try {
            FileOutputStream out = new FileOutputStream(userHome + "/workbook.xls");
            sheets.write(out);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            sheets.write(byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();

            out.close();
            sheets.close();
            return  bytes;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getCellValue(short num, WorkItem workItem){
        String value = new String();
        switch (num){
            case 0:
                value = workItem.getId();
                break;
            case 1:
                value = workItem.getTitle();
                break;
            case 2:
                value = workItem.getWorkTypeSys().getName();
                break;
            case 3:
                String id = workItem.getWorkStatusNode().getId();
                StateNode stateNode = stateNodeService.findStateNode(id);
                value = stateNode.getName();
                break;
            case 4:
                SelectItem workPriority = workItem.getWorkPriority();
                if(!ObjectUtils.isEmpty(workPriority)){
                    value = workPriority.getName();
                }else {
                    value = "";
                }
                break;
            case 5:
                User assigner = workItem.getAssigner();
                if(!ObjectUtils.isEmpty(assigner)){
                    value = assigner.getName();
                }else {
                    value = "";
                }
                break;
            case 6:
                User builder = workItem.getBuilder();
                if(!ObjectUtils.isEmpty(builder)){
                    value = builder.getName();
                }else {
                    value = "";
                }
                break;
            case 7:
                value = workItem.getBuildTime();
                break;
            case 8:
                Module module = workItem.getModule();
                if(!ObjectUtils.isEmpty(module)){
                    value = module.getModuleName();
                }else {
                    value = "";
                }
                break;
            case 9:
                Sprint sprint = workItem.getSprint();
                if(!ObjectUtils.isEmpty(sprint)){
                    value = sprint.getSprintName();
                }else {
                    value = "";
                }
                break;
            case 10:
                value = workItem.getPlanBeginTime();
                break;
            case 11:
                value = workItem.getPlanEndTime();
                break;
            case 12:
                WorkItem parentWorkItem = workItem.getParentWorkItem();
                if(!ObjectUtils.isEmpty(parentWorkItem)){
                    value = parentWorkItem.getTitle();
                }else {
                    value = "";
                }
                break;
            case 13:
                WorkItem preDependWorkItem = workItem.getPreDependWorkItem();
                if(!ObjectUtils.isEmpty(preDependWorkItem)){
                    value = preDependWorkItem.getTitle();
                }else {
                    value = "";
                }
                break;
            default:
                break;
        }

        return value;
    }
}