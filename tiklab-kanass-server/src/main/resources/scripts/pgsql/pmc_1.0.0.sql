
CREATE TABLE pmc_project_type(
        id VARCHAR(8) PRIMARY KEY,
        type VARCHAR(12) NOT NULL,
        name VARCHAR(64) NOT NULL,
        icon_url VARCHAR(32),
        description TEXT
);

CREATE TABLE pmc_project(
        id VARCHAR(12) PRIMARY KEY,
        project_key VARCHAR(12) NOT NULL,
        project_name VARCHAR(64) NOT NULL,
        project_type_id VARCHAR(8) NOT NULL,
        project_limits VARCHAR(8) NOT NULL,
        creator VARCHAR(12) NOT NULL,
        master VARCHAR(12),
        description TEXT,
        project_set_id VARCHAR(12),
        project_state VARCHAR(8),
        start_time timestamp,
        end_time timestamp,
        icon_url VARCHAR(64) NOT NULL
);


CREATE TABLE pmc_module(
        id VARCHAR(12) PRIMARY KEY,
        module_name VARCHAR(64) NOT NULL,
        description TEXT,
        parent_id VARCHAR(12),
        project_id VARCHAR(12) NOT NULL

);


CREATE TABLE pmc_sprint(
        id VARCHAR(12) PRIMARY KEY,
        sprint_name VARCHAR(64) NOT NULL,
        description TEXT,
        project_id VARCHAR(12) NOT NULL,
        master VARCHAR(12),
        sprint_state_id varchar (12),
        start_time timestamp,
        end_time  timestamp,
        builder VARCHAR(12),
        rela_start_time timestamp,
        rela_end_time timestamp,
        color INT
);


CREATE TABLE pmc_sprint_status(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) ,
        description VARCHAR(64),
        sort integer,
        grouper VARCHAR(32)
);

CREATE TABLE pmc_version(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        master VARCHAR(12) NOT NULL,
        publish_time timestamp NOT NULL,
        start_time timestamp NOT NULL,
        project_id VARCHAR(12) NOT NULL,
        version_state VARCHAR(32) NOT NULL,
        rela_publish_time timestamp,
        builder VARCHAR(12),
        rela_start_time timestamp,
        color INT


);
CREATE TABLE pmc_version_focus(
        id VARCHAR(12) PRIMARY KEY,
        version_id VARCHAR(12),
        master_id VARCHAR(12),
        project_id VARCHAR(12),
        sort int
);

CREATE TABLE pmc_version_status(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) ,
        description VARCHAR(64),
        sort integer,
        grouper VARCHAR(32)
);

CREATE TABLE pmc_work_item(
        id VARCHAR(16) PRIMARY KEY,
        order_num int,
        root_id VARCHAR(16),
        title VARCHAR(128) NOT NULL,
        description TEXT,
        parent_id VARCHAR(16),
        tree_path VARCHAR(2048),
        project_id VARCHAR(12) NOT NULL,
        work_type_id VARCHAR(8) NOT NULL,
        work_priority_id VARCHAR(8) ,
        work_status_id VARCHAR(8) ,
        module_id VARCHAR(12),
        sprint_id VARCHAR(12),
        version_id VARCHAR(12),
        pre_depend_id VARCHAR (16),
        builder_id VARCHAR(12),
        assigner_id VARCHAR(12),
        reporter_id VARCHAR(12),
        plan_begin_time timestamp,
        plan_end_time timestamp,
        actual_begin_time timestamp,
        actual_end_time timestamp,
        percent int,
        estimate_time int,
        ext_data TEXT,
        build_time timestamp NOT NULL,
        work_status_code VARCHAR(8),
        work_type_code VARCHAR(8),
        surplus_time int,
        work_type_sys_id VARCHAR(8) NOT NULL,
        work_status_node_id VARCHAR(8) NOT NULL,
        each_type VARCHAR(64),
        update_time timestamp,
        code VARCHAR(32),
        stage_id VARCHAR(12)
);


CREATE TABLE pmc_work_item_document(
        id VARCHAR(12) PRIMARY KEY,
        work_item_id VARCHAR(16) NOT NULL,
        document_id VARCHAR(12) NOT NULL,
        repository_id VARCHAR(12) NOT NULL,
        sort int,
        project_id VARCHAR(12)
);


