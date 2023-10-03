package com.baturin.person.controller;
import com.baturin.person.model.Location;
import com.baturin.person.model.Person;
import com.baturin.person.repository.LocationRepository;
import com.baturin.person.repository.PersonRepository;
import com.baturin.person.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private PersonRepository repositoryPerson;
    @Autowired
    private LocationRepository repositoryLocation;
    @Autowired
    private LocationService service;

    @GetMapping
    public Iterable<Location> getLocations() {
        return service.getLocations();
    }
    @PostMapping("/persons/{id}")
    public ResponseEntity<Person> addLocationToPerson(@PathVariable int id, @RequestBody Location location) {
       return service.addLocationToPerson(id,location);
    }
}
