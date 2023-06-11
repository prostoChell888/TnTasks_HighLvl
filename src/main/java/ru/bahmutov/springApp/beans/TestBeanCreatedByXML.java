package ru.bahmutov.springApp.beans;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class TestBeanCreatedByXML {

  private final String name;

    public TestBeanCreatedByXML() {
        this.name = "XMLBean";
    }

}
