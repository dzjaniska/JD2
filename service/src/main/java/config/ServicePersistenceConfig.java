package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"service", "converter"})
@Import(PersistenceConfig.class)
public class ServicePersistenceConfig {
}
