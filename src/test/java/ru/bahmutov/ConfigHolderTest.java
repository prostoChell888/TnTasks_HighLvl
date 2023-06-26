package ru.bahmutov;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ConfigHolderTest {

    @Autowired
    private ConfigHolder configHolder;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Test
    void testCreateBeanWithAnnotationValue() {
        assertEquals( 2222, configHolder.getNum() );
        assertEquals("hello2", configHolder.getStr());
    }

    @Test
    void testCreateBeanWithAnnotationConfigurationProperties() {
        assertEquals( 2222, applicationConfig.num() );
        assertEquals(List.of("one2", "two2", "three2", "four2"), applicationConfig.list());
        assertEquals("hello2", applicationConfig.str());
        assertEquals(new MyClass("firstParam2", Duration.ofSeconds(102)), applicationConfig.myClass());
    }

  
}