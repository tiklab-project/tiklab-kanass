package io.tiklab.kanass.starter;

import io.tiklab.core.property.PropertyAndYamlSourceFactory;
import io.tiklab.kanass.starter.annotation.EnableKanass;
import io.tiklab.postin.client.EnablePostInClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * ProjectApplication
 */
@SpringBootApplication
@EnableKanass
@EnableScheduling
@EnablePostInClient
@PropertySource(value = {"classpath:application.yaml"},factory = PropertyAndYamlSourceFactory.class)
public class KanassApplication {

    public static final Logger logger = LoggerFactory.getLogger(KanassApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KanassApplication.class, args);
    }

}