CREATE TABLE pmc_work_attach(
        id VARCHAR(12) PRIMARY KEY,
        work_item_id VARCHAR(16) NOT NULL,
        attachmentName VARCHAR(64) NOT NULL,
        attachmentUrl VARCHAR(128) NOT NULL,
        type VARCHAR(32) NOT NULL,
        sort int
);

CREATE TABLE pmc_work_log(
        id VARCHAR(12) PRIMARY KEY,
        work_item_id VARCHAR(16) NOT NULL,
        worker VARCHAR(12) NOT NULL,
        work_date timestamp NOT NULL,
        takeup_time int NOT NULL,
        work_content TEXT NOT NULL,
        creat_time timestamp,
        update_time timestamp,
        project_id VARCHAR(12)
);

CREATE TABLE pmc_work_priority(
        id VARCHAR(8) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        description VARCHAR(64),
        sort int,
        grouper VARCHAR(12) NOT NULL,
        icon_url VARCHAR(64)
);


CREATE TABLE pmc_work_relate(
        id VARCHAR(12) PRIMARY KEY,
        work_item_id VARCHAR(16) NOT NULL,
        relate_item_id VARCHAR(16) NOT NULL,
        create_time timestamp
);

CREATE TABLE pmc_work_status(
        id VARCHAR(8) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        description VARCHAR(2048),
        sort int,
        grouper VARCHAR(32) NOT NULL
);


CREATE TABLE pmc_work_type(
        id VARCHAR(8) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        description VARCHAR(2048),
        code VARCHAR(64),
        form_id VARCHAR(8),
        flow_id VARCHAR(8) NOT NULL,
        icon_url VARCHAR(64),
        grouper VARCHAR(64) NOT NULL,
        sort  VARCHAR(64),
        scope int NOT NULL

);


CREATE TABLE pmc_project_set(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        start_time timestamp NOT NULL,
        end_time timestamp NOT NULL,
        creator VARCHAR(12) NOT NULL,
        project_set_limits VARCHAR(12) NOT NULL,
        remark VARCHAR(2048),
        sort int NOT NULL,
        master VARCHAR(12) NOT NULL,
        is_delete int,
        creat_time timestamp,
        update_time timestamp,
        color int NOT NULL
);


CREATE TABLE pmc_epic(
        id VARCHAR(12) PRIMARY KEY,
        epic_name VARCHAR(64) NOT NULL,
        master VARCHAR(12),
        description VARCHAR(64),
        project_id VARCHAR(12) NOT NULL,
        parent_id VARCHAR(12),
        status VARCHAR(8) NOT NULL,
        progress int NOT NULL,
        start_time timestamp,
        end_time timestamp

);


CREATE TABLE pmc_epic_work_item(
        id VARCHAR(12) PRIMARY KEY,
        work_item_id VARCHAR(26) NOT NULL,
        epic_id VARCHAR(12) NOT NULL
);


CREATE TABLE pmc_icon(
        id VARCHAR(12) PRIMARY KEY,
        icon_name VARCHAR(64) NOT NULL,
        icon_url VARCHAR(64) NOT NULL,
        icon_type VARCHAR(32)
);


CREATE TABLE pmc_dynamic(
        id VARCHAR(12) NOT NULL PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        model VARCHAR(64) NOT NULL,
        model_id VARCHAR(16) NOT NULL,
        dynamic_type VARCHAR(64) NOT NULL,
        master_id VARCHAR(12) NOT NULL,
        dynamic_time timestamp NOT NULL,
        project_id VARCHAR(12) NOT NULL,
        data VARCHAR(4096)
);


CREATE TABLE pmc_work_comment(
        id VARCHAR(12) PRIMARY KEY,
        work_item_id VARCHAR(16) NOT NULL,
        details TEXT NOT NULL,
        user_id VARCHAR(12) NOT NULL,
        create_time timestamp,
        update_time timestamp
);


