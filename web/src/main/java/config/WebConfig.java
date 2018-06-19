package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller", "pojo"})
@Import(value = {InternationalizationConfig.class, ThymeleafConfig.class, AspectConfig.class})
public class WebConfig {
}
