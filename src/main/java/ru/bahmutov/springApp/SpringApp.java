package ru.bahmutov.springApp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bahmutov.springApp.beans.TestBeanCreatedByAnnotation;
import ru.bahmutov.springApp.beans.TestBeanCreatedByJavaCode;
import ru.bahmutov.springApp.beans.TestBeanCreatedByXML;

public class SpringApp {
    public static void main(String[] args) {
        var contextXML = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        var testBeanCreatedByXML = contextXML.getBean("testBeanCreatedByXML", TestBeanCreatedByXML.class);
        System.out.println(testBeanCreatedByXML.getName());
        var contextAnnotation = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );

        var testBeanCreatedByAnnotation = contextAnnotation.getBean("testBeanCreatedByAnnotation", TestBeanCreatedByAnnotation.class);
        System.out.println(testBeanCreatedByAnnotation.getName());

        var testBeanCreatedByJavaCode = contextAnnotation.getBean("javaCodeBean", TestBeanCreatedByJavaCode.class);
        System.out.println(testBeanCreatedByJavaCode.getName());


        contextXML.close();
    }
}