CREATE TABLE pmc_report(
        id VARCHAR(12) NOT NULL PRIMARY KEY,
        title VARCHAR(64) NOT NULL,
        report_type VARCHAR(64) NOT NULL,
        report_data  VARCHAR(2048) NOT NULL,
        project_id VARCHAR(12) NOT NULL,
        creater_id VARCHAR(12) NOT NULL,
        creater_time VARCHAR(64) NOT NULL,
        sprint_id VARCHAR(12),
        type VARCHAR(64) NOT NULL
);

CREATE TABLE pmc_recent(
        id VARCHAR(12) NOT NULL PRIMARY KEY,
        name VARCHAR(128) NOT NULL,
        model VARCHAR(64) NOT NULL,
        model_id VARCHAR(16) NOT NULL,
        master_id VARCHAR(12) NOT NULL,
        project_id VARCHAR(12),
        recent_time timestamp NOT NULL,
        icon_url VARCHAR(64),
        project_type_id VARCHAR(8)
);

CREATE TABLE pmc_sprint_focus(
        id VARCHAR(12) PRIMARY KEY,
        sprint_id VARCHAR(12),
        master_id VARCHAR(12),
        project_id VARCHAR(12),
        sort int
);


CREATE TABLE pmc_sprint_burndowm(
        id VARCHAR(12) NOT NULL PRIMARY KEY,
        sprint_id VARCHAR(12) NOT NULL,
        record_time timestamp,
        remain_workitem_count int,
        total_workitem_count int,
        end_workitem_count int,
        progress_workitem_count int,
        nostart_workitem_count int,
        remain_bug_count int,
        total_bug_count int,
        end_bug_count int,
        progress_bug_count int,
        nostart_bug_count int,
        remain_demand_count int,
        total_demand_count int,
        end_demand_count int,
        progress_demand_count int,
        nostart_demand_count int,
        remain_task_count int,
        total_task_count int,
        end_task_count int,
        progress_task_count int,
        nostart_task_count int
);


CREATE TABLE pmc_milestone(
        id VARCHAR(12) PRIMARY KEY,
        project_id VARCHAR(12) NOT NULL,
        name VARCHAR(64) NOT NULL,
        milestone_time timestamp,
        master VARCHAR(12),
        milestone_status VARCHAR (8) NOT NULL
);

CREATE TABLE pmc_plan(
        id VARCHAR(12) PRIMARY KEY,
        plan_name VARCHAR(64) NOT NULL,
        project_id VARCHAR(12) NOT NULL,
        start_time timestamp,
        end_time timestamp,
        plan_state VARCHAR(8),
        master VARCHAR(12),
        parent_id VARCHAR(12),
        description VARCHAR(64)
);


CREATE TABLE pmc_plan_work_item(
        id VARCHAR(12) PRIMARY KEY,
        work_item_id VARCHAR(16) NOT NULL,
        plan_id VARCHAR(12) NOT NULL
);


CREATE TABLE pmc_insight(
        id VARCHAR(12) PRIMARY KEY,
        insight_name VARCHAR(64) NOT NULL,
        insight_group VARCHAR(12) NOT NULL,
        master VARCHAR(12),
        createdtime timestamp,
        data TEXT
);


CREATE TABLE pmc_project_burndowm (
        id VARCHAR(12) PRIMARY KEY,
        project_id varchar(12) NOT NULL,
        record_time timestamp ,
        remain_workitem_count int,
        total_workitem_count int,
        end_workitem_count int,
        progress_workitem_count int,
        nostart_workitem_count int,
        remain_bug_count int,
        total_bug_count int,
        end_bug_count int,
        progress_bug_count int,
        nostart_bug_count int,
        remain_demand_count int,
        total_demand_count int,
        end_demand_count int,
        progress_demand_count int ,
        nostart_demand_count int,
        remain_task_count int,
        total_task_count int,
        end_task_count int,
        progress_task_count int,
        nostart_task_count int
);

CREATE TABLE pmc_project_focus(
        id VARCHAR(12) PRIMARY KEY,
        project_id VARCHAR(12),
        master_id VARCHAR(12),
        sort int
);


CREATE TABLE pmc_project_set_focus(
        id VARCHAR(12) PRIMARY KEY,
        project_set_id VARCHAR(12),
        master_id VARCHAR(12),
        sort int
);

