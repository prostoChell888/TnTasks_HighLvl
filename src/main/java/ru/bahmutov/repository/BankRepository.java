package ru.bahmutov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bahmutov.models.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Bank b SET b.name = :newName ")
    void updateBankNames(String newName);
}
