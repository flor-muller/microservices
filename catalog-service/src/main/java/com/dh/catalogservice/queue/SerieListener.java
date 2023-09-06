package com.dh.catalogservice.queue;

import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SerieListener {
    @Autowired
    private CatalogService service;

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receive(@Payload Serie serie){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.save(serie);
    }
}
