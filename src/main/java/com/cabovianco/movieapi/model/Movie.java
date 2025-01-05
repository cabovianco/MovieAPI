package com.cabovianco.movieapi.model;

import lombok.Data;

@Data
public class Movie {

    private Long id;
    private String name;
    private String genre;
    private String director;
    private Double timeInMinutes;

}
