package ru.bahmutov.springApp.beans;


import org.springframework.stereotype.Component;

@Component
public class TestBeanCreatedByAnnotation {
    private String name;

    public TestBeanCreatedByAnnotation() {
        this.name = "AnnotationBean";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
