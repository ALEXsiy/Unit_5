package com.baturin.person.controller;
import com.baturin.person.model.Weather;
import com.baturin.person.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/weathers")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping
    public Iterable<Weather> getWeathers() {
        return service.getWeathers();
    }

    @PostMapping("/locations/{id}")
    public ResponseEntity<Weather> addWeatherToLocation(@PathVariable int id) throws IOException {
        return service.addWeatherToLocation(id);
    }
}
