package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"service", "repository"})
@Import(ServicePersistenceConfig.class)
public class TestConfiguration {
}
