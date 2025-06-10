package com.library.controllers;

import com.library.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class BookController {

    BookDAO bookDAO;

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
        model.addAttribute("ownerPeople", bookDAO.getBookOwners(id));
        return "books/book";
    }

    // Вернуть книгу
//    @PostMapping(")
//    public String returnBook(@PathVariable("person_id") int id,
//                             @RequestParam int book_id,
//                             Model model) {
//        bookDAO.
//    }
}
