package com.baturin.person.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double temp;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double feels_like;
    private double humidity;
    @OneToOne
    @JsonIgnore
    private Location location;

}






