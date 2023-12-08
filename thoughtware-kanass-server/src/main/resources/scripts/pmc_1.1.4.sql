UPDATE "pcs_todo_task_template" SET "title" = '初始化密码', "link" = NULL, "bgroup" = 'eas', "content" = '${username}请及时修改初始密码。' WHERE "id" = '280485bff145';
UPDATE "pcs_todo_task_template" SET "title" = '待办事项', "link" = 'index/projectDetail/${projectId}/work/${workItemId}', "bgroup" = 'kanass', "content" = '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;">
        <div style="display: flex;align-items: center;flex-shrink: 1;min-width: 0;">
            <div style="width: 25px; height: 25px; line-height: 25px;border-radius: 25px;text-align: center; color: #fff;"  class = "todo-user-icon">${createUserIcon}</div>
            <div style="display: flex;flex-direction: column;padding: 0 20px;flex-shrink: 1;min-width: 0;">
                <div>${createUser.nickname}向您分配了事项 </div>
                <div style="line-height: 30px; display: flex; align-items: center; height: 30px;">
                    <div class="todo-work-title" style="text-overflow: ellipsis;white-space: nowrap;overflow: hidden;">${workItemTitle}</div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;flex-shrink: 0"> ${receiveTime} </div>
    </div>' WHERE "id" = 'ff0a79e4fc61';


		UPDATE "pcs_op_log_template" SET "title" = '事项添加', "content" = ' <div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;">
        <div style="display: flex;align-items: center;">
            <div
                class = "dynamic-user-icon"
                style="width: 25px; height: 25px; line-height: 25px; border-radius: 32px; text-align: center; color: #fff;">
                ${createUserIcon}
            </div>
            <div style="display: flex; flex-direction: column; padding: 0 10px;">
                <div> <span style="padding-right: 10px;">${master}创建了${workItemTypeName} </div>
                <div class="dynamic-work-title"
                        style="cursor: pointer; height: 40px; line-height: 40px; border-radius: 5px;">
                        ${workItemTitle} </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${createTime} </div>
    </div>', "link" = 'projectDetail/${projectId}/work/${workItemId}', "bgroup" = 'kanass', "abstract_content" = '${master}添加事项' WHERE "id" = 'f621925c6e63';
UPDATE "pcs_op_log_template" SET "title" = '项目添加日志', "content" = '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;gap: 20px;">
        <div style="display: flex;align-items: center;gap: 10px;">
            <div
               class = "dynamic-user-icon"
                style="width: 25px; height: 25px; line-height: 25px;   border-radius: 32px;text-align: center; color: #fff;">
                ${createUserIcon}</div>
            <div style="display: flex; flex-direction: column;">
                <div>${master} 创建了项目 </div>
                <div style="line-height: 40px; display: flex; align-items: center; height: 40px;">

                    <div
                        class="dynamic-work-title"
                        style="cursor: pointer; font-size: 12px; height: 15px; line-height: 15px;border-radius: 5px;">
                        ${projectName} </div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${creatTime} </div>
    </div>', "link" = '/index/${projectType}/${projectId}/survey', "bgroup" = 'kanass', "abstract_content" = '${master}添加项目' WHERE "id" = 'c5613dbc2726';
UPDATE "pcs_op_log_template" SET "title" = '事项负责人修改', "content" = '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between; gap: 20px;">
        <div style="display: flex;align-items: center; gap: 10px; flex-shrink: 1;min-width: 0;">
            <div
                class = "dynamic-user-icon"
                style="width: 25px; height: 25px; line-height: 25px;
                border-radius: 32px;text-align: center; color: #fff; flex-shrink: 0;">
                ${createUserIcon}</div>
            <div style="display: flex; flex-direction: column; flex-shrink: 1;min-width: 0;">
                <div style="line-height: 30px;">${master.nickname}更新了负责人</div>
                <div style="line-height: 30px; display: flex; align-items: center; height: 40px;">
                    <div class="dynamic-work-title" style="
                    text-overflow: ellipsis;
                    white-space: nowrap;
                    overflow: hidden;
                    flex: 1;
                    margin-right: 10px;
                    cursor: pointer;
                    max-width: 400px;
                    cursor: pointer;
                "> ${workItemTitle}</div>
                    <div
                        style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px;border-radius: 5px; margin-right: 10px;">
                        ${oldValue. nickname} </div> ——— <div
                        style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px; border-radius: 5px;margin-left: 10px;">
                        ${newValue.nickname} </div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px; flex-shrink: 0;"> ${receiveTime} </div>
    </div>', "link" = 'projectDetail/${projectId}/work/${workItemId}', "bgroup" = 'kanass', "abstract_content" = '${master.nickname}更换负责人' WHERE "id" = '4c4d9114ddc0';
UPDATE "pcs_op_log_template" SET "title" = '事项状态修改', "content" = '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between; gap: 20px;">
        <div style="display: flex;align-items: center; gap: 10px; flex-shrink: 1;min-width: 0;">
            <div
                class = "dynamic-user-icon"
                style="width: 25px; height: 25px; line-height: 25px;
                border-radius: 32px;text-align: center; color: #fff;">
                ${createUserIcon}</div>
            <div style="display: flex; flex-direction: column; flex-shrink: 1;min-width: 0;">
                <div style="line-height: 30px;"> ${master.nickname}更新了状态 </div>
                <div style="line-height: 30px; display: flex; align-items: center; height: 40px;">
                    <div class="dynamic-work-title" style="
                    text-overflow: ellipsis;
                    white-space: nowrap;
                    overflow: hidden;
                    flex: 1;
                    margin-right: 10px;
                    cursor: pointer;
                    max-width: 400px
                ">  ${workItemTitle}</div>
                    <div
                        style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px;border-radius: 5px; margin-right: 10px;">
                        ${oldValue. name} </div> ——— <div
                        style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px; border-radius: 5px;margin-left: 10px;">
                        ${newValue. name} </div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px; flex-shrink: 0;"> ${receiveTime} </div>
    </div>', "link" = 'index/projectDetail/${projectId}/work/${workItemId}', "bgroup" = 'kanass', "abstract_content" = '${master.nickname}更新了状态' WHERE "id" = '31d39e6f981a';