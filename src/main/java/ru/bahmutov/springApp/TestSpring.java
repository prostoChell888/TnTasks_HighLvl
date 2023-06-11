package ru.bahmutov.springApp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        TestBeanCreatedByXML testBeanCreatedByXML = context.getBean("testBeanCreatedByXML", TestBeanCreatedByXML.class);
        System.out.println(testBeanCreatedByXML.getName());
        context.close();
    }
}
