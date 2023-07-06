package ru.bahmutov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bahmutov.models.Bank;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("UPDATE Bank b SET b.name = :newName ")
    List<Bank> updateBankNames(String newName);
}
