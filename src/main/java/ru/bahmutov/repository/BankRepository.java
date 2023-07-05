package ru.bahmutov.repository;

import ru.bahmutov.models.Bank;

import java.util.List;

public interface BankRepository {


    List<Bank> updateBankNames(String newName) ;

    List<Bank> getAllBunks() ;

    Bank getById(long id) ;

    Bank save(Bank bank) ;

    void deleteAll() ;
}
