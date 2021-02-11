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
    private double nota1;
    private double nota2;
    private double nota3;
    private double nota4;

    public Nota() {
    }

    public Nota(double nota1, double nota2, double nota3, double nota4) {
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
    }
}
