INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('KANASS_LOGTYPE_WORKITEMCOMMENTADD', '添加事项评论', 'kanass');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('KANASS_LOGTYPE_WORKITEMTAKEUPTIMEADD', '添加事项登记工时', 'kanass');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('KANASS_LOGTYPE_WORKITEMSURPLUSTIMEADD', '添加事项剩余工时', 'kanass');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('KANASS_LOGTYPE_WORKUPDATETAKEUPTIME', '修改事项登记工时', 'kanass');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('KANASS_LOGTYPE_WORKUPDATESURPLUSTIME', '修改事项剩余工时', 'kanass');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('KANASS_LOGTYPE_WORKITEMESTIMATETIMEADD', '添加事项预估工时', 'kanass');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('KANASS_LOGTYPE_WORKUPDATEMESTIMATETIME', '修改事项预估工时', 'kanass');

INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('7a3b9c2d5e1f', '添加事项评论', '<div style=display: flex; align-items: center; font-size: 14px; justify-content: space-between;>
        <div style=display: flex;align-items: center;>
            <div
                style=width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;>
                ${createUserIcon}
            </div>
            <div style=display: flex; flex-direction: column; padding: 0 20px;>
                <div> <span style=padding-right: 10px;> ${master} </span> 创建了${workItemTypeName} </div>
                <div style=line-height: 40px; display: flex; align-items: center; height: 40px;>
                    <div
                        style=font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px; margin-right: 10px;>
                        <img src=${workItemIcon} alt=width=16px height=16px>
                    </div>
                    <div
                        style=color: #5d70ea; cursor: pointer; font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px;>
                        ${workItemTitle} </div>
                </div>
            </div>
        </div>
        <div style=font-size: 13px;> ${createTime} </div>
    </div>', 'index/work/workone/${workItemId}', 'kanass', '${master}添加事项评论');
INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('4d6f8a2b3c5e', '添加事项登记工时', '<div style=display: flex; align-items: center; font-size: 14px; justify-content: space-between;>
        <div style=display: flex;align-items: center;>
            <div
                style=width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;>
                ${createUserIcon}
            </div>
            <div style=display: flex; flex-direction: column; padding: 0 20px;>
                <div> <span style=padding-right: 10px;> ${master} </span> 创建了${workItemTypeName} </div>
                <div style=line-height: 40px; display: flex; align-items: center; height: 40px;>
                    <div
                        style=font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px; margin-right: 10px;>
                        <img src=${workItemIcon} alt=width=16px height=16px>
                    </div>
                    <div
                        style=color: #5d70ea; cursor: pointer; font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px;>
                        ${workItemTitle} </div>
                </div>
            </div>
        </div>
        <div style=font-size: 13px;> ${createTime} </div>
    </div>', 'index/work/workone/${workItemId}', 'kanass', '${master}添加事项登记工时');
INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('3e5f7a9b1c2d', '修改事项登记工时', '<div style=display: flex; align-items: center; font-size: 14px; justify-content: space-between;>
        <div style=display: flex;align-items: center;>
            <div
                style=width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;>
                ${createUserIcon}
            </div>
            <div style=display: flex; flex-direction: column; padding: 0 20px;>
                <div> <span style=padding-right: 10px;> ${master} </span> 创建了${workItemTypeName} </div>
                <div style=line-height: 40px; display: flex; align-items: center; height: 40px;>
                    <div
                        style=font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px; margin-right: 10px;>
                        <img src=${workItemIcon} alt=width=16px height=16px>
                    </div>
                    <div
                        style=color: #5d70ea; cursor: pointer; font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px;>
                        ${workItemTitle} </div>
                </div>
            </div>
        </div>
        <div style=font-size: 13px;> ${createTime} </div>
    </div>', 'index/work/workone/${workItemId}', 'kanass', '${master}修改事项登记工时');
INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('2a4b6c8d0e1f', '添加事项剩余工时', '<div style=display: flex; align-items: center; font-size: 14px; justify-content: space-between;>
        <div style=display: flex;align-items: center;>
            <div
                style=width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;>
                ${createUserIcon}
            </div>
            <div style=display: flex; flex-direction: column; padding: 0 20px;>
                <div> <span style=padding-right: 10px;> ${master} </span> 创建了${workItemTypeName} </div>
                <div style=line-height: 40px; display: flex; align-items: center; height: 40px;>
                    <div
                        style=font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px; margin-right: 10px;>
                        <img src=${workItemIcon} alt=width=16px height=16px>
                    </div>
                    <div
                        style=color: #5d70ea; cursor: pointer; font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px;>
                        ${workItemTitle} </div>
                </div>
            </div>
        </div>
        <div style=font-size: 13px;> ${createTime} </div>
    </div>', 'index/work/workone/${workItemId}', 'kanass', '${master}添加事项剩余工时');
INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('6c8f1a3b5d9e', '修改事项剩余工时', '<div style=display: flex; align-items: center; font-size: 14px; justify-content: space-between;>
        <div style=display: flex;align-items: center;>
            <div
                style=width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;>
                ${createUserIcon}
            </div>
            <div style=display: flex; flex-direction: column; padding: 0 20px;>
                <div> <span style=padding-right: 10px;> ${master} </span> 创建了${workItemTypeName} </div>
                <div style=line-height: 40px; display: flex; align-items: center; height: 40px;>
                    <div
                        style=font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px; margin-right: 10px;>
                        <img src=${workItemIcon} alt=width=16px height=16px>
                    </div>
                    <div
                        style=color: #5d70ea; cursor: pointer; font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px;>
                        ${workItemTitle} </div>
                </div>
            </div>
        </div>
        <div style=font-size: 13px;> ${createTime} </div>
    </div>', 'index/work/workone/${workItemId}', 'kanass', '${master}修改事项剩余工时');
INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('8a0b1c2d3e4f', '添加事项预估工时', '<div style=display: flex; align-items: center; font-size: 14px; justify-content: space-between;>
        <div style=display: flex;align-items: center;>
            <div
                style=width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;>
                ${createUserIcon}
            </div>
            <div style=display: flex; flex-direction: column; padding: 0 20px;>
                <div> <span style=padding-right: 10px;> ${master} </span> 创建了${workItemTypeName} </div>
                <div style=line-height: 40px; display: flex; align-items: center; height: 40px;>
                    <div
                        style=font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px; margin-right: 10px;>
                        <img src=${workItemIcon} alt=width=16px height=16px>
                    </div>
                    <div
                        style=color: #5d70ea; cursor: pointer; font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px;>
                        ${workItemTitle} </div>
                </div>
            </div>
        </div>
        <div style=font-size: 13px;> ${createTime} </div>
    </div>', 'index/work/workone/${workItemId}', 'kanass', '${master}添加事项预估工时');
INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('7b9c3d5e1f2a', '修改事项预估工时', '<div style=display: flex; align-items: center; font-size: 14px; justify-content: space-between;>
        <div style=display: flex;align-items: center;>
            <div
                style=width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;>
                ${createUserIcon}
            </div>
            <div style=display: flex; flex-direction: column; padding: 0 20px;>
                <div> <span style=padding-right: 10px;> ${master} </span> 创建了${workItemTypeName} </div>
                <div style=line-height: 40px; display: flex; align-items: center; height: 40px;>
                    <div
                        style=font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px; margin-right: 10px;>
                        <img src=${workItemIcon} alt=width=16px height=16px>
                    </div>
                    <div
                        style=color: #5d70ea; cursor: pointer; font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px;>
                        ${workItemTitle} </div>
                </div>
            </div>
        </div>
        <div style=font-size: 13px;> ${createTime} </div>
    </div>', 'index/work/workone/${workItemId}', 'kanass', '${master}修改事项预估工时');

UPDATE pmc_project_type SET name = '瀑布式项目' WHERE name = '瀑布研发项目';
UPDATE pmc_project_type SET description = '瀑布式项目' WHERE description = '瀑布研发项目';
INSERT INTO pmc_project_type (id, type, name, icon_url, description) VALUES ('1ca782eb', 'kanban', '看板式项目', 'project3.png', '看板式项目');
INSERT INTO pmc_project_type (id, type, name, icon_url, description) VALUES ('571fc1sa', 'hybrid', '混合式项目', 'project4.png', '混合式项目');
