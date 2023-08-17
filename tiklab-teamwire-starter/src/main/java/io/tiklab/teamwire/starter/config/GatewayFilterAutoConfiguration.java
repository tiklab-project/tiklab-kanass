package io.tiklab.teamwire.starter.config;

import io.tiklab.eam.author.Authenticator;
import io.tiklab.eam.client.author.config.AuthorConfig;
import io.tiklab.eam.client.author.config.AuthorConfigBuilder;
import io.tiklab.eam.client.author.filter.AuthorFilter;
import io.tiklab.gateway.router.Router;
import io.tiklab.gateway.router.RouterBuilder;
import io.tiklab.gateway.router.config.RouterConfig;
import io.tiklab.gateway.router.config.RouterConfigBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayFilterAutoConfiguration{

    @Value("${eas.address:null}")
    String EasAuthAddress;

    @Value("${eas.embbed.enable:false}")
    Boolean enableEam;
    @Bean
    Router router(RouterConfig routerConfig){
        return RouterBuilder.newRouter(routerConfig);
    }

    //路由配置
    @Bean
    RouterConfig routerConfig(){
        String[] s = {};

        return RouterConfigBuilder.instance()
                .preRoute(s, EasAuthAddress)
                .get();
    }

    //认证filter
    @Bean
    AuthorFilter authorFilter(Authenticator authenticator, AuthorConfig ignoreConfig){
        return new AuthorFilter()
                .setAuthenticator(authenticator)
                .setAuthorConfig(ignoreConfig);
    }

    @Bean
    public AuthorConfig authorConfig(){
        return AuthorConfigBuilder.instance()
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
                        ".svg"
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
                        "/workItem/findWorkItem"



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


