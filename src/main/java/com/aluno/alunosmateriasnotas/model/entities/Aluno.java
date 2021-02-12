package com.aluno.alunosmateriasnotas.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @OneToMany
    private List<Materia> materias;

    public Aluno() {
    }

    public Aluno(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Aluno(Long id, String nome, List<Materia> materias) {
        this.id = id;
        this.nome = nome;
        this.materias = materias;
    }
}
