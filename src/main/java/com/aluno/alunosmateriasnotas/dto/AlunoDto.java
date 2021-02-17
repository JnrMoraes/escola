package com.aluno.alunosmateriasnotas.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class AlunoDto extends RepresentationModel<AlunoDto> {

    private Long id;

    @NotBlank(message = "Infomar o nome do Aluno")
    private String nome;

}
