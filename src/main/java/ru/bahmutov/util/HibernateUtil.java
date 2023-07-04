package ru.bahmutov.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {


    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        try  {
             return configuration.buildSessionFactory();
        } catch (Exception e) {
            getSessionFactory().close();
            throw e;
        }
    }


    public static SessionFactory getSessionFactoryWithCustomConfig(String url, String username, String password) {
        Configuration configuration = new Configuration().configure()
                .setProperty("hibernate.connection.url", url)
                .setProperty("hibernate.connection.username", username)
                .setProperty("hibernate.connection.password", password);

        try  {
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            getSessionFactory().close();
            throw e;
        }
    }
}
