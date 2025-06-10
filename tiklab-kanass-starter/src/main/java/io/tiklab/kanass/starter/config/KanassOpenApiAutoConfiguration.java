package io.tiklab.kanass.starter.config;


import io.tiklab.openapi.config.AllowConfig;
import io.tiklab.openapi.config.AllowConfigBuilder;
import io.tiklab.openapi.config.OpenApiConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KanassOpenApiAutoConfiguration {

    @Value("${soular.address:null}")
    String authAddress;

    @Value("${soular.embbed.enable:false}")
    Boolean enableEam;

    @Value("${server.port}")
    Integer port;


    //路由
    @Bean("routerForOpenApi")
    OpenApiConfig router(@Qualifier("routerConfigForOpenApi") AllowConfig allowConfig){
        OpenApiConfig openApiConfig = new OpenApiConfig();
        openApiConfig.setAllowConfig(allowConfig);

        return openApiConfig;
    }

    //路由配置
    @Bean("routerConfigForOpenApi")
    AllowConfig routerConfig(){
        String[] s =  new String[]{
                "/project/findProject",
                "/project/findAllProject",
                "/workItem/findConditionWorkItemPage",
                "/workItem/findWorkItem"
        };

        return AllowConfigBuilder.instance()
                .allowUrls(s)
                .get();
    }
}
