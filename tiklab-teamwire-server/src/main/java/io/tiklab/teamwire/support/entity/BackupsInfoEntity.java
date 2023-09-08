package io.tiklab.teamwire.support.entity;

import io.tiklab.dal.jpa.annotation.*;

@Entity
@Table(name="pmc_backups_info")
public class BackupsInfoEntity {

    @Id
    @GeneratorValue(length=12)
    @Column(name = "id")
    private String id;

    //定时状态 开启true 关闭:false
    @Column(name = "task_state")
    private String taskState;


    //最后执行的结果
    @Column(name = "last_result")
    private String lastResult;

    //最近执行时间
    @Column(name = "exec_time")
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

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }
}




















































