package com.baturin.person.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;
}