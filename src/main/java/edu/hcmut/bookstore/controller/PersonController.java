package edu.hcmut.bookstore.controller;

import edu.hcmut.bookstore.dao.Person;
import edu.hcmut.bookstore.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    PersonRepository repo;

    PersonController(PersonRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/api/person/")
    public List<Person> getAll() {
        return this.repo.findAll();
    }

    @GetMapping("/api/person/filter")
    public List<Person> getPeopleOlderThanAge(@RequestParam int age) {
        return this.repo.findByAgeGreaterThan(age);
    }

    @GetMapping("/api/person/{id}")
    public Person getPersonById(@PathVariable int id) {
        return repo.findById(id);
    }

    @PostMapping("/api/person/")
    public Person addPerson(@RequestBody Person newPerson) {
        return repo.save(newPerson);
    }
}
