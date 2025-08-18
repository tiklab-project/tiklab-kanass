package io.tiklab.kanass.starter.config;


import io.tiklab.dsm.model.DsmConfig;
import io.tiklab.dsm.model.DsmVersion;
import io.tiklab.dsm.support.DsmVersionBuilder;
import io.tiklab.kanass.sql.Pmc155Task;
import io.tiklab.kanass.sql.Pmc159Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class KanassDsmAutoConfiguration {

    @Autowired
    Pmc155Task pmc155Task;

    @Autowired
    Pmc159Task pmc159Task;

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
                .task(pmc155Task)
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
                .task(pmc159Task)
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

        return versionList;
    }
}