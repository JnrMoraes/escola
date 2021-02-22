package com.aluno.alunosmateriasnotas.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Aluno implements Serializable {


    private static final long serialVersionUID = 837052379084307171L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String nome;

    @OneToMany
    @JoinColumn(name = "materia_id")
    private List<Materia> materias;

    public Aluno(Long id, String nome, Materia materia) {
        this.id = id;
        this.nome = nome;
        this.materias = new ArrayList<>();
    }

}
