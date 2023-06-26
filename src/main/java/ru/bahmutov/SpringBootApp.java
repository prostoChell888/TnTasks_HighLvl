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


        var config = ctx.getBean(ApplicationConfig.class);
        log.info("Configurations @ConfigurationProperties:\n" +
                "App num: " + config.num() + "\n" +
                "App list: " + config.list() + "\n" +
                "App myClass: " + config.myClass() + "\n" +
                "App str: " + config.str());


        var configHolder = ctx.getBean(ConfigHolder.class);

        log.info("Configurations @Value:\n" +
                "App num: " + configHolder.getNum() + "\n" +
                "App str: " + configHolder.getStr());
    }
}