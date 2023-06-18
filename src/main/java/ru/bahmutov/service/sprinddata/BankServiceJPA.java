package ru.bahmutov.service.sprinddata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bahmutov.models.Bank;
import ru.bahmutov.repository.BankRepository;
import ru.bahmutov.service.BankService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankServiceJPA implements BankService {

    private final BankRepository repository;

    @Override
    public List<Bank> updateBankNames(String newName) {
        return repository.updateBankNames(newName);
    }
}
