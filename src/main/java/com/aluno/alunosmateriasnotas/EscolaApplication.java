package com.aluno.alunosmateriasnotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EscolaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EscolaApplication.class, args);
    }

}
