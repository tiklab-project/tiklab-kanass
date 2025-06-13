package io.tiklab.kanass.starter.config;

import io.tiklab.security.backups.config.BackupsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.tiklab.security.backups.util.BackupsFinal.DFS;

@Configuration
public class KanassBackupsConfiguration {

    @Value("${DATA_HOME}")
    String DATA_HOME;

    @Bean
    BackupsConfig backupsConfig() {
        return BackupsConfig.instance()
                .addPath(DFS,DATA_HOME + "/file")
                .get();
    }
}
