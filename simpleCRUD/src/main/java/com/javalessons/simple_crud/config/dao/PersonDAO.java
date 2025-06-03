package com.javalessons.simple_crud.config.dao;

import com.javalessons.simple_crud.config.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private final List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT,"Tom","tom@mail.ru", 10 ));
        people.add(new Person(++PEOPLE_COUNT, "Jack", "Jack@mail.ru", 19 ));
        people.add(new Person(++PEOPLE_COUNT, "Bob", "Bob@mail.ru", 15 ));
        people.add(new Person(++PEOPLE_COUNT, "Sally", "Sally@mail.ru", 22 ));

    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person personToUpdate = show(id);
        personToUpdate.setName(person.getName());
        personToUpdate.setEmail(person.getEmail());
        personToUpdate.setAge(person.getAge());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }

}
