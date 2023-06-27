package ru.bahmutov.repository;

import ru.bahmutov.dao.BankDTO;

import java.sql.SQLException;
import java.util.List;

public interface BankRepository {

    List<BankDTO> updateBankNames(String newName) throws SQLException;

    List<BankDTO> getAllBunks() throws SQLException;

    void save(BankDTO bank) throws SQLException;
}
