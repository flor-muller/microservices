package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.ISerieRepository;
import com.dh.catalogservice.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Genre findAllByGenre(String genre){
        List<Serie> listSerie = serieRepository.findAllByGenre(genre);
        List<Movie> listMovie = movieRepository.findAllByGenre(genre);
        return new Genre(listMovie, listSerie);
    }
}
