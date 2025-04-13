package com.example.consultantinternship.config;

import com.example.consultantinternship.service.StatService;
import com.example.consultantinternship.service.StatServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@Slf4j
public class StatServiceConfig {
    @Value("${sourceDir}")
    private String sourceDir;
    @Bean
    public StatService statService() {
        File dir = new File(sourceDir);
        if (!dir.exists() && dir.mkdirs()) {
            log.info("Created directory for logs: {}", dir.getAbsolutePath());
        }
        return new StatServiceImpl(sourceDir);
    }
}
