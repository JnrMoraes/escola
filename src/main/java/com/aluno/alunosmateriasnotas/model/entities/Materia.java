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
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @OneToMany
    private List<Nota> notas;

    public Materia() {
    }

    public Materia(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Materia(Long id, String nome, List<Nota> notas) {
        this.id = id;
        this.nome = nome;
        this.notas = notas;
    }
}
