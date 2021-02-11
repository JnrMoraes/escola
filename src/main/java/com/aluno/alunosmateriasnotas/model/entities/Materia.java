package com.aluno.alunosmateriasnotas.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @OneToOne
    private Nota notas;

    public Materia() {
    }

    public Materia(String nome, Nota notas) {
        this.nome = nome;
        this.notas = notas;
    }
}
