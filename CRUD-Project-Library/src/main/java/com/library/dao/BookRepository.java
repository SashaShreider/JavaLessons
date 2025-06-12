package com.library.dao;

import com.library.model.Book;
import com.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getOwnedBooks(long personId) {
        return jdbcTemplate.query(
                """
                select b.* from book b join person_book pb on pb.book_id = b.id where pb.person_id = ?
                """,
                new BeanPropertyRowMapper<>(Book.class),
                personId
        );
    }

    public List<Book> getUnownedBooks(long personId) {
        return jdbcTemplate.query(
                """
                SELECT b.*
                FROM book b
                LEFT JOIN person_book pb ON b.id = pb.book_id AND pb.person_id = ?
                WHERE pb.book_id IS NULL;
                """,
                new BeanPropertyRowMapper<>(Book.class),
                personId
        );
    }

    public List<Person> getPeopleWithBook(long bookId) {
        return jdbcTemplate.query(
                """
                SELECT p.* FROM person p
                JOIN person_book pb ON p.id = pb.person_id
                WHERE pb.book_id = ?
                """,
                new BeanPropertyRowMapper<>(Person.class),
                bookId
        );
    }

    public List<Person> getPeopleWithoutBook(long bookId) {
        return jdbcTemplate.query(
                """
                SELECT *
                FROM person p
                LEFT JOIN person_book pb ON p.id = pb.person_id and pb.book_id = ?
                where person_id is null
                """,
                new BeanPropertyRowMapper<>(Person.class),
                bookId
        );
    }


    public void assignBook(long bookId, long personId) {
        jdbcTemplate.update(
                "INSERT INTO person_book (person_id, book_id) VALUES (?, ?)",
                personId,
                bookId
        );
    }

    public void returnBook(long bookId, long personId) {
        jdbcTemplate.update(
                "DELETE FROM person_book WHERE book_id = ? AND person_id = ?",
                bookId,
                personId
        );
    }


}
