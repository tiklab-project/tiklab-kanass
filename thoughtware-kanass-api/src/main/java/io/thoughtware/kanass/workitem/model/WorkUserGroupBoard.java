package io.thoughtware.kanass.workitem.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.user.user.model.User;

import java.util.List;

/**
 * 按人员分组的事项看板模型
 */
@ApiModel
public class WorkUserGroupBoard {

    @ApiProperty(name="user",desc="事项状态")
    private User user;

    @ApiProperty(name="id",desc="事项列表")
    private List<WorkBoard> workBoardList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WorkBoard> getWorkBoardList() {
        return workBoardList;
    }

    public void setWorkBoardList(List<WorkBoard> workBoardList) {
        this.workBoardList = workBoardList;
    }
}
