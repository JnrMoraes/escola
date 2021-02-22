package com.aluno.alunosmateriasnotas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotaDto {

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
