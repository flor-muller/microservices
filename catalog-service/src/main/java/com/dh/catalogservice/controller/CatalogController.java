package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("catalog")
public class CatalogController {

    @Autowired
    private IMovieClient IMovieClient;

    @Autowired
    private DiscoveryClient discoveryClient; // Para acceder a información de instancias de servicio

    @GetMapping("/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre (@PathVariable String genre) {
        ResponseEntity<List<Movie>> response = IMovieClient.getMovieByGenre(genre);

        // Obtiene información de la instancia de servicio actual
        List<ServiceInstance> instances = discoveryClient.getInstances("movie-service");
        if (!instances.isEmpty()) {
            int port = instances.get(0).getPort();
            System.out.println("Puerto del servicio Movie: " + port);
        }

        return response;
    }

    @PostMapping("/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(IMovieClient.saveMovie(movie).getBody());
    }
}
