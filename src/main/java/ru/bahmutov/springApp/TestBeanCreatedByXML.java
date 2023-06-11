package ru.bahmutov.springApp;



public class TestBeanCreatedByXML {

  private String name;

    public TestBeanCreatedByXML(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
