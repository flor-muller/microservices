package com.dh.catalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Genre {
    private List<Movie> movies;
    private List<Serie> series;
}
