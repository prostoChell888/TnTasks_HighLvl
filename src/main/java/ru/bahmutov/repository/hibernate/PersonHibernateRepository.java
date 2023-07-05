package ru.bahmutov.repository.hibernate;

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
    public List<Person> getAllPeople() {
        List<Person> people;
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            var query = session.createQuery("SELECT u FROM Person u", Person.class);
            people = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            throw e;
        }

        return people;
    }


    @Override
    public Person getById(long id) {
        Person person;

        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            person = session.get(Person.class, id);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            throw e;
        }

        return person;
    }


    @Override
    public Person save(Person person) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            throw e;
        }

        return person;
    }


    @Override
    public void deleteAll() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.createQuery("delete from Person").executeUpdate();
                session.createNativeQuery("ALTER SEQUENCE person_id_seq RESTART WITH 1").executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }
}
