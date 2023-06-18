package ru.bahmutov.repository.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import ru.bahmutov.models.Bank;
import ru.bahmutov.repository.BankRepository;

import java.util.List;

@RequiredArgsConstructor
public class BankHibernateRepository implements BankRepository {

    private final SessionFactory sessionFactory;
    @Override
    public List<Bank> updateBankNames(String newName) {
        List<Bank> banks;
        try(var session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            var criteria = builder.createQuery(Bank.class);
            criteria.from(Bank.class);
            banks = session.createQuery(criteria).getResultList();
            for (var bank : banks) {
                bank.setName(newName);
            }
            session.getTransaction().commit();
        }

        return banks;
    }
}
