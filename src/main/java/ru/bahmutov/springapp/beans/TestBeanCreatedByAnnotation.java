package ru.bahmutov.springapp.beans;


import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TestBeanCreatedByAnnotation {
    private final String name;

    public TestBeanCreatedByAnnotation() {
        this.name = "AnnotationBean";
    }



}
