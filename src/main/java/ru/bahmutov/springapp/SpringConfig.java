package ru.bahmutov.springapp;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.bahmutov.springapp.beans.TestBeanCreatedByJavaCode;

@Configuration
@ComponentScan("ru.bahmutov.springapp")
public class SpringConfig {

    @Bean
    public TestBeanCreatedByJavaCode javaCodeBean() {
        return new TestBeanCreatedByJavaCode();
    }
}
