package com.library.controllers;

import com.library.dao.BookDAO;
import com.library.dao.BookRepository;
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

    private final BookDAO bookDAO;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookDAO bookDAO, BookRepository bookRepository) {
        this.bookDAO = bookDAO;
        this.bookRepository = bookRepository;
    }

    @RequestMapping()
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDAO.getAll());
        return "books/list";
    }

    @RequestMapping("/{id}")
    public String getBookById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getById(id).orElseGet(null));
        model.addAttribute("peopleWithBook", bookRepository.getPeopleWithBook(id));
        model.addAttribute("peopleWithoutBook", bookRepository.getPeopleWithoutBook(id));
        return "books/book";
    }
    @PostMapping("/{bookId}/return-book")
    public String returnBook (@PathVariable("bookId") int bookId, @RequestParam("personId") int personId) {
        bookRepository.returnBook(bookId, personId);
        return "redirect:/books/" + bookId;
    }

    @PostMapping("/{bookId}/assign-book")
    public String assignBook (@PathVariable("bookId") int bookId, @RequestParam("personId") int personId) {
        bookRepository.assignBook(bookId, personId);
        return "redirect:/books/" + bookId;
    }
}
