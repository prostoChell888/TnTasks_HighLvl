package ru.bahmutov.springapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import ru.bahmutov.springapp.beans.TestBeanCreatedByAnnotation;
import ru.bahmutov.springapp.beans.TestBeanCreatedByJavaCode;
import ru.bahmutov.springapp.beans.TestBeanCreatedByXML;


@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class BeansApp implements CommandLineRunner {
        @Autowired
        private TestBeanCreatedByXML exampleXMLBean;
        @Autowired
        private TestBeanCreatedByJavaCode javaCodeBean;
        @Autowired
        private TestBeanCreatedByAnnotation annotationBean;


        public static void main(String[] args) {
            ConfigurableApplicationContext ctxt = SpringApplication.run(BeansApp.class, args);

        }
        @Override
        public void run(String... args) throws Exception {
            System.out.println(exampleXMLBean.getName());
            System.out.println(javaCodeBean.getName());
            System.out.println(annotationBean.getName());

        }
}
