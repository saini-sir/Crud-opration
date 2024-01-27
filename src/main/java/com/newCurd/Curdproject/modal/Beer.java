package com.newCurd.Curdproject.modal;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;

import java.security.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Entity defines that a class can be mapped to a table.
@Table(name = "beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key & generated value for columns
    private int id;

    @Column(name = "beer_name")
    private String beerName;

    @Column(name = "beer_desc")
    private String desc;

    @Column(name = "beer_abv")
    private double beerABV;

    @Column(name = "beer_ibu")
    private int beerIBU;

}
