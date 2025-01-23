package com.cabovianco.movieapi.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class Movie {

    @Null
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String genre;

    @NotNull
    private String director;

    @NotNull
    @Min(0)
    private Double timeInMinutes;

}
