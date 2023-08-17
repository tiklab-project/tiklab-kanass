package io.tiklab.teamwire.support.service;

import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.teamwire.workitem.model.WorkItemQuery;
import io.tiklab.teamwire.workitem.model.WorkPriority;
import io.tiklab.teamwire.workitem.service.WorkItemService;
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

    @Value("${user.home:null}")
    String userHome;


    @Override
    public byte[] exportWorkItemXml(WorkItemQuery workItemQuery) {

        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
        Workbook sheets = new HSSFWorkbook();
        Sheet sheet = sheets.createSheet();
        Row row = sheet.createRow(0);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("名称");
        strings.add("id");
        strings.add("类型");
        strings.add("优先级");
        strings.add("负责人");
        for (short cellnum = (short) 0; cellnum < 5; cellnum += 1) {
            Cell cell = row.createCell(cellnum);
            cell.setCellValue(strings.get(cellnum));
        }

        ArrayList<String> stringKeys = new ArrayList<>();
        stringKeys.add("title");
        stringKeys.add("id");
        stringKeys.add("workTypeSys");
        stringKeys.add("workPriority");
        stringKeys.add("assigner");

        Integer integer = new Integer(1);
        for (WorkItem workItem : workItemList) {
            Row workItemRow = sheet.createRow(integer);
            integer++;
            for (short cellnum = (short) 0; cellnum < 5; cellnum += 1) {
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
//            response.setContentLength(bytes.length);
//            response.getOutputStream().write(bytes);
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
                value =  workItem.getTitle();
                break;
            case 1:
                value = workItem.getId();
                break;
            case 2:
                value = workItem.getWorkTypeSys().getName();
                break;
            case 3:
                WorkPriority workPriority = workItem.getWorkPriority();
                if(!ObjectUtils.isEmpty(workPriority)){
                    value = workPriority.getName();
                }else {
                    value = "";
                }
                break;
            case 4:
                User assigner = workItem.getAssigner();
                if(!ObjectUtils.isEmpty(assigner)){
                    value = assigner.getName();
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