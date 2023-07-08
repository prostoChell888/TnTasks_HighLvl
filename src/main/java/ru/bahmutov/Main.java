package ru.bahmutov;

import ru.bahmutov.configuration.ConfigurationDB;
import ru.bahmutov.repository.impl.BankJDBCRepository;
import ru.bahmutov.repository.impl.PersonJDBCRepository;
import ru.bahmutov.service.BankService;
import ru.bahmutov.service.PersonService;
import ru.bahmutov.service.impl.BankJDBCService;
import ru.bahmutov.service.impl.PersonJDBCService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        var config = new ConfigurationDB("application.properties");

        BankJDBCRepository repository = new BankJDBCRepository(config);
        BankService bankService = new BankJDBCService(repository);
        var updatedBanks = bankService.updateBankNames("newBankName");
        updatedBanks.forEach(System.out::println);

        PersonJDBCRepository personRopo = new PersonJDBCRepository(config);
        PersonService personService = new PersonJDBCService(personRopo);
        var persons = personService.getAllPerson();
        persons.forEach(System.out::println);
    }
}