package io.tiklab.teamwire.starter.annotation;

import io.tiklab.teamwire.starter.TeamWireAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({TeamWireAutoConfiguration.class})
public @interface EnableTeamWire {
}
