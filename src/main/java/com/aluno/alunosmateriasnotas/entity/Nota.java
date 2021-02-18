package com.aluno.alunosmateriasnotas.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Nota {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double[] bimetre1 = new double[4];
    private double[] bimetre2 = new double[4];
    private double[] bimetre3 = new double[4];
    private double[] bimetre4 = new double[4];
    private double mediaBimentre1;
    private double mediaBimentre2;
    private double mediaBimentre3;
    private double mediaBimentre4;
    private double mediaFinal;

}
