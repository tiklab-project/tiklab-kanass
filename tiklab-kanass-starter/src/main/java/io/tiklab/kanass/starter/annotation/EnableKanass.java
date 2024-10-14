package io.tiklab.kanass.starter.annotation;

import io.tiklab.kanass.starter.KanassAutoConfiguration;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({KanassAutoConfiguration.class})
public @interface EnableKanass {
}
