package com.dh.catalogservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.dh.catalogservice.model.Movie;

import java.util.List;

@Repository
public interface IMovieRepository extends MongoRepository<Movie, Long> {
    List<Movie> findAllByGenre(String genre);
}
