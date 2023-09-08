package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.ISerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    @Autowired
    private ISerieRepository repository;

    public void saveSerie(Serie serie) {
        repository.save(serie);
    }

}
