package ru.bahmutov.service.hibernate;

import lombok.RequiredArgsConstructor;
import ru.bahmutov.dto.BankDto;
import ru.bahmutov.repository.BankRepository;
import ru.bahmutov.service.BankService;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class BankHibernateService implements BankService {
    private final BankRepository repository;
    @Override
    public List<BankDto> updateBankNames(String newName) {
        List<BankDto> bankDtoList = new LinkedList<>();
        var banks =  repository.updateBankNames(newName);
        banks.forEach(bank -> bankDtoList.add(new BankDto(bank.getId(), bank.getName())));
        return bankDtoList;
    }
}
