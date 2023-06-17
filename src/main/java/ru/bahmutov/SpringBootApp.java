package ru.bahmutov;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationConfig.class})
@Slf4j
public class SpringBootApp {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(SpringBootApp.class, args);


        ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
        log.info("App num: " + config.num());
        log.info("App str: " + config.str());
    }
}