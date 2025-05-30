package io.tiklab.kanass.starter.config;


import io.tiklab.dsm.model.DsmConfig;
import io.tiklab.dsm.support.DsmConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KanassDsmAutoConfiguration {

    /**
     * 初始化dsm
     */
    @Bean
    DsmConfig initDsmConfig() {
        DsmConfig dsmConfig = DsmConfigBuilder.instance();
        //1.0.0
        dsmConfig.newVersion("1.0.0", new String[]{
                "openapi_1.0.0",
                "privilege_1.0.0",
                //UserDsm
                "user_1.0.0",
                "userCe_1.0.0",
                //IntegrationDsm
                "tool_1.0.0",
                //LicenceDsm
                "app-authorization_1.0.0",
                //MessageDsm
                "message_1.0.0",
                //SecurityDsm
                "oplog_1.0.0",
                //TodoTaskDsm
                "todotask_1.0.0",
                //FLOW
                "flow_1.0.0",
                //FORM
                "form_1.0.0",
                //TeamWireDsm
                "pmc_1.0.0",
                "backups_1.0.0"
        });
        dsmConfig.newVersion("1.0.1", new String[]{
                "flow_1.0.1",
                "form_1.0.1",
                "privilege_1.0.1",
                "message_1.0.1",
                //SecurityDsm
                "oplog_1.0.1",
                //TodoTaskDsm
                "todotask_1.0.1",
                "apply-auth_1.0.1",
                "privilege_1.0.1"
        });
        dsmConfig.newVersion("1.0.2", new String[]{
                "message_1.0.2",
                "oplog_1.0.2",
                "flow_1.0.2",
                "todotask_1.0.2",
                "apply-auth_1.0.2",
                "privilege_1.0.2",
                "form_1.0.2"
        });
        dsmConfig.newVersion("1.0.3", new String[]{
                "message_1.0.3",
                "flow_1.0.3",
                "apply-auth_1.0.3",
                "form_1.0.3",
                "privilege_1.0.3",
        });
        dsmConfig.newVersion("1.0.4", new String[]{
                "oplog_1.0.4",
                "message_1.0.4",
                "flow_1.0.4",
                "apply-auth_1.0.4",
                "privilege_1.0.4"
        });
        dsmConfig.newVersion("1.0.5", new String[]{
                "message_1.0.5",
                "flow_1.0.5",
        });
        dsmConfig.newVersion("1.0.6", new String[]{
                "message_1.0.6"
        });
        dsmConfig.newVersion("1.0.7", new String[]{
                "message_1.0.7"
        });
        dsmConfig.newVersion("1.0.8", new String[]{
                "message_1.0.8"
        });
        dsmConfig.newVersion("1.1.0", new String[]{
                "user_1.1.0"
        });
        dsmConfig.newVersion("1.1.1", new String[]{
                "user_1.1.1"
        });
        dsmConfig.newVersion("1.4.2", new String[]{
                "pmc_1.4.2"
        });
        dsmConfig.newVersion("1.4.3", new String[]{
                "pmc_1.4.3"
        });
        dsmConfig.newVersion("1.4.5", new String[]{
                "pmc_1.4.5"
        });
        dsmConfig.newVersion("1.4.6", new String[]{
                "pmc_1.4.6"
        });
        dsmConfig.newVersion("1.4.7", new String[]{
                "pmc_1.4.7"
        });
        dsmConfig.newVersion("1.4.8", new String[]{
                "pmc_1.4.8"
        });
        dsmConfig.newVersion("1.4.9", new String[]{
                "pmc_1.4.9"
        });
        dsmConfig.newVersion("1.5.0", new String[]{
                "pmc_1.5.0"
        });
        dsmConfig.newVersion("1.5.1", new String[]{
                "pmc_1.5.1"
        });
        dsmConfig.newVersion("1.5.2", new String[]{
                "pmc_1.5.2"
        });
        dsmConfig.newVersion("1.5.3", new String[]{
                "pmc_1.5.3"
        });
        dsmConfig.newVersion("1.5.4", new String[]{
                "pmc_1.5.4"
        });
        return dsmConfig;
    }
}