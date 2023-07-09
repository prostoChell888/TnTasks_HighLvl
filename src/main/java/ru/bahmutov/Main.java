package ru.bahmutov;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.bahmutov.service.BankService;
import ru.bahmutov.service.PersonService;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        var conf = SpringApplication.run(Main.class, args);
        var bankService = conf.getBean(BankService.class);
        var personService = conf.getBean(PersonService.class);

        var banks = bankService.updateBankNames("new bank name");
        banks.forEach(System.out::println);

        var allPeople = personService.getAllPeople();
        allPeople.forEach(System.out::println);
    }
}