package io.tiklab.teamwire.starter;

import io.tiklab.core.property.PropertyAndYamlSourceFactory;
import io.tiklab.teamwire.starter.annotation.EnableTeamWire;
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
@EnableTeamWire
@EnableScheduling
@PropertySource(value = {"classpath:application.yaml"},factory = PropertyAndYamlSourceFactory.class)
public class TeamWireApplication {

    public static final Logger logger = LoggerFactory.getLogger(TeamWireApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TeamWireApplication.class, args);
    }

}
