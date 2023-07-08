package ru.bahmutov.springapp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bahmutov.springapp.beans.TestBeanCreatedByAnnotation;
import ru.bahmutov.springapp.beans.TestBeanCreatedByJavaCode;
import ru.bahmutov.springapp.beans.TestBeanCreatedByXML;

public class BeansApp {

    public static void main(String[] args) {
        var contextXML = new ClassPathXmlApplicationContext("applicationContext.xml");
        var testBeanCreatedByXML = contextXML.getBean("testBeanCreatedByXML", TestBeanCreatedByXML.class);
        contextXML.close();


        var contextAnnotation = new AnnotationConfigApplicationContext(SpringConfig.class);
        var testBeanCreatedByAnnotation = contextAnnotation.getBean("testBeanCreatedByAnnotation", TestBeanCreatedByAnnotation.class);

        var testBeanCreatedByJavaCode = contextAnnotation.getBean("javaCodeBean", TestBeanCreatedByJavaCode.class);
        contextAnnotation.close();

        System.out.println( testBeanCreatedByXML.getName());
        System.out.println( testBeanCreatedByAnnotation.getName());
        System.out.println( testBeanCreatedByJavaCode.getName());

    }
}
