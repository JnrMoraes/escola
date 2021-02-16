package com.aluno.alunosmateriasnotas.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Aluno implements Serializable {


    private static final long serialVersionUID = 837052379084307171L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

//    @OneToMany
//    @JoinColumn(name = "materia_id")
//    private List<Materia> materias;

    public Aluno(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

//    public Aluno(Long id, String nome, List<Materia> materias) {
//        this.id = id;
//        this.nome = nome;
//        this.materias = materias;
//    }
}
