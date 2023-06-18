package ru.bahmutov.service.hibernate;

import lombok.RequiredArgsConstructor;
import ru.bahmutov.models.Bank;
import ru.bahmutov.repository.BankRepository;

import java.util.List;

@RequiredArgsConstructor
public class BankHibernateService implements BankRepository {
    private final BankRepository repository;
    @Override
    public List<Bank> updateBankNames(String newName) {
        return repository.updateBankNames(newName);
    }
}
