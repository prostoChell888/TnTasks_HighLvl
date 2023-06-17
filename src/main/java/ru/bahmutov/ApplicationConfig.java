package ru.bahmutov;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(String str,
                                Integer num) { }

