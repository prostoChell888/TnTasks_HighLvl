package ru.bahmutov.springapp.beans;


import lombok.Getter;

@Getter
public class TestBeanCreatedByJavaCode {

    private final String name;

    public TestBeanCreatedByJavaCode() {
        this.name = "JavaCodeBean";
    }

}
