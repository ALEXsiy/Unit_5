package com.baturin.person.service;
import com.baturin.person.model.Person;
import com.baturin.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PersonService {
    @Autowired
    private PersonRepository repositoryPerson;


    public Iterable<Person> getPersons() {
        return repositoryPerson.findAll();
    }

    public Person addPerson( Person person) {
        repositoryPerson.save(person);
        return person;
    }
}
