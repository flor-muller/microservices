package com.dh.movieservice.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQSenderConfig {

    @Value("${queue.movie.name}")
    private String queueMovie;

    @Bean
    public Queue queue() {
        return new Queue(this.queueMovie, true);
    }
}
