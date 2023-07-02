package ru.bahmutov.service.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bahmutov.dao.BankDTO;
import ru.bahmutov.repository.jdbc.BankJDBCRepository;
import ru.bahmutov.service.BankService;

import java.sql.SQLException;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class BankJDBCServiceTest {
    @Mock
    private BankJDBCRepository repository;

    private BankService service;


    @BeforeEach
    public  void setUp() {
        service = new BankJDBCService(repository);
    }

    @Test
    @DisplayName("should change names of banks")
    void shouldDeleteNote() throws SQLException {
        List<BankDTO> expectedListOfBanks = List.of(
                new BankDTO(1L ,"newBankName"),
                new BankDTO(2L ,"newBankName"));

        Mockito.when(repository.updateBankNames("newBankName")).thenReturn(expectedListOfBanks);

        var updatedBanks = service.updateBankNames("newBankName");

        Assertions.assertIterableEquals(expectedListOfBanks, updatedBanks);
    }

}