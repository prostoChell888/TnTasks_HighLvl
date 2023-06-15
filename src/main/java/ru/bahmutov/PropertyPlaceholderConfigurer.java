package ru.bahmutov;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Getter
@PropertySource("classpath:app.properties")
public class PropertyPlaceholderConfigurer {
    @Value("${app.str}")
    private String name;
    @Value("${app.num}")
    private String version;

}