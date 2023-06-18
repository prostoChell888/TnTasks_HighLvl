package ru.bahmutov.repository;

import ru.bahmutov.dao.BankDTO;

import java.util.List;

public interface BankRepository {

    List<BankDTO> updateBankNames(String newName);
}
