package ru.bahmutov.repository.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bahmutov.models.Person;
import ru.bahmutov.repository.PersonRepository;

import java.util.List;

@RequiredArgsConstructor
public class PersonHibernateRepository implements PersonRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<Person> getAllUsers() {
        List<Person> people;
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            var criteria = builder.createQuery(Person.class);
            criteria.from(Person.class);
            people = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
        }
        return people;
    }

    @Override
    public Person getById(long id) {
        Person person;
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            var query = session.createQuery("SELECT u FROM Person u", Person.class);
            query.executeUpdate();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            var criteria = builder.createQuery(Person.class);
            criteria.from(Person.class);
            person = session.get(Person.class, id);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) transaction.rollback();
            throw e;
        }

        return person;
    }

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public void deleteAll() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                var query = session.createQuery("DELETE FROM Person", Person.class);
                query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
