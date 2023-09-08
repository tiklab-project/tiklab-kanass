package io.tiklab.teamwire.support.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.io.Serializable;

@ApiModel
@Mapper
public class BackupsInfo implements Serializable {

    @ApiProperty(name="id",desc="仓库id")
    private String id;

    @ApiProperty(name="taskState",desc="定时开启状态 true、false")
    private String taskState;


    @ApiProperty(name="lastResult",desc="最后执行结果 未执行：non 失败：fail 成功：success")
    private String lastResult;


    @ApiProperty(name="execTime",desc="执行时间")
    private String execTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }
}







































