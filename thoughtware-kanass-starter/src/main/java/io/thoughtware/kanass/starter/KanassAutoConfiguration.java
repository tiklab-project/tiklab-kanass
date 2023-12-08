package io.thoughtware.kanass.starter;

import io.thoughtware.dal.boot.starter.annotation.EnableDal;
import io.thoughtware.dcs.boot.starter.annotation.EnableDcsClient;
import io.thoughtware.dcs.boot.starter.annotation.EnableDcsServer;
import io.thoughtware.dfs.boot.starter.annotation.EnableDfsClient;
import io.thoughtware.dfs.boot.starter.annotation.EnableDfsServer;
import io.thoughtware.eam.boot.starter.annotation.EnableEamClient;
import io.thoughtware.eam.boot.starter.annotation.EnableEamServer;
import io.thoughtware.flow.starter.EnableFlowServer;
import io.thoughtware.form.starter.EnableFormServer;
import io.thoughtware.gateway.boot.starter.annotation.EnableGateway;
import io.thoughtware.kanass.EnableKanassServer;
import io.thoughtware.licence.boot.starter.annotation.EnableLicenceServer;
import io.thoughtware.messsage.boot.starter.annotation.EnableMessageClient;
import io.thoughtware.messsage.boot.starter.annotation.EnableMessageServer;
import io.thoughtware.openapi.boot.starter.annotation.EnableOpenApi;
import io.thoughtware.plugin.starter.EnablePluginServer;
import io.thoughtware.postgresql.EnablePostgresql;
import io.thoughtware.postin.client.EnablePostInClient;
import io.thoughtware.privilege.boot.starter.annotation.EnablePrivilegeServer;
import io.thoughtware.rpc.boot.starter.annotation.EnableRpc;
import io.thoughtware.security.boot.stater.annotation.EnableSecurityClient;
import io.thoughtware.security.boot.stater.annotation.EnableSecurityServer;
import io.thoughtware.todotask.boot.stater.annotation.EnableTodoTaskClient;
import io.thoughtware.todotask.boot.stater.annotation.EnableTodoTaskServer;
import io.thoughtware.toolkit.boot.starter.annotation.EnableToolkit;
import io.thoughtware.user.boot.starter.annotation.EnableUserClient;
import io.thoughtware.user.boot.starter.annotation.EnableUserServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
//platform
@EnableToolkit
@EnableDal
@EnableDfsServer
@EnableDfsClient
@EnableDcsServer
@EnableDcsClient
@EnableRpc
@EnableGateway
@EnablePostgresql
//pcs
@EnableUserClient
@EnableUserServer
@EnableEamServer
@EnableEamClient
@EnablePrivilegeServer
@EnableFormServer
@EnableFlowServer
@EnablePluginServer
@EnableLicenceServer
@EnableMessageServer
@EnableMessageClient
@EnableSecurityServer
@EnableSecurityClient
@EnableTodoTaskServer
@EnableTodoTaskClient

//other
@EnableKanassServer
@EnablePostInClient
@EnableOpenApi
@ComponentScan({"io.thoughtware.kanass"})
public class KanassAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(KanassAutoConfiguration.class);
}
