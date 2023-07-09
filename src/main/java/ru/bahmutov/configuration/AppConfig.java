package ru.bahmutov.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bahmutov.repository.impl.BankHibernateRepository;
import ru.bahmutov.repository.impl.PersonHibernateRepository;
import ru.bahmutov.service.BankService;
import ru.bahmutov.service.PersonService;
import ru.bahmutov.service.impl.BankHibernateService;
import ru.bahmutov.service.impl.PersonHibernateService;
import ru.bahmutov.util.HibernateUtil;

@Configuration
public class AppConfig {
    @Bean
    public BankHibernateRepository bankRepository() {
        return new BankHibernateRepository(HibernateUtil.getSessionFactory());
    }

    @Bean
    public PersonHibernateRepository personRepository() {
        return new PersonHibernateRepository(HibernateUtil.getSessionFactory());
    }

    @Bean
    public BankService bankService() {
        return new BankHibernateService(bankRepository());
    }

    @Bean
    public PersonService personService() {
        return new PersonHibernateService(personRepository());
    }
}
