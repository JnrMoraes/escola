package com.aluno.alunosmateriasnotas.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double[] notas;

    public Nota() {
    }

    public Nota(double[] notas) {
        this.notas = notas;
    }
}
