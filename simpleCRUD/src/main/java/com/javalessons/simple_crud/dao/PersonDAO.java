package com.javalessons.simple_crud.dao;

import com.javalessons.simple_crud.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {

        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Person WHERE id = ?",
                new BeanPropertyRowMapper<>(Person.class), id);
    }

    public Optional<Person> show(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from Person where email = ?",
                    new BeanPropertyRowMapper<>(Person.class), email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (name, age, email) VALUES (?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

//    public void testMultipleUpdate() {
//        List<Person> people = createRandom1000People();
//        long start = System.currentTimeMillis();
//        for (Person person : people) {
//            jdbcTemplate.update("INSERT INTO Person VALUES (?, ?, ?, ?)",
//                    person.getId(),person.getName(),person.getAge(),person.getEmail());
//                    }
//        long end = System.currentTimeMillis();
//        System.out.println("Time: " + (end - start));
//    }
//
//    public void testBatchUpdate() {
//        List<Person> people = createRandom1000People();
//        long start = System.currentTimeMillis();
//        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES (?, ?, ?, ?)",
//                new BatchPreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
//                        preparedStatement.setInt(1, people.get(i).getId());
//                        preparedStatement.setString(2, people.get(i).getName());
//                        preparedStatement.setInt(3, people.get(i).getAge());
//                        preparedStatement.setString(4, people.get(i).getEmail());
//                    }
//
//                    @Override
//                    public int getBatchSize() {
//                        return people.size();
//                    }
//                });
//        long end = System.currentTimeMillis();
//
//        System.out.println("Time: " + (end - start));
//    }
//
//    private List<Person> createRandom1000People() {
//        List<Person> people = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            people.add(new Person(
//                    i,
//                    "Name" + i,
//                    i,
//                    "test" + i + "@mail.ru"
//            ));
//        }
//        return people;
//    }


    //Тест производительности массовой вставки


}
