package io.tiklab.kanass.starter;

import io.tiklab.dal.boot.starter.annotation.EnableDal;
import io.tiklab.dcs.boot.starter.annotation.EnableDcsClient;
import io.tiklab.dcs.boot.starter.annotation.EnableDcsServer;
import io.tiklab.dfs.boot.starter.annotation.EnableDfsClient;
import io.tiklab.dfs.boot.starter.annotation.EnableDfsInit;
import io.tiklab.dfs.boot.starter.annotation.EnableDfsServer;
import io.tiklab.dfs.client.DefaultDfsClient;
import io.tiklab.dfs.client.DfsClient;
import io.tiklab.dsm.boot.starter.annotation.EnableDsm;
import io.tiklab.eam.boot.starter.annotation.EnableEamClient;
import io.tiklab.eam.boot.starter.annotation.EnableEamServer;
import io.tiklab.flow.starter.EnableFlowServer;
import io.tiklab.form.starter.EnableFormServer;
import io.tiklab.gateway.boot.starter.annotation.EnableGateway;
import io.tiklab.install.spring.boot.starter.EnableInstallServer;
import io.tiklab.kanass.EnableKanassServer;
import io.tiklab.licence.boot.starter.annotation.EnableLicenceServer;
import io.tiklab.messsage.boot.starter.annotation.EnableMessageServer;
import io.tiklab.openapi.boot.starter.annotation.EnableOpenApi;
import io.tiklab.postgresql.spring.boot.starter.EnablePostgresql;
import io.tiklab.postin.client.EnablePostInClient;
import io.tiklab.postin.client.openapi.ParamConfig;
import io.tiklab.postin.client.openapi.ParamConfigBuilder;
import io.tiklab.postin.client.openapi.PostInClientConfig;
import io.tiklab.privilege.boot.starter.annotation.EnablePrivilegeServer;
import io.tiklab.rpc.boot.starter.annotation.EnableRpc;
import io.tiklab.security.backups.config.BackupsConfig;
import io.tiklab.security.boot.stater.annotation.EnableSecurityServer;
import io.tiklab.toolkit.boot.starter.annotation.EnableToolkit;
import io.tiklab.user.boot.starter.annotation.EnableUserClient;
import io.tiklab.user.boot.starter.annotation.EnableUserServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

import static io.tiklab.security.backups.util.BackupsFinal.DFS;


@Configuration
//platform
@EnableToolkit
@EnableDal
@EnableDsm
@EnableDfsServer
@EnableDfsClient
@EnableDfsInit
@EnableDcsServer
@EnableDcsClient
@EnableRpc
@EnableGateway
@EnablePostgresql
@EnableInstallServer
//pcs
@EnableUserClient
@EnableUserServer
@EnableEamServer
@EnableEamClient
@EnablePrivilegeServer
@EnableFormServer
@EnableFlowServer
@EnableLicenceServer
@EnableMessageServer
@EnableSecurityServer

//other
@EnablePostInClient
@EnableKanassServer
@EnableOpenApi
@ComponentScan({"io.tiklab.kanass"})
public class KanassAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(KanassAutoConfiguration.class);

    @Value("${DATA_HOME}")
    String DATA_HOME;

    @Bean
    BackupsConfig backupsConfig() {
        return BackupsConfig.instance()
                .addPath(DFS,DATA_HOME + "/file")
                .get();
    }

    @Bean
    DfsClient dfsClient() {
        return new DefaultDfsClient();
    }

    @Bean
    PostInClientConfig postInClientConfig(ParamConfig paramConfig){
        PostInClientConfig config = new PostInClientConfig();
        config.setParamConfig(paramConfig);

        return config;
    }

    @Bean
    ParamConfig paramConfig(){
        //设置请求头，属性名称：属性描述
        HashMap<String,String> headers = new HashMap<>();
        headers.put("accessToken","设置的apiKey");

        return ParamConfigBuilder.instance()
                .setScanPackage("io.tiklab.kanass")
                .prePath("/api") //设置额外的前缀
                .setHeaders(headers)
                .get();
    }
}
