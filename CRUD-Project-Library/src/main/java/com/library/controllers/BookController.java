package com.library.controllers;

import com.library.dao.BookDAO;
import com.library.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    BookDAO bookDAO;
    BookRepository bookRepository;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @RequestMapping()
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDAO.getAll());
        return "books/list";
    }

    @RequestMapping("/{id}")
    public String getBookById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getById(id).orElseGet(null));
        model.addAttribute("ownerPeople", bookRepository.getPeopleWithBook(id));
        return "books/book";
    }

}
