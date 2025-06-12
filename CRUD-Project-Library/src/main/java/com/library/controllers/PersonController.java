package com.library.controllers;

import com.library.dao.BookDAO;
import com.library.dao.BookRepository;
import com.library.dao.PersonDAO;
import com.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final BookRepository bookRepository;

    @Autowired
    public PersonController(PersonDAO personDAO, BookDAO bookDAO, BookRepository bookRepository) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.bookRepository = bookRepository;
    }

    //все пользователи
    @GetMapping()
    public String getAllPeople(Model model) {
        model.addAttribute("people", personDAO.getAll());
        System.out.println(personDAO.getAll());
        return "people/list";
    }

    //страница пользователя
    @GetMapping({"/{id}"})
    public String getPersonById(Model model, @PathVariable("id") long id) {
        model.addAttribute("person", personDAO.getById(id).orElse(null));
        model.addAttribute("ownedBooks", bookRepository.getOwnedBooks(id));
        model.addAttribute("unOwnedBooks", bookRepository.getUnownedBooks(id));
        return "people/person";
    }

    //форма создания пользователя
    @GetMapping("/new")
    public String createPersonForm(Model model) {
        model.addAttribute("person", new Person());

        return "people/new";
    }

    //создание пользователя
    @PostMapping
    public String createUser(@ModelAttribute Person person) {
        personDAO.save(person);
        return "redirect:/people/list";
    }

    @PostMapping("/{personId}/return-book")
    public String returnBook(@PathVariable("personId") int personId, @RequestParam("bookId") int bookId) {
        bookRepository.returnBook(bookId, personId);
        return "redirect:/people/" + personId;
    }

    @PostMapping("/{personId}/assign-book")
    public String assignBook(@PathVariable("personId") int personId, @RequestParam("bookId") int bookId) {
        bookRepository.assignBook(bookId, personId);
        return "redirect:/people/" + personId;
    }
}
