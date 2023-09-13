package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.ISerieRepository;
import com.dh.catalogservice.repository.IMovieRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CatalogService {

    @Autowired
    private ISerieRepository serieRepository;

    @Autowired
    private IMovieRepository movieRepository;

    public void saveSerie(Serie serie) {
        serieRepository.save(serie);
    }

    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @CircuitBreaker(name = "catalogs", fallbackMethod = "findAllByGenreEmpty")
    @Retry(name = "catalogs")
    public Genre findAllByGenre(String genre, Boolean throwError) {
        List<Serie> listSerie = serieRepository.findAllByGenre(genre);
        List<Movie> listMovie = movieRepository.findAllByGenre(genre);
        if (throwError) {
            throw new RuntimeException();
        }
        return new Genre(listMovie, listSerie);
    }

    private Genre findAllByGenreEmpty(CallNotPermittedException exception){
        List<Serie> listSerie = new ArrayList<>();
        List<Movie> listMovie = new ArrayList<>();
        return new Genre(listMovie, listSerie);
    }
}
