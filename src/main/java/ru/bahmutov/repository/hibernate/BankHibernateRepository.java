package ru.bahmutov.repository.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import ru.bahmutov.dao.BankDTO;
import ru.bahmutov.dao.UserDTO;
import ru.bahmutov.repository.BankRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BankHibernateRepository implements BankRepository {

    private final SessionFactory sessionFactory;
    @Override
    public List<BankDTO> updateBankNames(String newName) {
        List<BankDTO> banks;
        try(var session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            var criteria = builder.createQuery(BankDTO.class);
            criteria.from(BankDTO.class);
            banks = session.createQuery(criteria).getResultList();
            for (var bank : banks) {
                bank.setName(newName);
            }
            session.getTransaction().commit();
        }

        return banks;
    }
}
