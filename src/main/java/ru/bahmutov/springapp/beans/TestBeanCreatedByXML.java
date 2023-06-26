package ru.bahmutov.springapp.beans;


import lombok.Getter;

@Getter
public class TestBeanCreatedByXML {

  private final String name;

    public TestBeanCreatedByXML() {
        this.name = "XMLBean";
    }

}
