package com.aluno.alunosmateriasnotas.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AlunoDto {

    private Long id;

    @NotBlank(message = "Infomar o nome do Aluno")
    private String nome;

}
