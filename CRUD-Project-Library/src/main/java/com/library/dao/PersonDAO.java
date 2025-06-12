package com.library.dao;

import com.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO implements DAO<Person> {
    JdbcTemplate jdbcTemplate;


    @Autowired
    PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Person> getById(long id) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("select * from person where id = ?", new BeanPropertyRowMapper<>(Person.class), id));
    }

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public void save(Person person) {
        jdbcTemplate.update("insert into person (fio) VALUES (?)", person.getFio());
    }

    @Override
    public void update(Person updatedPerson, long id) {
        jdbcTemplate.update("update person (fio) values(?) where id = ?", updatedPerson.getFio());
    }

    @Override
    public void delete(Person person) {
        jdbcTemplate.update("delete from person where id = ?", person.getId());
    }
}
