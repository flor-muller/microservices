package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("catalog")
public class CatalogController {

    @Autowired
    private IMovieClient IMovieClient;

    @GetMapping("/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre (@PathVariable String genre) {
        return IMovieClient.getMovieByGenre(genre);
    }

    @PostMapping("/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(IMovieClient.saveMovie(movie).getBody());
    }
}
