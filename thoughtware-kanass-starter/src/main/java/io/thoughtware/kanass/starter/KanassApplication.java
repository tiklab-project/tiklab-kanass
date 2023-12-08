package io.thoughtware.kanass.starter;

import io.thoughtware.core.property.PropertyAndYamlSourceFactory;
import io.thoughtware.kanass.starter.annotation.EnableKanass;
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
@PropertySource(value = {"classpath:application.yaml"},factory = PropertyAndYamlSourceFactory.class)
public class KanassApplication {

    public static final Logger logger = LoggerFactory.getLogger(KanassApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KanassApplication.class, args);
    }

}
