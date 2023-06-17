package ru.bahmutov;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app")
public record ApplicationConfig(String str,
                                Integer num,
                                List<String> list,
                                MyClass myClass) { }

