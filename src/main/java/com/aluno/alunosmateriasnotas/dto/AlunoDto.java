package com.aluno.alunosmateriasnotas.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AlunoDto extends RepresentationModel<AlunoDto> {

    private Long id;

    @NotBlank(message = "Infomar o nome do Aluno")
    private String nome;

}
