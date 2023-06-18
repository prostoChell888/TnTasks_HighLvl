package ru.bahmutov.repository.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import ru.bahmutov.dao.UserDTO;
import ru.bahmutov.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
public class UserHibernateRepository implements UserRepository {

    private final SessionFactory sessionFactory;
    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users;
       try( var session = sessionFactory.getCurrentSession()) {
           session.beginTransaction();

           CriteriaBuilder builder = session.getCriteriaBuilder();
           var criteria = builder.createQuery(UserDTO.class);
           criteria.from(UserDTO.class);
           users = session.createQuery(criteria).getResultList();
           session.getTransaction().commit();
       }
        return users;
    }
}
