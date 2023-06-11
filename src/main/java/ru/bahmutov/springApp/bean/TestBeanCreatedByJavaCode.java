package ru.bahmutov.springApp.bean;

public class TestBeanCreatedByJavaCode {

    private String name;

    public TestBeanCreatedByJavaCode() {
        this.name = "JavaCodeBean";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
