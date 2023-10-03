package com.baturin.person.service;
import com.baturin.person.model.Location;
import com.baturin.person.model.Person;
import com.baturin.person.repository.LocationRepository;
import com.baturin.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private PersonRepository repositoryPerson;
    @Autowired
    private LocationRepository repositoryLocation;

    public Iterable<Location> getLocations() {
        return repositoryLocation.findAll();
    }
    public ResponseEntity<Person> addLocationToPerson( int id, Location location) {
        Optional<Person> optional_person = repositoryPerson.findById(id);
        if (optional_person.isPresent()) {
            Person person = optional_person.get();
            location.setPerson(person);
            person.setLocation(location);
            repositoryPerson.save(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
