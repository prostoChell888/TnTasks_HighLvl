package ru.bahmutov.springapp.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bahmutov.springapp.SpringConfig;



class TestBeanCreatedByAnnotationTest {

    @Test
    @DisplayName("Создание бина с помощью XML")
    void XMLBeanCreation() {
        var context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        var testBeanCreatedByXML =
                context.getBean("testBeanCreatedByXML", TestBeanCreatedByXML.class);
        context.close();

        Assertions.assertEquals("XMLBean", testBeanCreatedByXML.getName());
    }

    @Test
    @DisplayName("Создание бина с помощью Анотации")
    void annotationBeanCreation() {
        var context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );

        var testBeanCreatedByAnnotation
                = context.getBean("testBeanCreatedByAnnotation", TestBeanCreatedByAnnotation.class);
        context.close();

        Assertions.assertEquals("AnnotationBean", testBeanCreatedByAnnotation.getName());
    }

    @Test
    @DisplayName("Создание бина с помощью Java-config")
    void javaCodeBeanCreation() {
        var context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );

        var testBeanCreatedByJavaCode =
                context.getBean("javaCodeBean", TestBeanCreatedByJavaCode.class);
        context.close();

        Assertions.assertEquals("JavaCodeBean", testBeanCreatedByJavaCode.getName());
    }

}