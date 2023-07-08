package ru.bahmutov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.bahmutov.models.Bank;
import ru.bahmutov.repository.BankRepository;
import ru.bahmutov.service.BankService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class BankServiceJPA implements BankService {

    private final BankRepository repository;

    @Override
    @Transactional
    public List<Bank> updateBankNames(String newName) {
        repository.updateBankNames(newName);
        return repository.findAll();
    }
}
