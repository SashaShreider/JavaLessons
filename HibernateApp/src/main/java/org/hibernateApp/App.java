package org.hibernateApp;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernateApp.model.Person;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class);

        // создание сессии
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()){
            Person person = new Person("Name", 10);

            //начало транзакции
            session.beginTransaction();

            List<Person> people = session.createQuery("from Person", Person.class).getResultList();

            session.persist(person);

            // коммит транзакции
            session.getTransaction().commit();

        }
    }
}
