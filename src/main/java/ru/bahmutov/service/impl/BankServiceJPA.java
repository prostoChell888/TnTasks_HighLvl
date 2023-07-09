package ru.bahmutov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.bahmutov.dto.BankDto;
import ru.bahmutov.models.Bank;
import ru.bahmutov.repository.BankRepository;
import ru.bahmutov.service.BankService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class BankServiceJPA implements BankService {

    private final BankRepository repository;

    @Override
    @Transactional
    public List<BankDto> updateBankNames(String newName) {
        repository.updateBankNames(newName);
        List<Bank> banks = repository.findAll();

        List<BankDto> banksDto = new ArrayList<>();
        for (var bank: banks) {
            banksDto.add(new BankDto(bank.getId(), bank.getName()));
        }

        return banksDto;
    }
}
