package com.example.demo.configuration;

import com.example.demo.service.CustomService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
            value="module.enabled",
            havingValue = "true",
            matchIfMissing = true)
    @Qualifier(value = "service")
    public CustomService service2() throws Exception {
        System.out.println("Service 2 is called");
        if(true)
            throw new Exception("Exception generated");
        return new CustomService();
    }

    @Bean
    @ConditionalOnMissingBean
    @Qualifier(value = "service")
    public CustomService service1() {
        System.out.println("Service 1 is called");
        return new CustomService();
    }
}
