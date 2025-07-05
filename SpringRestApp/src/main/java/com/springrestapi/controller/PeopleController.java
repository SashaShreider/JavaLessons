package com.springrestapi.controller;


import com.springrestapi.exception.PersonNotCreatedException;
import com.springrestapi.exception.PersonNotFoundException;
import com.springrestapi.model.Person;
import com.springrestapi.service.PeopleService;
import com.springrestapi.unil.PersonErrorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public List<Person> getPeople() {
        return peopleService.findAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable int id) {
        return peopleService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createPerson(@RequestBody @Valid Person person,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining("; "));
            throw new PersonNotCreatedException(errorMsg);
        }
        peopleService.save(person);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @ExceptionHandler()
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Person not found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
