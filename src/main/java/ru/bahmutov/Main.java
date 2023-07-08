package ru.bahmutov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.bahmutov.service.BankService;
import ru.bahmutov.service.PersonService;

@SpringBootApplication
public class Main  {

    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        var bankService = context.getBean(BankService.class);
        var personService = context.getBean(PersonService.class);

        var banks = bankService.updateBankNames("new bank Names");
        banks.forEach(System.out::println);

        var users = personService.getAllUsers();
        users.forEach(System.out::println);

    }
}