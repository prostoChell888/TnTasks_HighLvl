package ru.bahmutov.service.hibernate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bahmutov.dto.BankDto;
import ru.bahmutov.models.Bank;
import ru.bahmutov.repository.impl.BankHibernateRepository;
import ru.bahmutov.service.BankService;
import ru.bahmutov.service.impl.BankHibernateService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;


@ExtendWith(MockitoExtension.class)
class BankHibernateServiceTest {
    @Mock
    private BankHibernateRepository repository;

    private BankService service;


    @BeforeEach
    public  void setUp() {
        service = new BankHibernateService(repository);
    }

    @Test
    @DisplayName("should change names of banks")
    void shouldDeleteNote()  {
        List<BankDto> expectedListOfBanks = List.of(
                new BankDto(1L ,"newBankName"),
                new BankDto(2L ,"newBankName"));

        Mockito.when(repository.updateBankNames("newBankName")).thenReturn(List.of(
                new Bank(1L ,"newBankName"),
                new Bank(2L ,"newBankName")));

        var updatedBanks = service.updateBankNames("newBankName");

        assertIterableEquals(expectedListOfBanks, updatedBanks);
    }
}