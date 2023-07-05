package ru.bahmutov.repository.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bahmutov.models.Bank;
import ru.bahmutov.repository.BankRepository;

import java.util.List;

@RequiredArgsConstructor
public class BankHibernateRepository implements BankRepository {

    private final SessionFactory sessionFactory;
    @Override
    public List<Bank> updateBankNames(String newName) {
        List<Bank> banks;
        try(var session = sessionFactory.openSession()) {
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

    @Override
    public List<Bank> getAllBunks() {
        List<Bank> banks;
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            var query = session.createQuery("SELECT u FROM Bank u", Bank.class);
            banks = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            throw e;
        }

        return banks;
    }

    @Override
    public Bank getById(long id) {
        Bank bank;

        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            bank = session.get(Bank.class, id);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            throw e;
        }

        return bank;
    }

    @Override
    public Bank save(Bank bank) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(bank);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            throw e;
        }

        return bank;
    }

    @Override
    public void deleteAll() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.createQuery("delete from Bank").executeUpdate();
                session.createNativeQuery("ALTER SEQUENCE bank_id_seq RESTART WITH 1").executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }
}
