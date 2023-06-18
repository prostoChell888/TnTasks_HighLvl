package ru.bahmutov.service;

import ru.bahmutov.dao.BankDTO;

import java.sql.SQLException;
import java.util.List;

public interface BankService {

    List<BankDTO> updateBankNames(String newName) throws SQLException;
}
