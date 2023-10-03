package com.baturin.person.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Location {
    //поля
    @Id
    @GeneratedValue
    private int id;
    @NonNull private String localName;
    @NonNull private double lon;
    @NonNull private double lat;

    @OneToOne
    @JsonIgnore
    private Person person;
    @OneToOne(cascade = CascadeType.ALL)
    private Weather weather;


    public Location(@NonNull String localName,@NonNull double lon,@NonNull double lat) {
        this.lat=lat;
        this.lon=lon;
        this.localName=localName;
    }

}