package io.tiklab.kanass.starter.config;


import io.tiklab.dsm.model.DsmConfig;
import io.tiklab.dsm.model.DsmVersion;
import io.tiklab.dsm.support.DsmVersionBuilder;
import io.tiklab.kanass.support.sql.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class KanassDsmAutoConfiguration {

    @Autowired
    Pmc159Task pmc159Task;

    @Autowired
    Pmc164PrivilegeTask pmc164PrivilegeTask;

    @Autowired
    Pmc166ProjectPrivilegeTask pmc166ProjectPrivilegeTask;

    @Autowired
    KanassMessage100Task kanassMessage100Task;

    @Autowired
    KanassMessage101Task kanassMessage101Task;

    @Autowired
    Pmc168WorkCodeTask pmc168WorkCodeTask;

    @Autowired
    Pmc169ReportDescTask pmc169ReportDescTask;

    @Autowired
    KanassProjectPrivilege100Task kanassProjectPrivilege100Task;

    @Autowired
    KanassProjectPrivilege101Task kanassProjectPrivilege101Task;

    @Autowired
    KanassTest100Task kanassTest100Task;

    @Bean
    DsmConfig dsmConfig(){
        DsmConfig dsmConfig = new DsmConfig();

        dsmConfig.setVersionList(versionList());
        return dsmConfig;
    }

    /**
     * 初始化dsm
     */
    @Bean
    List<DsmVersion> versionList() {
        List<DsmVersion> versionList = new ArrayList<>();
        DsmVersion dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.0")
                .db(new String[]{
                        "openapi_1.0.0",
                        "privilege_1.0.0",
                        "user_1.0.0",
                        "app-authorization_1.0.0",
                        "message_1.0.0",
                        "oplog_1.0.0",
                        "todotask_1.0.0",

                        //FLOW
                        "flow_1.0.0",
                        //FORM
                        "form_1.0.0",
                        //TeamWireDsm
                        "pmc_1.0.0",

                })
                .dfs(new String[]{
                        "data_1.0.0"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.1")
                .db(new String[]{
                        "flow_1.0.1",
                        "form_1.0.1",
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.2")
                .db(new String[]{
                        "flow_1.0.2",
                        "form_1.0.2"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.3")
                .db(new String[]{
                        "flow_1.0.3",
                        "form_1.0.3",
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.4")
                .db(new String[]{
                        "flow_1.0.4",
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.5")
                .db(new String[]{
                        "flow_1.0.5",
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.4.2")
                .db(new String[]{
                        "pmc_1.4.2"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.4.3")
                .db(new String[]{
                        "pmc_1.4.3"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.4.5")
                .db(new String[]{
                        "pmc_1.4.5"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.4.6")
                .db(new String[]{
                        "pmc_1.4.6"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.4.7")
                .db(new String[]{
                        "pmc_1.4.7"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.4.8")
                .db(new String[]{
                        "pmc_1.4.8"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.4.9")
                .db(new String[]{
                        "pmc_1.4.9"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.5.0")
                .db(new String[]{
                        "pmc_1.5.0"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.5.1")
                .db(new String[]{
                        "pmc_1.5.1"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.5.2")
                .db(new String[]{
                        "pmc_1.5.2"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.5.3")
                .db(new String[]{
                        "pmc_1.5.3"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.5.4")
                .db(new String[]{
                        "pmc_1.5.4"
                })
                .get();
        versionList.add(dsmVersion);

        DsmVersion message_109 = DsmVersionBuilder.instance()
                .version("message_1.0.9")
                .db(new String[]{
                        "message_1.0.9",
                }).get();
        versionList.add(message_109);

        DsmVersion pmc_155 = DsmVersionBuilder.instance()
                .version("pmc_1.5.5")
                .db(new String[]{
                        "pmc_1.5.5",
                }).get();
        versionList.add(pmc_155);

        DsmVersion pmc_156 = DsmVersionBuilder.instance()
                .version("pmc_1.5.6")
                .db(new String[]{
                        "pmc_1.5.6",
                }).get();
        versionList.add(pmc_156);

        DsmVersion pmc_157 = DsmVersionBuilder.instance()
                .version("pmc_1.5.7")
                .db(new String[]{
                        "pmc_1.5.7",
                }).get();
        versionList.add(pmc_157);

        DsmVersion pmc_158 = DsmVersionBuilder.instance()
                .version("pmc_1.5.8")
                .db(new String[]{
                        "pmc_1.5.8",
                }).get();
        versionList.add(pmc_158);

        DsmVersion pmc_159 = DsmVersionBuilder.instance()
                .version("pmc_1.5.9")
                .db(new String[]{
                        "pmc_1.5.9",
                }).get();
        versionList.add(pmc_159);

        DsmVersion pmc_160 = DsmVersionBuilder.instance()
                .version("pmc_1.6.0")
                .db(new String[]{
                        "pmc_1.6.0",
                }).get();
        versionList.add(pmc_160);

        DsmVersion pmc_161 = DsmVersionBuilder.instance()
                .version("pmc_1.6.1")
                .db(new String[]{
                        "pmc_1.6.1",
                }).get();
        versionList.add(pmc_161);

        DsmVersion pmc_162 = DsmVersionBuilder.instance()
                .version("pmc_1.6.2")
                .db(new String[]{
                        "pmc_1.6.2",
                }).get();
        versionList.add(pmc_162);

        DsmVersion pmc_163 = DsmVersionBuilder.instance()
                .version("pmc_1.6.3")
                .db(new String[]{
                        "pmc_1.6.3",
                }).get();
        versionList.add(pmc_163);

        DsmVersion privilege_gorup_100 = DsmVersionBuilder.instance()
                .version("privilege-gorup_1.0.0")
                .db(new String[]{
                        "privilege-gorup_1.0.0",
                }).get();
        versionList.add(privilege_gorup_100);

        DsmVersion pmc_164_privilege = DsmVersionBuilder.instance()
                .version("pmc_1.6.4_privilege")
                .db(new String[]{
                        "pmc_1.6.4_privilege",
                }).get();
        versionList.add(pmc_164_privilege);

        DsmVersion pmc_165 = DsmVersionBuilder.instance()
                .version("pmc_1.6.5")
                .db(new String[]{
                        "pmc_1.6.5",
                }).get();
        versionList.add(pmc_165);

        DsmVersion pmc_166_project_privilege = DsmVersionBuilder.instance()
                .version("pmc_1.6.6_project_privilege")
                .db(new String[]{
                        "pmc_1.6.6_project_privilege",
                }).get();
        versionList.add(pmc_166_project_privilege);

        DsmVersion user_200 = DsmVersionBuilder.instance()
                .version("user_2.0.0")
                .db(new String[]{
                        "user_2.0.0",
                }).get();
        versionList.add(user_200);

        DsmVersion user_201 = DsmVersionBuilder.instance()
                .version("user_2.0.1")
                .db(new String[]{
                        "user_2.0.1",
                }).get();
        versionList.add(user_201);

        DsmVersion kanass_message_100 = DsmVersionBuilder.instance()
                .version("kanass_message_1.0.0")
                .db(new String[]{
                        "kanass_message_1.0.0",
                }).get();
        versionList.add(kanass_message_100);

        DsmVersion kanass_message_101 = DsmVersionBuilder.instance()
                .version("kanass_message_1.0.1")
                .db(new String[]{
                        "kanass_message_1.0.1",
                }).get();
        versionList.add(kanass_message_101);

        DsmVersion message_200 = DsmVersionBuilder.instance()
                .version("message_2.0.0")
                .db(new String[]{
                        "message_2.0.0",
                }).get();
        versionList.add(message_200);

        DsmVersion pmc_167 = DsmVersionBuilder.instance()
                .version("pmc_1.6.7")
                .db(new String[]{
                        "pmc_1.6.7",
                }).get();
        versionList.add(pmc_167);

        DsmVersion kanass_test_100 = DsmVersionBuilder.instance()
                .version("kanass_test_1.0.0")
                .db(new String[]{
                        "kanass_test_1.0.0",
                }).get();
        versionList.add(kanass_test_100);

        DsmVersion kanass_flow_100 = DsmVersionBuilder.instance()
                .version("kanass_flow_1.0.0")
                .db(new String[]{
                        "kanass_flow_1.0.0",
                }).get();
        versionList.add(kanass_flow_100);

        DsmVersion kanass_flow_101 = DsmVersionBuilder.instance()
                .version("kanass_flow_1.0.1")
                .db(new String[]{
                        "kanass_flow_1.0.1",
                }).get();
        versionList.add(kanass_flow_101);

        DsmVersion kanass_flow_102 = DsmVersionBuilder.instance()
                .version("kanass_flow_1.0.2")
                .db(new String[]{
                        "kanass_flow_1.0.2",
                }).get();
        versionList.add(kanass_flow_102);

        DsmVersion kanass_message_102 = DsmVersionBuilder.instance()
                .version("kanass_message_1.0.2")
                .db(new String[]{
                        "kanass_message_1.0.2",
                }).get();
        versionList.add(kanass_message_102);

        DsmVersion licence200 = DsmVersionBuilder.instance()
                .version("licence_2.0.0")
                .db(new String[]{
                        "licence_2.0.0",
                }).get();
        versionList.add(licence200);

        DsmVersion kanass_privilege_100 = DsmVersionBuilder.instance()
                .version("kanass_privilege_1.0.0")
                .db(new String[]{
                        "kanass_privilege_1.0.0",
                }).get();
        versionList.add(kanass_privilege_100);

        DsmVersion kanass_test_101 = DsmVersionBuilder.instance()
                .version("kanass_test_1.0.1")
                .db(new String[]{
                        "kanass_test_1.0.1",
                }).get();
        versionList.add(kanass_test_101);

        DsmVersion task159 = DsmVersionBuilder.instance()
                .version("task_1.5.9")
                .task(pmc159Task)
                .get();
        versionList.add(task159);

//        评审放到EE版
//        DsmVersion task155 = DsmVersionBuilder.instance()
//                .version("task_1.5.5")
//                .task(pmc155Task)
//                .get();
//        versionList.add(task155);
//        评审放到EE版
//        DsmVersion task163 = DsmVersionBuilder.instance()
//                .version("task_1.6.3")
//                .task(pmc163Task)
//                .get();
//        versionList.add(task163);

//        DsmVersion pmc164Privilege = DsmVersionBuilder.instance()
//                .version("task_1.6.4_privilege")
//                .task(pmc164PrivilegeTask)
//                .get();
//        versionList.add(pmc164Privilege);

        DsmVersion pmc166ProjectPrivilege = DsmVersionBuilder.instance()
                .version("task_1.6.6_project_privilege")
                .task(pmc166ProjectPrivilegeTask)
                .get();
        versionList.add(pmc166ProjectPrivilege);

        DsmVersion TaskKanassMessage100 = DsmVersionBuilder.instance()
                .version("kanass_message_1.0.0_task")
                .task(kanassMessage100Task)
                .get();
        versionList.add(TaskKanassMessage100);

        DsmVersion TaskKanassMessage101 = DsmVersionBuilder.instance()
                .version("kanass_message_1.0.1_task")
                .task(kanassMessage101Task)
                .get();
        versionList.add(TaskKanassMessage101);

        DsmVersion Pmc168WorkCodeTask = DsmVersionBuilder.instance()
                .version("pmc_1.6.8_work_code_task")
                .task(pmc168WorkCodeTask)
                .get();
        versionList.add(Pmc168WorkCodeTask);

        DsmVersion Pmc169ReportDescTask = DsmVersionBuilder.instance()
                .version("pmc_1.6.9_report_desc_task")
                .task(pmc169ReportDescTask)
                .get();
        versionList.add(Pmc169ReportDescTask);

        DsmVersion TaskKanassProjectPrivilege100 = DsmVersionBuilder.instance()
                .version("project_privilege_1.0.0_task")
                .task(kanassProjectPrivilege100Task)
                .get();
        versionList.add(TaskKanassProjectPrivilege100);

        DsmVersion TaskKanassProjectPrivilege101 = DsmVersionBuilder.instance()
                .version("project_privilege_1.0.1_task")
                .task(kanassProjectPrivilege101Task)
                .get();
        versionList.add(TaskKanassProjectPrivilege101);

        DsmVersion TaskKanassTest100 = DsmVersionBuilder.instance()
                .version("kanass_test_1.0.0_task")
                .task(kanassTest100Task)
                .get();
        versionList.add(TaskKanassTest100);

        return versionList;
    }
}