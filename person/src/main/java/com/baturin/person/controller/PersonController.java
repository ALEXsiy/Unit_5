package com.baturin.person.controller;
import java.lang.*;
import com.baturin.person.model.Person;
import com.baturin.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public Iterable<Person> getPersons() {
        return service.getPersons();
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return service.addPerson(person);
    }

}
