package com.library.controllers;

import com.library.dao.PersonDAO;
import com.library.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;

    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //все пользователи
    @GetMapping()
    public String getAllPeople(Model model) {
        model.addAttribute("people", personDAO.getAll());
        System.out.println(personDAO.getAll());
        return "people/list";
    }

    //страница пользователя
    @GetMapping({"/{person_id}"})
    public String getPersonById(Model model, @PathVariable("person_id") long id) {
        model.addAttribute("person", personDAO.getById(id).orElse(null));
        model.addAttribute("ownedBooks", personDAO.getOwnedBooks(id));
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

//    @PostMapping("/{person_id}/return-book")
//    public String returnBook (@PathVariable("person_id") int id,
//    @RequestParam int book_id,
//    Model model) {
//    }

}
