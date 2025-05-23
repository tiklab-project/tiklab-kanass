package io.tiklab.kanass.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JdbcTypeCheckUtil {

    @Value("${jdbc.driverClassName}")
    private String jdbcType;

    public String checkType() {
        if (jdbcType.contains("mysql")){
            return "mysql";
        } else if (jdbcType.contains("postgresql")) {
            return "postgresql";
        }
        return "postgresql";
    }
}
