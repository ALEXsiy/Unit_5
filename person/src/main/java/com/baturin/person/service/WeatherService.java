package com.baturin.person.service;
import com.baturin.person.model.Location;
import com.baturin.person.model.Weather;
import com.baturin.person.repository.LocationRepository;
import com.baturin.person.repository.WeatherRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
@Service
public class WeatherService {
    @Autowired
    private LocationRepository repositoryLocation;
    @Autowired
    private WeatherRepository repositoryWeather;

    public Iterable<Weather> getWeathers() {
        return repositoryWeather.findAll();
    }

    public ResponseEntity<Weather> addWeatherToLocation( int id) throws IOException {
        Optional<Location> optional_message= repositoryLocation.findById(id);
        if (optional_message.isPresent()) {
            Weather weather=new Weather();
            Location location = optional_message.get();
            weather.setLocation(location);
            final String key="ad294eadb905c3a3bb8b29a2b3cc4512";
            String url = "https://api.openweathermap.org/data/2.5/weather?lat="+Double.toString(location.getLat())+"&lon="+Double.toString(location.getLon())+"&appid=" + key;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject weatherJSON = new JSONObject(response.toString());
            weather.setTemp(weatherJSON.getJSONObject("main").getDouble("temp"));
            weather.setFeels_like(weatherJSON.getJSONObject("main").getDouble("feels_like"));
            weather.setTemp_min(weatherJSON.getJSONObject("main").getDouble("temp_min"));
            weather.setTemp_max(weatherJSON.getJSONObject("main").getDouble("temp_max"));
            weather.setPressure(weatherJSON.getJSONObject("main").getDouble("pressure"));
            weather.setHumidity( weatherJSON.getJSONObject("main").getDouble("humidity"));
            weather.setName(location.getLocalName());
            location.setWeather(weather);
            repositoryLocation.save(location);
            return new ResponseEntity<>(weather, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
