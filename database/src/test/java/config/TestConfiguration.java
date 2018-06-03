package com.matveyenka.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.matveyenka.spring.util")
@Import(PersistenceConfig.class)
public class TestConfiguration {

}
