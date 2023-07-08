package ru.bahmutov.service.jdbc;

import lombok.RequiredArgsConstructor;
import ru.bahmutov.dao.BankDTO;
import ru.bahmutov.repository.impl.BankJDBCRepository;
import ru.bahmutov.service.BankService;

import java.sql.SQLException;
import java.util.List;


@RequiredArgsConstructor
public class BankJDBCService implements BankService {
    private final BankJDBCRepository repository;


    @Override
    public List<BankDTO> updateBankNames(String newName) throws SQLException {
        return repository.updateBankNames(newName);
    }
}
