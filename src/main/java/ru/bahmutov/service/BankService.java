package ru.bahmutov.service;

import ru.bahmutov.models.Bank;

import java.util.List;

public interface BankService {

    List<Bank> updateBankNames(String newName);
}
