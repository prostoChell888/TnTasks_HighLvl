package ru.bahmutov.springApp;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.bahmutov.springApp.bean.TestBeanCreatedByJavaCode;

@Configuration
@ComponentScan("ru.bahmutov.springApp")
public class SpringConfig {

    @Bean
    public TestBeanCreatedByJavaCode javaCodeBean(){
        return new TestBeanCreatedByJavaCode();
    }
}
