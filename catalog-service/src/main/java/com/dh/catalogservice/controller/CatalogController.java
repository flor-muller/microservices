package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.queue.Listener;
import com.dh.catalogservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("catalog")
public class CatalogController {

    @Autowired
    private IMovieClient IMovieClient;

    @Autowired
    private ISerieClient ISerieClient;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private DiscoveryClient discoveryClient; // Para acceder a información de instancias de servicio

    @Autowired
    private Listener listener;

    private static Logger log = Logger.getLogger(CatalogController.class.getName());

    @GetMapping("/movie/{genre}")
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

    @GetMapping("/serie/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {
        return ISerieClient.getSerieByGenre(genre);
    }

    @PostMapping("/movie/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        listener.receive(movie);
        return ResponseEntity.ok().body(IMovieClient.saveMovie(movie).getBody());
    }

    @PostMapping("/serie/save")
    public String createSerie(@RequestBody Serie serie) {
        listener.receive(serie);
        return ISerieClient.createSerie(serie);
    }

    @GetMapping("/{genre}")
    public Genre getAllByGenre(@PathVariable String genre) {
        log.info("Mostrando series y peliculas del genero " + genre);
        return catalogService.findAllByGenre(genre);
    }
}
