package ru.bahmutov.springApp.beans;



public class TestBeanCreatedByXML {

  private String name;

    public TestBeanCreatedByXML() {
        this.name = "XMLBean";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
