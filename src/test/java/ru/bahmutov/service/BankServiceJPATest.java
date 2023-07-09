package ru.bahmutov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bahmutov.dto.BankDto;
import ru.bahmutov.models.Bank;
import ru.bahmutov.repository.BankRepository;
import ru.bahmutov.service.impl.BankServiceJPA;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@ExtendWith(MockitoExtension.class)
class BankServiceJPATest {
    @Mock
    private BankRepository bankRepository;

    private BankService bankService;


    @BeforeEach
    public void setUp() {
        bankService = new BankServiceJPA(bankRepository);
    }

    @Test
    @DisplayName("should find note by id")
    void shouldUpdateAllBanks() {
        Mockito.when(bankRepository.findAll()).thenReturn(List.of(
                new Bank(1L, "new bank name"),
                new Bank(2L, "new bank name"),
                new Bank(3L, "new bank name")));

        List<BankDto> expectedListOfBanks = List.of(
                new BankDto(1L, "new bank name"),
                new BankDto(2L, "new bank name"),
                new BankDto(3L, "new bank name"));

        List<BankDto> banks = bankService.updateBankNames("new bank name");

        assertIterableEquals(expectedListOfBanks, banks);
    }
}