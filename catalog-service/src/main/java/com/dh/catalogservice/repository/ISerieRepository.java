package com.dh.catalogservice.repository;

import com.dh.catalogservice.model.Serie;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface ISerieRepository extends MongoRepository<Serie, Long> {

}


