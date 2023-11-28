package io.tiklab.teamwire;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({TeamWireServerAutoConfiguration.class})
public @interface EnableTeamWireServer {
}
