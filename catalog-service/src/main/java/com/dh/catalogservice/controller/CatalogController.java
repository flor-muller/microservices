package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("catalog")
public class CatalogController {

    @Autowired
    private IMovieClient IMovieClient;

    @GetMapping("/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre (@PathVariable String genre) {
        ResponseEntity<List<Movie>> response = IMovieClient.getMovieByGenre(genre);
        System.out.println("puerto utilizado " + response.getHeaders().get("puerto"));
        return IMovieClient.getMovieByGenre(genre);
    }

    @PostMapping("/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(IMovieClient.saveMovie(movie).getBody());
    }
}
