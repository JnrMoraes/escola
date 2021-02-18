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
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Materia implements Serializable {

    private static final long serialVersionUID = 4715077195893680902L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String nome;

    @OneToMany
    @JoinColumn(name = "nota_id")
    private List<Nota> nota;


    public Materia(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
