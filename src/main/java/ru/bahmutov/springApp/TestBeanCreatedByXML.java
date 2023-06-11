package ru.bahmutov.springApp;



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
