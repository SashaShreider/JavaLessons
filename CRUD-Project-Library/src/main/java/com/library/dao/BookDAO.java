package com.library.dao;

import com.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO implements DAO<Book> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        "SELECT * FROM book WHERE id = ?",
                        new BeanPropertyRowMapper<>(Book.class),
                        id
                ));
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM book",
                new BeanPropertyRowMapper<>(Book.class)
        );
    }

    @Override
    public void save(Book book) {
        jdbcTemplate.update(
                "INSERT INTO book (title, author) VALUES (?, ?)",
                book.getTitle(),
                book.getAuthor()
        );
    }

    @Override
    public void update(Book updatedBook, long id) {
        jdbcTemplate.update(
                "UPDATE book SET title = ?, author = ? WHERE id = ?",
                updatedBook.getTitle(),
                updatedBook.getAuthor(),
                id
        );
    }

    @Override
    public void delete(Book book) {
        jdbcTemplate.update(
                "DELETE FROM book WHERE id = ?",
                book.getId()
        );
    }
}