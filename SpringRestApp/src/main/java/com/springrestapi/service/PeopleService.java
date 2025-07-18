package com.springrestapi.service;

import com.springrestapi.exception.PersonNotFoundException;
import com.springrestapi.model.Person;
import com.springrestapi.repo.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Person findById(long id) {
        return peopleRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public void save(Person person) {
        enrichPerson(person);
        peopleRepository.save(person);
    }

    private void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("Admin");
    }
}
