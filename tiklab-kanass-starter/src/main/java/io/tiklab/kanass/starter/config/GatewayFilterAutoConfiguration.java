package io.tiklab.kanass.starter.config;

import io.tiklab.eam.author.Authenticator;

import io.tiklab.eam.client.author.handler.DefaultAuthorHandler;
import io.tiklab.gateway.config.*;
import io.tiklab.gateway.handler.author.AuthorHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayFilterAutoConfiguration{

    @Bean
    GatewayConfig gatewayConfig(IgnoreConfig ignoreConfig){
        GatewayConfig gatewayConfig = new GatewayConfig();
        gatewayConfig.setIgnoreConfig(ignoreConfig);

        return gatewayConfig;
    }

    @Bean
    public IgnoreConfig authorConfig(){
        return IgnoreConfigBuilder.instance()
                .ignoreTypes(new String[]{
                        ".ico",
                        ".jpg",
                        ".jpeg",
                        ".png",
                        ".gif",
                        ".html",
                        ".js",
                        ".css",
                        ".json",
                        ".xml",
                        ".ftl",
                        ".map",
                        ".svg",
                        ".txt"
                })

                .ignoreUrls(new String[]{
                        "/",
                        "/mobile",
                        "/mobile.html/",
                        "/passport/login",
                        "/passport/logout",
                        "/passport/valid",
                        "/auth/valid",
                        "/callback/instruct",
                        "/callback/data",
                        "/ftptest.cgi",
                        "/guest/reception",
                        "/oauth/oauth",
                        "/callback/getUserinfo3rd",
                        "/callback/registerx",
                        "/user/dingdingcfg/findId",
                        "/dingding/passport/login",
                        "/dingding/passport/logout",
                        "/user/wechatcfg/findWechatById",
                        "/ldap/passport/login",
                        "/ldap/passport/logout",
                        "/version/getVersion",
                        "/wechat/passport/login",
                        "/wechat/passport/logout",
                        "/permission/findPermissions",
                        "/wechat/passport/internallogin",
                        "/dfs/upload",
                        "/eam/auth/login",
                        "/updateMySql/updateAllData",

                        "/project/findAllProject",
                        "/dmUser/findDmUserPage",
                        "/workTypeDm/findWorkTypeDmList",
                        "/workItem/findWorkItemList",
                        "/workItem/findWorkItemPage",
                        "/workItem/findWorkItem",
                        "/message/messageItem/syncUpdateMessage",
                        "/message/messageItem/syncDeleteMessage",
                        "/init/install/findStatus",
                        "/openapi/doc"

                })
                .ignorePreUrls(new String[]{
                        "/service",
                        "/apis/list",
                        "/apis/detail",
                        "/file",
                        "/plugin",
                        "/authConfig",
                        "/app",
                        "/licence",
                        "/workLog",
                        "/image"
                })
                .get();
    }
}


