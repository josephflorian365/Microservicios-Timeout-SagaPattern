package com.martinsaman.libreria;

import com.martinsaman.libreria.persistence.ILibro;
import com.martinsaman.libreria.persistence.Libro;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class LibreriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibreriaApplication.class, args);
    }

    @Bean
    CommandLineRunner preloadData(ILibro repo) {
        return args -> {
            repo.deleteAll();
            repo.save(new Libro("09876543","A 200 millas",200.8));
            repo.save(new Libro("12345678","Mi lucha, tu lucha nuestra lucha",23.2));
        };
    }

}
