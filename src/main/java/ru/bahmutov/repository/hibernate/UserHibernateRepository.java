package ru.bahmutov.repository.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import ru.bahmutov.models.User;
import ru.bahmutov.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
public class UserHibernateRepository implements UserRepository {

    private final SessionFactory sessionFactory;
    @Override
    public List<User> getAllUsers() {
        List<User> users;
       try( var session = sessionFactory.getCurrentSession()) {
           session.beginTransaction();

           CriteriaBuilder builder = session.getCriteriaBuilder();
           var criteria = builder.createQuery(User.class);
           criteria.from(User.class);
           users = session.createQuery(criteria).getResultList();
           session.getTransaction().commit();
       }
        return users;
    }
}
