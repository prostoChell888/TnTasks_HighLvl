package ru.bahmutov.repository;

import ru.bahmutov.models.Bank;

import java.util.List;

public interface BankRepository {

    List<Bank> updateBankNames(String newName);
}