CREATE TABLE pmc_work_type_dm(
        id VARCHAR(8) PRIMARY KEY,
        project_id VARCHAR(12) NOT NULL,
        work_type_id VARCHAR(8) NOT NULL,
        form_id VARCHAR(8),
        flow_id VARCHAR(8) NOT NULL
);

CREATE TABLE pmc_stage(
        id VARCHAR(12) PRIMARY KEY,
        stage_name VARCHAR(64) NOT NULL,
        parent_id VARCHAR(12),
        status VARCHAR(8) NOT NULL,
        progress int NOT NULL,
        master VARCHAR(12),
        description VARCHAR(64),
        project_id VARCHAR(12) NOT NULL,
        start_time timestamp,
        end_time timestamp,
        tree_path TEXT,
        root_id VARCHAR(12),
        deep int,
        color INT
);


CREATE TABLE pmc_stage_work_item(
        id VARCHAR(12) PRIMARY KEY,
        work_item_id VARCHAR(3162) NOT NULL,
        stage_id VARCHAR(12) NOT NULL
);

CREATE TABLE pmc_system_url(
        id VARCHAR(8) PRIMARY KEY,
        name VARCHAR(32) NOT NULL,
        system_url VARCHAR(64) NOT NULL,
        web_url VARCHAR(64)
);

CREATE TABLE pmc_project_wiki_repository(
        id VARCHAR(12) PRIMARY KEY,
        wiki_repository_id VARCHAR(12) NOT NULL,
        project_id VARCHAR(12) NOT NULL,
        sort int
);

CREATE TABLE pmc_project_test_repository(
        id VARCHAR(12) PRIMARY KEY,
        test_repository_id VARCHAR(12) NOT NULL,
        project_id VARCHAR(12) NOT NULL,
        sort int
);


CREATE TABLE pmc_work_test_case(
        id VARCHAR(12) PRIMARY KEY,
        work_item_id VARCHAR(16) NOT NULL,
        test_case_id VARCHAR(12) NOT NULL,
        repository_id VARCHAR(12),
        sort int,
        project_id VARCHAR(12)
);

CREATE TABLE pmc_insight_focus(
        id VARCHAR(12) PRIMARY KEY,
        insight_id VARCHAR(12),
        master_id VARCHAR(12),
        sort int
);

CREATE TABLE pmc_version_burndowm(
        id VARCHAR(12) NOT NULL PRIMARY KEY,
        version_id VARCHAR(12) NOT NULL,
        record_time timestamp,
        remain_workitem_count int,
        total_workitem_count int,
        end_workitem_count int,
        progress_workitem_count int,
        nostart_workitem_count int,
        remain_bug_count int,
        total_bug_count int,
        end_bug_count int,
        progress_bug_count int,
        nostart_bug_count int,
        remain_demand_count int,
        total_demand_count int,
        end_demand_count int,
        progress_demand_count int,
        nostart_demand_count int,
        remain_task_count int,
        total_task_count int,
        end_task_count int,
        progress_task_count int,
        nostart_task_count int
);
CREATE TABLE pmc_work_sprint(
    id VARCHAR(12) PRIMARY KEY,
    work_item_id VARCHAR(16) NOT NULL,
    sprint_id VARCHAR(12) NOT NULL
);

CREATE TABLE pmc_work_version(
    id VARCHAR(12) PRIMARY KEY,
    work_item_id VARCHAR(16) NOT NULL,
    version_id VARCHAR(12) NOT NULL
);

CREATE TABLE pmc_stage_burndowm(
        id VARCHAR(12) NOT NULL PRIMARY KEY,
        stage_id VARCHAR(12) NOT NULL,
        record_time timestamp,
        remain_workitem_count int,
        total_workitem_count int,
        end_workitem_count int,
        progress_workitem_count int,
        nostart_workitem_count int,
        remain_bug_count int,
        total_bug_count int,
        end_bug_count int,
        progress_bug_count int,
        nostart_bug_count int,
        remain_demand_count int,
        total_demand_count int,
        end_demand_count int,
        progress_demand_count int,
        nostart_demand_count int,
        remain_task_count int,
        total_task_count int,
        end_task_count int,
        progress_task_count int,
        nostart_task_count int
);

ALTER TABLE pmc_work_type_dm ALTER COLUMN form_id DROP NOT NULL;