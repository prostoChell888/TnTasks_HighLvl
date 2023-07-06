package ru.bahmutov.service.sprinddata;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public List<Bank> updateBankNames(@NotBlank String newName) {
        repository.updateBankNames(newName);
        return repository.findAll();
    }
}
