package ru.bahmutov.service;

import ru.bahmutov.dto.BankDto;

import java.util.List;

public interface BankService {

    List<BankDto> updateBankNames(String newName);
}
